dir = ARGV[0]

require_relative './gadgets/slicer_engine'


slicer = SlicerEngine.new
slicer.directory = "./src/test/resources/features"
tags = ARGV[1..-1]
raise "provide tags as a script argument" unless tags
slicer.tags = tags

# initialize tests to have it in scope
tests = nil

puts "Starting pwd for enqueue tests #{Dir.pwd}"
#/home/ketan/git/JenkinsSlave/workspace/SELENIUM_DOCKER_RUNNER

  tests = slicer.slice


commands = tests.map do |test|
  puts tests
end

commands.each { |command| puts command }
