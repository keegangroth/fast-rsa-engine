package com.lookout.fastrsa;

import com.squareup.crypto.rsa.NativeRSAEngine;

import java.lang.reflect.Field;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.MD2Digest;
import org.bouncycastle.crypto.digests.MD4Digest;
import org.bouncycastle.crypto.digests.MD5Digest;
import org.bouncycastle.crypto.digests.NullDigest;
import org.bouncycastle.crypto.digests.RIPEMD128Digest;
import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.crypto.digests.RIPEMD256Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.crypto.digests.SHA224Digest;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.digests.SHA384Digest;
import org.bouncycastle.crypto.digests.SHA512Digest;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.DigestSignatureSpi;

public class FastDigestSignatureSpi extends DigestSignatureSpi {
    
    private FastDigestSignatureSpi(ASN1ObjectIdentifier objId, Digest digest, AsymmetricBlockCipher cipher) {
        super( objId, digest, cipher);
    }

    static public class SHA1
        extends DigestSignatureSpi
    {
        public SHA1()
        {
            super(OIWObjectIdentifiers.idSHA1, new SHA1Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class SHA224
        extends DigestSignatureSpi
    {
        public SHA224()
        {
            super(NISTObjectIdentifiers.id_sha224, new SHA224Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class SHA256
        extends DigestSignatureSpi
    {
        public SHA256()
        {
            super(NISTObjectIdentifiers.id_sha256, new SHA256Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class SHA384
        extends DigestSignatureSpi
    {
        public SHA384()
        {
            super(NISTObjectIdentifiers.id_sha384, new SHA384Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class SHA512
        extends DigestSignatureSpi
    {
        public SHA512()
        {
            super(NISTObjectIdentifiers.id_sha512, new SHA512Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class MD2
        extends DigestSignatureSpi
    {
        public MD2()
        {
            super(PKCSObjectIdentifiers.md2, new MD2Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class MD4
        extends DigestSignatureSpi
    {
        public MD4()
        {
            super(PKCSObjectIdentifiers.md4, new MD4Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class MD5
        extends DigestSignatureSpi
    {
        public MD5()
        {
            super(PKCSObjectIdentifiers.md5, new MD5Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class RIPEMD160
        extends DigestSignatureSpi
    {
        public RIPEMD160()
        {
            super(TeleTrusTObjectIdentifiers.ripemd160, new RIPEMD160Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class RIPEMD128
        extends DigestSignatureSpi
    {
        public RIPEMD128()
        {
            super(TeleTrusTObjectIdentifiers.ripemd128, new RIPEMD128Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class RIPEMD256
        extends DigestSignatureSpi
    {
        public RIPEMD256()
        {
            super(TeleTrusTObjectIdentifiers.ripemd256, new RIPEMD256Digest(), new PKCS1Encoding(new NativeRSAEngine()));
        }
    }
}
