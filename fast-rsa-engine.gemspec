#-*- mode: ruby -*-

Gem::Specification.new do |s|
  s.name = 'fast-rsa-engine'
  s.version = '0.1'
  s.author = 'christian meier'
  s.email = [ 'christian.meier@lookout.com' ]
  s.platform = 'java'
 
  s.license = 'MIT'
  s.summary = %q(provide an API to register metrics)
  s.homepage = 'https://github.com/lookout/leafy'
  s.description = %q(provides an API to register metrics like meters, timers, gauge, counter using dropwizard-metrics. it also allows to setup reporters: console-reporter, csv-reporter and graphite-reporter)
  
  s.files = `git ls-files`.split($/)

  BC_VERSION = '1.50'
  s.requirements << "jar com.squareup.jnagmp:bouncycastle-rsa, 1.0.0"
  s.requirements << "jar org.bouncycastle:bcpkix-jdk15on, #{BC_VERSION}, :scope => :provided"
  s.requirements << "jar org.bouncycastle:bcprov-jdk15on, #{BC_VERSION}, :scope => :provided"
  s.requirements << "pom org.jruby:jruby-core, 1.7.21, :scope => :provided"

  s.add_runtime_dependency 'jar-dependencies', '~> 0.1'
  s.add_development_dependency 'ruby-maven', '~> 3.3'
  s.add_development_dependency 'rspec', '~> 3.1'
  s.add_development_dependency 'yard', '~> 0.8'
  s.add_development_dependency 'rake', '~> 10.2'
end

# vim: syntax=Ruby
