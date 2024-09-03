require 'fileutils'

# Helper method to convert a string to PascalCase
def camelize(str)
  str.gsub(/(?:^|_)([a-z])/) { $1.upcase }
end

# Method to generate a TypeScript interface from a Kotlin data class
def generate_ts_interface(data_class_name, properties, output_folder)
  dependencies = []
  ts_interface_params = properties.map do |property|
    name, type = property.split(":").map(&:strip)
    next if name.nil? || type.nil?

    # Remove "val" and "= null" from property names and types
    name = name.gsub(/^val\s+/, '').gsub(/\s*=.*$/, '')
    type = type.gsub(/\s*=.*$/, '')

    is_optional = type.end_with?("?")

    # Map Kotlin types to TypeScript types
    ts_type = case type.chomp("?")
              when "String" then "string"
              when "Int", "Long", "Double", "Float" then "number"
              when "Boolean" then "boolean"
              when "PlausibleEvent" then
                  dependencies << "PlausibleEvent"
                  "PlausibleEvent"
              when "PlausibleProps" then
                  dependencies << "PlausibleProps"
                  "PlausibleProps"
              when "onEmitFunction" then
                  dependencies << "PlausibleEvent"
                  "(event: PlausibleEvent) => void"
              else "any" # You can expand this for more types
              end

    "#{name}#{is_optional ? '?' : ''}: #{ts_type};"
  end.compact

  # Generate TypeScript interface
  ts_interface_name = camelize(data_class_name)
  ts_interface = "export interface #{ts_interface_name} {\n  #{ts_interface_params.join("\n  ")}\n}\n"

  # Handle dependencies by adding import statements
  imports = dependencies.map do |dependency|
    "import { #{dependency} } from './#{dependency}';"
  end.join("\n")

  # Create the output folder if it doesn't exist
  FileUtils.mkdir_p(output_folder)

  # Define the output file path
  output_file_path = File.join(output_folder, "#{ts_interface_name}.ts")

  # Write the TypeScript interface to the file
  File.open(output_file_path, 'w') do |file|
    file.puts imports unless imports.empty?
    file.puts ts_interface
  end

  puts "TypeScript interface generated at: #{output_file_path}"
end

# Method to process Kotlin files in a directory and find data classes with @JsName
def process_kotlin_files(directory, output_folder)
  Dir.glob(File.join(directory, '**/*.kt')).each do |file_path|
    puts "Processing file: #{file_path}"
    lines = File.readlines(file_path)

    lines.each_with_index do |line, index|
      if line =~ /@JsName/ && lines[index + 1] =~ /data class/ # Look for @JsName followed by data class
        # Extract data class name
        data_class_name = lines[index + 1].match(/data class (\w+)/)[1]
        puts "Found data class: #{data_class_name} in #{file_path}"

        # Collect all properties of the data class
        properties = []
        index += 2
        while index < lines.size
          next_line = lines[index].strip
          break if next_line.start_with?(")") || next_line.empty?

          properties << next_line.gsub(/,/, '').strip
          index += 1
        end

        # Generate the TypeScript interface
        generate_ts_interface(data_class_name, properties, output_folder)
      end
    end
  end
end

# Define input directory and output directory
input_directory = File.expand_path('../../analyticsShared/src/commonMain/kotlin/mealz/ai', __dir__)
output_directory = File.expand_path('../../analyticsShared/scripts/typescriptInterfaces', __dir__)

# Process the Kotlin files to generate TypeScript interfaces
process_kotlin_files(input_directory, output_directory)
