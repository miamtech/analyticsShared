require 'fileutils'

# Method to generate TypeScript function declaration based on a Kotlin function signature
def generate_ts_fun_declaration(kotlin_signature, js_function_name, output_file_path)
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
                  "PlausibleEvent"
              when "PlausibleProps" then
                  "PlausibleProps"
              when "onEmitFunction" then
                  "(event: PlausibleEvent) => void"
              else "any" # You can expand this for more types
              end

    ts_function_params << "#{name}#{is_optional ? '?' : ''}: #{ts_type}"
  end

  # Generate TypeScript function
  ts_function_name = js_function_name + 'TS'
  ts_function = <<~TS
    export declare function #{js_function_name}(#{ts_function_params.join(", ")}): void;
  TS

  # Write the TypeScript code to the file
  File.open(output_file_path, 'a') do |file|
    file.puts ts_function
  end

  puts "TypeScript file generated at: #{output_file_path}"
end

# Method to process Kotlin files in a directory
def process_kotlin_files(directory, output_folder)
  # Create the output folder if it doesn't exist
  FileUtils.mkdir_p(output_folder)

  # Define the output file path
  output_file_path = File.join(output_folder, "main.d.ts")

  # Create the empty file if it doesn't exist
  File.open(output_file_path, 'w') do |file|
  end

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
          generate_ts_fun_declaration(function_line, js_name, output_file_path)
        else
          puts "No function found after @JsName in #{file_path}"
        end
      end
    end
  end
end

# Define input directory and output directory
input_directory = File.expand_path('../../mealz-shared-analytics/src/commonMain/kotlin/mealz/ai', __dir__)
output_directory = File.expand_path('../../mealz-shared-analytics/dist', __dir__)

# Process the Kotlin files to generate TypeScript files
process_kotlin_files(input_directory, output_directory)
