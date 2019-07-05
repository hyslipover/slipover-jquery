package org.slipover.frame.jquery.config;

import org.slipover.frame.jquery.constant.CacheType;

import java.io.Serializable;

public final class JQueryConfig implements Serializable {

    public static CacheType cacheType = CacheType.LOCAL;

    public static String cacheHost = "localhost";

    public static Integer cachePort = 6379;

    public static Integer cacheMaxTotal = 5;

    public static Integer cacheMaxIdle = 1;

    public static Long cacheMaxWaitMillis = 1000L;

}
