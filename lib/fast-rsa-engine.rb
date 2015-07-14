require 'fast-rsa-engine_jars.rb'
require 'fast-rsa-engine.jar'
require 'openssl'

tap do
  Helper = Java::OrgJrubyExtOpenssl::SecurityHelper
  engines = Helper.java_class.declared_field 'implEngines'
  engines.accessible = true
  com.lookout.fastrsa.SecurityHelperMap.setup( engines.value( Helper ))
end
