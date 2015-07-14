package com.lookout.fastrsa;

import java.util.Map;

public class SecurityHelperMap  {

    public static void setup( Map<String, Class<?>> engines ) {
        engines.put( "Signature:SHA512WITHRSA", FastDigestSignatureSpi.SHA512.class );
    }
}
