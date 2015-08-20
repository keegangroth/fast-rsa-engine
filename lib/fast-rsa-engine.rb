if RUBY_PLATFORM == 'java'
  require 'fast-rsa-engine_jars.rb'
  require 'fast-rsa-engine.jar'
  require 'openssl'

  # keep the default name space clean and use tap
  tap do
    sign_names = [ "MD2", "MD4", "MD5",
                   "RIPEMD128", "RIPEMD160", "RIPEMD256",
                   "SHA1", "SHA224", "SHA256", "SHA384", "SHA512" ]
    sign_names.each do |name|
      full = "#{name}WITHRSA"
      clazz = JRuby.runtime.jruby_class_loader.load_class( "com.github.lookout.fastrsa.FastDigestSignatureSpi$#{name}" )
      Java::OrgJrubyExtOpenssl::SecurityHelper.add_signature(full, clazz)
    end

    Java::OrgJrubyExtOpenssl::SecurityHelper.add_cipher('RSA',
                                                        com.github.lookout.fastrsa.FastCipherSpi::NoPadding.java_class)
  end
else
  warn "fast-rsa-engine does not affect MRI"
end
