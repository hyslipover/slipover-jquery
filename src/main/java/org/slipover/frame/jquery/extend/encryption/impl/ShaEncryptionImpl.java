package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.UniDirectionEncryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*单向加密
 ***********************************************************************************************************************
 SHA(Secure Hash Algorithm，安全散列算法），数字签名等密码学应用中重要的工具，被广泛地应用于电子商务等信息安全领域。虽然，SHA与MD5通
 过碰撞法都被破解了，但是SHA仍然是公认的安全加密算法，较之MD5更为安全。
 因为二者均由MD4导出，SHA-1和MD5彼此很相似。相应的，他们的强度和其他特性也是相似，但还有以下几点不同：
 l  对强行攻击的安全性：最显著和最重要的区别是SHA-1摘要比MD5摘要长32 位。使用强行技术，产生任何一个报文使其摘要等于给定报摘要的难度对
    MD5是2^128数量级的操作，而对SHA-1则是2^160数量级的操作。这样，SHA-1对强行攻击有更大的强度。
 2  对密码分析的安全性：由于MD5的设计，易受密码分析的攻击，SHA-1显得不易受这样的攻击。
 3  速度：在相同的硬件上，SHA-1的运行速度比MD5慢。
 ***********************************************************************************************************************/
public class ShaEncryptionImpl implements UniDirectionEncryption{

    private static final String KEY = "SHA";

    private static final int LENGTH = 32;

    @Override
    public String encrypt(String data) throws NoSuchAlgorithmException{
        MessageDigest messageDigest = MessageDigest.getInstance(KEY);
        messageDigest.update(data.getBytes());
        return new BigInteger(messageDigest.digest()).toString(LENGTH);
    }

}
