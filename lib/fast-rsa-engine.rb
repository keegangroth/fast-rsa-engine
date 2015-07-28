if RUBY_PLATFORM == 'java'
  require 'fast-rsa-engine_jars.rb'
  begin
    Java::OrgJrubyExtOpenssl::SecurityHelper
    warn 'openssl loaded before fast-rsa-engine'
  rescue NameError
    require 'fast-rsa-engine.jar'
  end
  require 'openssl'

  # lexical compare is sufficient here
  if Jopenssl::Version::VERSION > '0.9.5'
    # keep the default name space clean and use tap
    tap do
      begin
        engines = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'implEngines'
        engines.accessible = true
        com.github.lookout.fastrsa.SecurityHelperMap.setup( engines.value( Java::OrgJrubyExtOpenssl::SecurityHelper ))
        use_internal = Java::OrgJrubyExtOpenssl::SecurityHelper.java_class.declared_field 'tryCipherInternal'
        use_internal.accessible = true
        use_internal.set_value( Java::OrgJrubyExtOpenssl::SecurityHelper, true )
      rescue => e
        warn e.inspect
      end
    end
  else
    warn "jruby-openssl gem #{Jopenssl::Version::VERSION} is too old"
  end
else
  warn "fast-rsa-engine does not affect MRI"
end
