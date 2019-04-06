package org.slipover.frame.jquery.extend.encryption;

import org.slipover.frame.jquery.extend.encryption.bean.EncryptKey;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * 非对称加密
 */
public interface AsymmetryEncryption {
    /**
     * 生成公钥和私钥
     * @return
     * @throws Exception
     */
    EncryptKey generateKey() throws NoSuchAlgorithmException;

    /**
     * 生成公钥和私钥
     * @return
     * @throws Exception
     */
    EncryptKey generateKey(int length) throws Exception;

    /**
     * 生成公钥和私钥
     * @return
     * @throws Exception
     */
    EncryptKey generateKey(Key key) throws Exception;

    PublicKey getPublicKey(byte[] key) throws Exception;

    PrivateKey getPrivateKey(byte[] key) throws Exception;

    /*************************************************************************************************************************************************
     数字签名证书：防止公钥被篡改
     *************************************************************************************************************************************************/

    /**
     * 获取签名
     * @param data
     * @param privateKey
     * @return
     * @throws Exception
     */
    byte[] generateSign(byte[] data, PrivateKey privateKey) throws Exception;

    /**
     * 验证签名
     * @param data
     * @param publicKey
     * @param sign
     * @return
     * @throws Exception
     */
    boolean verifySign(byte[] data, PublicKey publicKey, byte[] sign) throws Exception;

    /**
     * 验证签名
     * @param encrypt
     * @param data
     * @return
     * @throws Exception
     */
    boolean verifySign(EncryptKey encrypt, byte[] data) throws Exception;
}
