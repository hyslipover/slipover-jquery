package org.slipover.frame.jquery.extend.encryption;

import java.security.NoSuchAlgorithmException;

/**
 * 单向加密方式
 */
public interface UniDirectionEncryption {

    /**
     * 加密
     * @param data
     * @return
     */
    String encrypt(String data) throws NoSuchAlgorithmException;

}
