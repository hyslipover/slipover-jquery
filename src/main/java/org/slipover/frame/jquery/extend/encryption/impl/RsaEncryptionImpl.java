package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.AsymmetryEncryption;
import org.slipover.frame.jquery.extend.encryption.bean.EncryptKey;

import javax.crypto.interfaces.DHPublicKey;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*************************************************************************************************************************************************
 其实，在早在 1978 年的时候，RSA就已经出现了，它是第一个既能用于数据加密也能用于数字签名的算法。它易于理解和操作，也很流行。
 其原理就如上面的工作过程所述。RSA 算法基于一个十分简单的数论事实：将两个大素数相乘十分容易，但是想要对其乘积进行因式分解却极其困难，
 因此可以将乘积公开作为加密密钥。
 *************************************************************************************************************************************************/
public class RsaEncryptionImpl implements AsymmetryEncryption {

    private static final String KEY = "RSA";

    private static final int LENGTH = 512;

    @Override
    public EncryptKey generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(LENGTH);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public EncryptKey generateKey(int length) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(length);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public EncryptKey generateKey(Key key) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(((DHPublicKey) key).getParams());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public PublicKey getPublicKey(byte[] key) throws Exception {
        return KeyFactory.getInstance(KEY).generatePublic(new X509EncodedKeySpec(key));
    }

    @Override
    public PrivateKey getPrivateKey(byte[] key) throws Exception {
        return KeyFactory.getInstance(KEY).generatePrivate(new PKCS8EncodedKeySpec(key));
    }

    @Override
    public byte[] generateSign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    @Override
    public boolean verifySign(byte[] data, PublicKey publicKey, byte[] sign) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    @Override
    public boolean verifySign(EncryptKey encrypt, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(encrypt.getPublicKey());
        signature.initSign(encrypt.getPrivateKey());
        signature.update(data);
        return signature.verify(signature.sign());
    }
}
