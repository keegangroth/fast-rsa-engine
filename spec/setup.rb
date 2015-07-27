$: << File.expand_path( '../../lib', __FILE__ )
require 'fast-rsa-engine'

def too_old
  Gem.loaded_specs['jruby-openssl'].version.to_s < '0.9.6'
end
def engines
  engines = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'implEngines'
  engines.accessible = true
  engines.value( Java::OrgJrubyExtOpenssl::SecurityHelper )
end unless too_old
