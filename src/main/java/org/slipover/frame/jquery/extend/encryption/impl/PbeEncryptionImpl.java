package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.SymmetryEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/*************************************************************************************************************************************************
 PBE，全称为“Password Base Encryption”，中文名“基于口令加密”，是一种基于密码的加密算法，其特点是使用口令代替了密钥，而口令由用户自己掌管，
 采用随机数杂凑多重加密等方法保证数据的安全性。
 PBE算法没有密钥的概念，把口令当做密钥了。因为密钥长短影响算法安全性，还不方便记忆，这里我们直接换成我们自己常用的口令就大大不同了，便于我们的记忆。
 但是单纯的口令很容易被字典法给穷举出来，所以我们这里给口令加了点“盐”，这个盐和口令组合，想破解就难了。
 同时我们将盐和口令合并后用消息摘要算法进行迭代很多次来构建密钥初始化向量的基本材料，使破译更加难了。
 *************************************************************************************************************************************************/
public class PbeEncryptionImpl implements SymmetryEncryption {

    private static final String KEY = "PBEWITHMD5andDES";

    private static final int LENGTH = 100;

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
        return SecretKeyFactory.getInstance(KEY).generateSecret(new PBEKeySpec(new String(key).toCharArray()));
    }
}
