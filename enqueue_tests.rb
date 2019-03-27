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
  "docker run -e HUB_HOST=http://192.168.1.10:4444/wd/hub -e BROWSER=chrome  -e TAG=@#{test} -v ${WORKSPACE}/chrome:/usr/share/udemy/target iamsethi/cucumber-selenium-docker  org.junit.runner.JUnitCore com.amazon.runner.RunnerTest"
end

commands.each { |command| puts command }
