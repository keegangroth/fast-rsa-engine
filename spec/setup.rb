$: << File.expand_path( '../../lib', __FILE__ )
require 'fast-rsa-engine'

def engines
  engines = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'implEngines'
  engines.accessible = true
  engines.value( Java::OrgJrubyExtOpenssl::SecurityHelper )
end
