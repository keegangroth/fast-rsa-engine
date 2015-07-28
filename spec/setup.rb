$: << File.expand_path( '../../lib', __FILE__ )
require 'fast-rsa-engine'

def too_old
  if defined? Jopenssl::Version
    Jopenssl::Version::VERSION < '0.9.6'
  else
    true # we are in MRI
  end
end
def engines
  engines = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'implEngines'
  engines.accessible = true
  engines.value( Java::OrgJrubyExtOpenssl::SecurityHelper )
end unless too_old
