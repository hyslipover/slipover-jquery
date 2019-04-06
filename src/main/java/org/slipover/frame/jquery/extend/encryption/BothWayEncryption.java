package org.slipover.frame.jquery.extend.encryption;

/**
 * 双向加密
 */
public interface BothWayEncryption {

    /**
     * 加密
     * @param data
     * @return
     */
    String encrypt(String data);

    /**
     * 解密
     * @param data
     * @return
     */
    String decrypt(String data);
}
