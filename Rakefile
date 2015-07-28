#-*- mode: ruby -*-

require 'bundler/gem_tasks'

if RUBY_PLATFORM == 'java'
  require 'ruby-maven'

  desc "Pack fast-rsa-engine.jar with the compiled classes"
  task :jar do
    RubyMaven.exec('prepare-package', '-Dmaven.test.skip')
  end
end

require "rspec/core/rake_task"
RSpec::Core::RakeTask.new

require 'rubygems/package_task'
Gem::PackageTask.new( eval File.read( './fast-rsa-engine.gemspec' ) ) do
  desc 'Pack fast-rsa-engine.gem'
  task :package => [:jar]
end

task :default => [ :jar, :spec ]

# vim: syntax=ruby
