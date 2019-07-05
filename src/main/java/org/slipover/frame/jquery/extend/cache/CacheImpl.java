package org.slipover.frame.jquery.extend.cache;

import org.slipover.frame.jquery.config.JQueryConfig;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;

public class CacheImpl {

    static Cache cache;

    static {
        switch (JQueryConfig.cacheType) {
            case LOCAL:
                cache = new ConcurrentMapCache("jqueryCache");
                break;
            case REDIS:
                break;
        }
    }

}
