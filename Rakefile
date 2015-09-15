#-*- mode: ruby -*-

require 'bundler/gem_tasks'

if RUBY_PLATFORM == 'java'
  require 'jars/installer'

  desc "Pack fast-rsa-engine.jar with the compiled classes"
  task :jar do
    Jars::Installer.new( 'fast-rsa-engine.gemspec' ).install_jars
    raise unless RubyMaven.exec('-f', 'fast-rsa-engine.gemspec', 'prepare-package', '-Dmaven.test.skip')
  end
else
  task :jar do
  end
end

require "rspec/core/rake_task"
RSpec::Core::RakeTask.new

task :build => [:jar]

task :default => [ :jar, :spec ]

# vim: syntax=ruby
