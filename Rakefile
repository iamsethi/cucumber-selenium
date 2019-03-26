task :run_scenario_count, [:tags, :env] do |task, args|
  test_directory = Dir.pwd + '/features'
  tags = args[:tags].split(' ')
  included_tags = tags.select {|tag| tag.include?('@') && !tag.include?('~')}
  excluded_tags = tags.select {|tag| tag.include?('~@')}.map {|tag| tag.gsub('~','')}
  filters = {
      excluded_tags: excluded_tags,
      included_tags: included_tags
  }
  found_tests = CukeSlicer::Slicer.new.slice(test_directory, filters, :file_line)
  tests_in_suite = found_tests.count
  tests_in_suite = 1 if (tests_in_suite < 1 || tests_in_suite.nil?)

  args[:env].nil? ? env = 'preproduction' : env = args[:env]
  env = 'preproduction' if env == /preprod/

  TestRun.state = 'PRE-SCENARIO'
  puts tests_in_suite
end