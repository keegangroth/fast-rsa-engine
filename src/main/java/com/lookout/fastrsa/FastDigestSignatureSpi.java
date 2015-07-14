package com.lookout.fastrsa;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.crypto.engines.RSABlindedEngine;
import java.lang.reflect.Field;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;
import com.squareup.crypto.rsa.NativeRSAEngine;

public class FastDigestSignatureSpi extends DigestSignatureSpi {
    
    FastDigestSignatureSpi(ASN1ObjectIdentifier objId, Digest digest, AsymmetricBlockCipher cipher) {
        super( objId, digest, cipher);
    }

    // private static RSABlindedEngine newRSABlindedEngine() throws Exception {
    //     RSABlindedEngine engine = new RSABlindedEngine();
    //     Field f = RSABlindedEngine.class.getDeclaredField( "core" );
    //     f.setAccessible( true );
    //     f.setValue( engine, new com.squareup.crypto.rsa.NativeRSACoreEngine() );
    //     return engine;
    // }

    public static class SHA512 extends FastDigestSignatureSpi {
        public SHA512() throws Exception {
            super( NISTObjectIdentifiers.id_sha512,
                   new SHA512Digest(),
                   new PKCS1Encoding( new NativeRSAEngine() ) );//RSABlindedEngine() ) );
        }
    }
}
