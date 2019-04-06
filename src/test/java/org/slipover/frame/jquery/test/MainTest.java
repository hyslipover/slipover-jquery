package org.slipover.frame.jquery.test;

import java.security.NoSuchAlgorithmException;

import static org.slipover.frame.jquery.JQuery.$;

public class MainTest {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println($.BASE64.decrypt($.BASE64.encrypt("ALita")));
    }

}
