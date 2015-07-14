require 'fast-rsa-engine_jars.rb'
require 'fast-rsa-engine.jar'
require 'openssl'

# keep the default name space clean and use tap
tap do
  engines = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'implEngines'
  engines.accessible = true
  com.lookout.fastrsa.SecurityHelperMap.setup( engines.value( Java::OrgJrubyExtOpenssl::SecurityHelper ))
end
