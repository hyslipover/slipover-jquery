package org.slipover.frame.jquery.extend.encryption.impl;

import org.slipover.frame.jquery.extend.encryption.AsymmetryEncryption;
import org.slipover.frame.jquery.extend.encryption.bean.EncryptKey;

import javax.crypto.interfaces.DHPublicKey;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/*************************************************************************************************************************************************
 DH，全称为“Diffie-Hellman”，他是一种确保共享KEY安全穿越不安全网络的方法，也就是常说的密钥一致协议。
 由公开密钥密码体制的奠基人Diffie和Hellman所提出的一种思想。简单的说就是允许两名用户在公开媒体上交换信息以生成“一致”的、可以共享的密钥。
 也就是由甲方产出一对密钥（公钥、私钥），乙方依照甲方公钥产生乙方密钥对（公钥、私钥）。
 以此为基线，作为数据传输保密基础，同时双方使用同一种对称加密算法构建本地密钥（SecretKey）对数据加密。
 这样，在互通了本地密钥（SecretKey）算法后，甲乙双方公开自己的公钥，使用对方的公钥和刚才产生的私钥加密数据，同时可以使用对方的公钥和自己的私钥对数据解密。
 不单单是甲乙双方两方，可以扩展为多方共享数据通讯，这样就完成了网络交互数据的安全通讯！
 *************************************************************************************************************************************************/
public class DhEncryptionImpl implements AsymmetryEncryption {

    private static final String KEY = "DH";

    private static final int LENGTH = 512;

    @Override
    public EncryptKey generateKey() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(LENGTH);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public EncryptKey generateKey(int length) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(length);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public EncryptKey generateKey(Key key) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY);
        keyPairGenerator.initialize(((DHPublicKey) key).getParams());
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return new EncryptKey().setKey(KEY).setPublicKey(keyPair.getPublic()).setPrivateKey(keyPair.getPrivate());
    }

    @Override
    public PublicKey getPublicKey(byte[] key) throws Exception {
        return KeyFactory.getInstance(KEY).generatePublic(new X509EncodedKeySpec(key));
    }

    @Override
    public PrivateKey getPrivateKey(byte[] key) throws Exception {
        return KeyFactory.getInstance(KEY).generatePrivate(new PKCS8EncodedKeySpec(key));
    }

    @Override
    public byte[] generateSign(byte[] data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initSign(privateKey);
        signature.update(data);
        return signature.sign();
    }

    @Override
    public boolean verifySign(byte[] data, PublicKey publicKey, byte[] sign) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(publicKey);
        signature.update(data);
        return signature.verify(sign);
    }

    @Override
    public boolean verifySign(EncryptKey encrypt, byte[] data) throws Exception {
        Signature signature = Signature.getInstance("MD5withRSA");
        signature.initVerify(encrypt.getPublicKey());
        signature.initSign(encrypt.getPrivateKey());
        signature.update(data);
        return signature.verify(signature.sign());
    }
}
