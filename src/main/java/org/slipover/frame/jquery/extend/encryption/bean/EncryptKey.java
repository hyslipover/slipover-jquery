package org.slipover.frame.jquery.extend.encryption.bean;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class EncryptKey {

    private String key;
    /**
     * 公钥
     */
    private PublicKey publicKey;
    /**
     * 私钥
     */
    private PrivateKey privateKey;

    public PrivateKey setPrivateKey(String key, byte[] privateKey) throws Exception {
        return KeyFactory.getInstance(key).generatePrivate(new PKCS8EncodedKeySpec(privateKey));
    }

    public PublicKey setPublicKey(String key, byte[] publicKey) throws Exception {
        return KeyFactory.getInstance(key).generatePublic(new X509EncodedKeySpec(publicKey));
    }

    public String getKey() {
        return key;
    }

    public EncryptKey setKey(String key) {
        this.key = key;
        return this;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public EncryptKey setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
        return this;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public EncryptKey setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
        return this;
    }
}
