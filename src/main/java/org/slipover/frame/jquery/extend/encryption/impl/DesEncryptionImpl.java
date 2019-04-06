package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.SymmetryEncryption;

import javax.crypto.Cipher;
import javax.crypto.KeyAgreement;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/*************************************************************************************************************************************************
 DES，全称为“DatePattern Encryption Standard”，中文名为“数据加密标准”，是一种使用密钥加密的块算法。
 DES 算法为密码体制中的对称密码体制，又被称为美国数据加密标准，是 1972 年美国 IBM 公司研制的对称密码体制加密算法。
 明文按 64 位进行分组，密钥长 64 位，密钥事实上是 56 位参与 DES 运算（第8、16、24、32、40、48、56、64 位是校验位， 使得每个密钥都有奇数个 1）
 分组后的明文组和 56 位的密钥按位替代或交换的方法形成密文组的加密方法。
 *************************************************************************************************************************************************/
public class DesEncryptionImpl implements SymmetryEncryption {

    private static final String KEY = "DES";

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
        return SecretKeyFactory.getInstance(KEY).generateSecret(new DESKeySpec(key));
    }
}
