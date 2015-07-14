package com.lookout.fastrsa;

import java.util.Map;

public class SecurityHelperMap  {

    public static void setup( Map<String, Class<?>> engines ) {
        String[] names = {
            "MD2", "MD4", "MD5",
            "RIPEMD128", "RIPEMD160", "RIPEMD256",
            "SHA1", "SHA224", "SHA256", "SHA384", "SHA512"
        };

        ClassLoader classLoader = SecurityHelperMap.class.getClassLoader();
        for (String name : names ) {
            try {
                engines.put( "Signature:" + name + "WITHRSA",
                             classLoader.loadClass( FastDigestSignatureSpi.class.getName() + "$" + name ) );
            }
            catch( ClassNotFoundException e ) {
                System.err.println( "signature class not found for: " + name + " ( " + e.getMessage() + " )" );
            }
        }
    }
}
