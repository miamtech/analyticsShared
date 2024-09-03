require 'fileutils'

# Helper method to convert a string to PascalCase, preserving inner capitalization
def camelize(str)
  str.gsub(/(?:^|_)([a-z])/) { $1.upcase }
end

# Method to generate TypeScript interface and function based on a Kotlin function signature
def generate_ts_interface(kotlin_signature, original_function_name, js_function_name, output_folder)
  dependencies = []
  # Split parameters and process each one
  params_string = kotlin_signature.match(/\((.*?)\)/m)[1] # 'm' flag to match across multiple lines
  params = params_string.split(",").map(&:strip)
  ts_interface_params = []
  ts_function_params = []

  params.each do |param|
    name, type = param.split(":").map(&:strip)
    is_optional = type.end_with?("?")

    # TypeScript equivalent of Kotlin types
    ts_type = case type.chomp("?")
              when "String" then "string"
              when "Int" then "number"
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

    ts_interface_params << "#{name}#{is_optional ? '?' : ''}: #{ts_type};"
    ts_function_params << name
  end

  # Generate TypeScript interface and function
  ts_interface_name = camelize(original_function_name) + "Params"
  ts_function_name = js_function_name + 'TS'
  import_statement = "import { #{original_function_name} } from '../analyticsShared.js';"
  ts_interface = "interface #{ts_interface_name} {\n  #{ts_interface_params.join("\n  ")}\n}\n"
  ts_function = <<~TS
    export function #{ts_function_name}({ #{ts_function_params.join(", ")} }: #{ts_interface_name}): void {
      // Call the original Kotlin function
      #{original_function_name}(#{ts_function_params.join(", ")});
    }
  TS

  # Handle dependencies by adding import statements
  imports = dependencies.map do |dependency|
    "import { #{dependency} } from './#{dependency}';"
  end.join("\n")

  # Create the output folder if it doesn't exist
  FileUtils.mkdir_p(output_folder)

  # Define the output file path
  output_file_path = File.join(output_folder, "#{js_function_name}.ts")

  # Write the TypeScript code to the file
  File.open(output_file_path, 'w') do |file|
    file.puts imports unless imports.empty?
    file.puts import_statement
    file.puts ts_interface
    file.puts ts_function
  end

  puts "TypeScript file generated at: #{output_file_path}"
end

# Method to process Kotlin files in a directory
def process_kotlin_files(directory, output_folder)
  Dir.glob(File.join(directory, '**/*.kt')).each do |file_path|
    puts "Processing file: #{file_path}"
    lines = File.readlines(file_path)

    lines.each_with_index do |line, index|
      if line =~ /@JsName/  # Look for the @JsName annotation
        js_name = line.match(/@JsName\("(.*?)"\)/)[1]
        function_lines = []
        while true
          next_line = lines[index + 1]&.strip
          break unless next_line

          # Continue to collect lines until a line ends with ')', indicating the end of the function signature
          function_lines << next_line
          index += 1
          break if next_line.include?(")")
        end

        function_line = function_lines.join(" ")
        if function_line =~ /fun (\w+)\s*\(/
          function_name = function_line.match(/fun (\w+)/)[1]
          puts "Found function: #{function_name} with JsName: #{js_name} in #{file_path}"
          generate_ts_interface(function_line, function_name, js_name, output_folder)
        else
          puts "No function found after @JsName in #{file_path}"
        end
      end
    end
  end
end

# Define input directory and output directory
input_directory = File.expand_path('../../analyticsShared/src/commonMain/kotlin/mealz/ai', __dir__)
output_directory = File.expand_path('../../analyticsShared/scripts/typescriptInterfaces', __dir__)

# Process the Kotlin files to generate TypeScript files
process_kotlin_files(input_directory, output_directory)
