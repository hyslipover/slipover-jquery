package org.slipover.frame.jquery.tool;

import org.slipover.frame.jquery.extend.encryption.impl.AesEncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.Base64EncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.DESedeEncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.DesEncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.HmacEncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.Md5EncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.PbeEncryptionImpl;
import org.slipover.frame.jquery.extend.encryption.impl.ShaEncryptionImpl;

/**
 * 加密工具
 */
public interface EncryptionTool {

    /**
     * 单向加密
     */
    Md5EncryptionImpl MD5 = new Md5EncryptionImpl();

    ShaEncryptionImpl SHA = new ShaEncryptionImpl();

    /**
     * 双向加密
     */
    Base64EncryptionImpl BASE64 = new Base64EncryptionImpl();

    /**
     * 对称加密
     */
    HmacEncryptionImpl HMAC = new HmacEncryptionImpl();

    DesEncryptionImpl DES = new DesEncryptionImpl();

    DESedeEncryptionImpl DESede = new DESedeEncryptionImpl();

    AesEncryptionImpl AES = new AesEncryptionImpl();

    PbeEncryptionImpl PBE = new PbeEncryptionImpl();

    /**
     * 非对称加密
     */

}
