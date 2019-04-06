package org.slipover.frame.jquery.extend.encryption;

import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 对称加密
 */
public interface SymmetryEncryption {

    /**
     * 生成密钥
     * @return
     * @throws Exception
     */
    SecretKey generateKey() throws NoSuchAlgorithmException;

    /**
     * 生成密钥
     * @return
     * @throws Exception
     */
    SecretKey generateKey(int length) throws NoSuchAlgorithmException;

    /**
     * 生成密钥
     * @return
     * @throws Exception
     */
    SecretKey generateKey(String key, PublicKey publicKey, PrivateKey privateKey) throws Exception;

    /**
     * 解密
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    byte[] decrypt(byte[] key, byte[] data) throws Exception;

    /**
     * 获取密钥对象
     * @param key
     * @return
     * @throws Exception
     */
    SecretKey getSecretKey(byte[] key) throws Exception;

}
