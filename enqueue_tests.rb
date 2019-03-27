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
  "bundle exec cucumber -p #{$world.configuration['TEST_ENVIRONMENT']} USE_GRID=true REMOTE_LOGGING=true CLOSE_BROWSER=true SUITE_RUN_ID=#{ENV['SUITE_RUN_ID']} PIPELINE_ID=#{ENV['PIPELINE_ID']} #{test}"
end

commands.each { |command| puts command }
