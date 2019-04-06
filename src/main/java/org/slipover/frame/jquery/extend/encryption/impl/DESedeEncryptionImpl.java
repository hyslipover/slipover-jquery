package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.SymmetryEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/*************************************************************************************************************************************************
 3DES，也就是“Triple DES”，中文名“三重数据加密算法”，它相当于是对每个数据块应用三次 DES 加密算法。
 由于计算机运算能力的增强，原版 DES 密码的密钥长度变得容易被暴力破解；
 3DES 即是设计用来提供一种相对简单的方法，即通过增加 DES 的密钥长度来避免类似的攻击，而不是设计一种全新的块密码算法。
 *************************************************************************************************************************************************/
public class DESedeEncryptionImpl implements SymmetryEncryption {

    private static final String KEY = "DESede";

    private static final int LENGTH = 168;

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
        return SecretKeyFactory.getInstance(KEY).generateSecret(new DESedeKeySpec(key));
    }
}
