package com.github.lookout.fastrsa;

import com.squareup.crypto.rsa.NativeRSAEngine;

import java.lang.reflect.Field;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

import org.bouncycastle.crypto.Digest;
import org.bouncycastle.jcajce.provider.util.DigestFactory;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.oiw.OIWObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.asn1.teletrust.TeleTrusTObjectIdentifiers;
import org.bouncycastle.crypto.encodings.ISO9796d1Encoding;
import org.bouncycastle.crypto.encodings.OAEPEncoding;
import org.bouncycastle.crypto.encodings.PKCS1Encoding;
import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.bouncycastle.util.Strings;

public class FastCipherSpi extends CipherSpi {

    private FastCipherSpi(AsymmetricBlockCipher cipher) {
        super( cipher );
    }

    private void initFromSpec(OAEPParameterSpec pSpec)
        throws NoSuchPaddingException, NoSuchFieldException, IllegalAccessException
    {
        MGF1ParameterSpec mgfParams = (MGF1ParameterSpec)pSpec.getMGFParameters();
        Digest digest = DigestFactory.getDigest(mgfParams.getDigestAlgorithm());

        if (digest == null)
        {
            throw new NoSuchPaddingException("no match on OAEP constructor for digest algorithm: "+ mgfParams.getDigestAlgorithm());
        }

        cipher(new OAEPEncoding(new NativeRSAEngine(), digest, ((PSource.PSpecified)pSpec.getPSource()).getValue()));
        set( pSpec, "paramSpec" );
    }

    private void cipher(AsymmetricBlockCipher cipher)
        throws NoSuchFieldException, IllegalAccessException
    {
        set( cipher, "cipher" );
    }

    private void set(Object object, String name)
        throws NoSuchFieldException, IllegalAccessException
    {
        Field field = getClass().getSuperclass().getSuperclass().getDeclaredField(name);
        field.setAccessible(true);
        field.set(this, object);
    }

    protected void engineSetPadding(
        String padding)
        throws NoSuchPaddingException
    {
        try {
            String pad = Strings.toUpperCase(padding);

            if (pad.equals("NOPADDING"))
                {
                    cipher(new NativeRSAEngine());
                }
            else if (pad.equals("PKCS1PADDING"))
                {
                    cipher(new PKCS1Encoding(new NativeRSAEngine()));
                }
            else if (pad.equals("ISO9796-1PADDING"))
                {
                    cipher(new ISO9796d1Encoding(new NativeRSAEngine()));
                }
            else if (pad.equals("OAEPWITHMD5ANDMGF1PADDING"))
                {
                    initFromSpec(new OAEPParameterSpec("MD5", "MGF1", new MGF1ParameterSpec("MD5"), PSource.PSpecified.DEFAULT));
                }
            else if (pad.equals("OAEPPADDING"))
                {
                    initFromSpec(OAEPParameterSpec.DEFAULT);
                }
            else if (pad.equals("OAEPWITHSHA1ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-1ANDMGF1PADDING"))
                {
                    initFromSpec(OAEPParameterSpec.DEFAULT);
                }
            else if (pad.equals("OAEPWITHSHA224ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-224ANDMGF1PADDING"))
                {
                    initFromSpec(new OAEPParameterSpec("SHA-224", "MGF1", new MGF1ParameterSpec("SHA-224"), PSource.PSpecified.DEFAULT));
                }
            else if (pad.equals("OAEPWITHSHA256ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-256ANDMGF1PADDING"))
                {
                    initFromSpec(new OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec.SHA256, PSource.PSpecified.DEFAULT));
                }
            else if (pad.equals("OAEPWITHSHA384ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-384ANDMGF1PADDING"))
                {
                    initFromSpec(new OAEPParameterSpec("SHA-384", "MGF1", MGF1ParameterSpec.SHA384, PSource.PSpecified.DEFAULT));
                }
            else if (pad.equals("OAEPWITHSHA512ANDMGF1PADDING") || pad.equals("OAEPWITHSHA-512ANDMGF1PADDING"))
                {
                    initFromSpec(new OAEPParameterSpec("SHA-512", "MGF1", MGF1ParameterSpec.SHA512, PSource.PSpecified.DEFAULT));
                }
            else
                {
                    throw new NoSuchPaddingException(padding + " unavailable with RSA.");
                }
        }
        catch(NoSuchFieldException e){
            System.err.println("fall back to slow engine: " + e.getMessage());
            super.engineSetPadding(padding);
        }
        catch(IllegalAccessException e){
            System.err.println("fall back to slow engine: " + e.getMessage());
            super.engineSetPadding(padding);
        }
    }

    static public class NoPadding
        extends FastCipherSpi
    {
        public NoPadding()
        {
            super(new NativeRSAEngine());
        }
    }

    static public class PKCS1v1_5Padding
        extends FastCipherSpi
    {
        public PKCS1v1_5Padding()
        {
            super(new PKCS1Encoding(new NativeRSAEngine()));
        }
    }

    static public class PKCS1v1_5Padding_PrivateOnly
        extends FastCipherSpi
    {
        public PKCS1v1_5Padding_PrivateOnly()
        {
            super(new PKCS1Encoding(new NativeRSAEngine()));
            try {
                engineSetMode("1");// private key only
            }
            catch(Exception e) {
                throw new RuntimeException( "bug", e );
            }
        }
    }

    static public class PKCS1v1_5Padding_PublicOnly
        extends FastCipherSpi
    {
        public PKCS1v1_5Padding_PublicOnly()
        {
            super(new PKCS1Encoding(new NativeRSAEngine()));
            try {
                engineSetMode("2");// public key only
            }
            catch(Exception e) {
                throw new RuntimeException( "bug", e );
            }
        }
    }

    static public class OAEPPadding
        extends FastCipherSpi
    {
        public OAEPPadding()
        {
            super(new NativeRSAEngine());
            //super(OAEPParameterSpec.DEFAULT);
            try {
                engineSetPadding("OAEPPADDING");
            }
            catch(Exception e) {
                throw new RuntimeException( "bug", e );
            }
        }
    }

    static public class ISO9796d1Padding
        extends FastCipherSpi
    {
        public ISO9796d1Padding()
        {
            super(new ISO9796d1Encoding(new NativeRSAEngine()));
        }
    }
}
