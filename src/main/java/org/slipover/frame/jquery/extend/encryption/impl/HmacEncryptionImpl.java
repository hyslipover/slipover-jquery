package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.SymmetryEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

public class HmacEncryptionImpl implements SymmetryEncryption {

    private static final String KEY = "HMAC";

    private static final int LENGTH = 56;

    @Override
    public SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY);
        keyGenerator.init(LENGTH);
        return keyGenerator.generateKey();
    }

    @Override
    public SecretKey generateKey(int length) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY);
        keyGenerator.init(length);
        return keyGenerator.generateKey();
    }

    @Override
    public SecretKey generateKey(String key, PublicKey publicKey, PrivateKey privateKey) throws Exception {
        KeyAgreement keyAgreement = KeyAgreement.getInstance(key);
        keyAgreement.init(privateKey);
        keyAgreement.doPhase(publicKey, true);
        return keyAgreement.generateSecret(key);
    }

    @Override
    public byte[] decrypt(byte[] key, byte[] data) throws Exception {
        SecretKey secretKey = getSecretKey(key);
        Cipher cipher = Cipher.getInstance(secretKey.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        return cipher.doFinal(data);
    }

    @Override
    public SecretKey getSecretKey(byte[] key) throws Exception {
        return SecretKeyFactory.getInstance(KEY).generateSecret(new SecretKeySpec(key, KEY));
    }
}
