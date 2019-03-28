package org.slipover.frame.jquery.test;


import org.springframework.http.MediaType;

import static org.slipover.frame.jquery.JQuery.$;

public class MainTest {

    public static void main(String[] args) {
        System.out.println($.get("http://his-member-service-qa4.guahao-test.com/member/package/info/150798805128880128", httpHeaders -> {
            httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }));
    }

}
