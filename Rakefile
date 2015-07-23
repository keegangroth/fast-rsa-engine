#-*- mode: ruby -*-

require 'bundler/gem_tasks'
require 'ruby-maven'

desc "Pack fast-rsa-engine.jar with the compiled classes"
task :jar do
  RubyMaven.exec('prepare-package', '-Dmaven.test.skip')
end

require "rspec/core/rake_task"
RSpec::Core::RakeTask.new

task :default => [ :jar, :spec ]

require 'rubygems/package_task'
Gem::PackageTask.new( eval File.read( './fast-rsa-engine.gemspec' ) ) do
  desc 'Pack leafy-metrics.gem'
  task :package => [:jar]
end

# vim: syntax=ruby
