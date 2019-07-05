package org.slipover.frame.jquery.extend.cache.redis;

import com.alibaba.fastjson.JSON;
import org.slipover.frame.jquery.config.JQueryConfig;
import org.slipover.frame.jquery.constant.CacheType;
import org.slipover.frame.share.code.CommonCode;
import org.slipover.frame.share.exception.ServerError;
import org.springframework.cache.support.AbstractValueAdaptingCache;
import org.springframework.web.server.ServerErrorException;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Objects;
import java.util.concurrent.Callable;

import static org.slipover.frame.jquery.JQuery.$;

public class RedisCache extends AbstractValueAdaptingCache {

    private static final JedisPoolConfig jedisPoolConfig;

    private static final JedisPool jedisPool;//非切片连接池

    private static final Jedis jedis;//非切片额客户端连接

//    private static final ShardedJedisPool shardedJedisPool;//切片连接池
//
//    private static final ShardedJedis shardedJedis;//切片额客户端连接

    static {
        if (CacheType.REDIS.equals(JQueryConfig.cacheType)) {
            jedisPoolConfig = new JedisPoolConfig();
            jedisPoolConfig.setMaxTotal(JQueryConfig.cacheMaxTotal);
            jedisPoolConfig.setMaxIdle(JQueryConfig.cacheMaxIdle);
            jedisPoolConfig.setMaxWaitMillis(JQueryConfig.cacheMaxWaitMillis);
            jedisPoolConfig.setTestOnBorrow(false);
            if (Objects.nonNull(JQueryConfig.cacheHost) && Objects.nonNull(JQueryConfig.cachePort)) {
                jedisPool = new JedisPool(jedisPoolConfig, JQueryConfig.cacheHost, JQueryConfig.cachePort);
            } else if (Objects.nonNull(JQueryConfig.cacheHost)) {
                jedisPool = new JedisPool(jedisPoolConfig, JQueryConfig.cacheHost);
            } else {
                jedisPool = new JedisPool(jedisPoolConfig);
            }
            jedis = jedisPool.getResource();
        } else {
            jedisPoolConfig = null;
            jedisPool = null;
            jedis = null;
        }
    }

    public RedisCache() {
        this(false);
    }

    /**
     * Create an {@code AbstractValueAdaptingCache} with the given setting.
     *
     * @param allowNullValues whether to allow for {@code null} values
     */
    private RedisCache(boolean allowNullValues) {
        super(allowNullValues);
    }

    @Override
    protected Object lookup(Object key) {
        examineCacheType();
        return jedis.get(examineKey(key));
    }

    @Override
    public String getName() {
        return "redisCache";
    }

    @Override
    public Object getNativeCache() {
        examineCacheType();
        return jedis;
    }

    @Override
    public <T> T get(Object key, Callable<T> valueLoader) {
        examineCacheType();
        return null;
    }

    @Override
    public void put(Object key, Object value) {
        examineCacheType();
        jedis.set(examineKey(key), examineValue(value));
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        examineCacheType();
        String keyString = examineKey(key);
        jedis.setnx(keyString, examineValue(value));
        return () -> jedis.get(keyString);
    }

    @Override
    public void evict(Object key) {
        examineCacheType();
        jedis.type(examineKey(key));
    }

    @Override
    public void clear() {
        examineCacheType();
        jedis.flushDB();
    }

    private void examineCacheType() {
        if (!CacheType.REDIS.equals(JQueryConfig.cacheType)) {
            throw new ServerError(CommonCode.ERROR, "请将 org.slipover.frame.jquery.config.JQueryConfig.cacheType 配置成 REDIS");
        }
    }

    private String examineKey(Object key) {
        return Objects.requireNonNull(convertString(key), "key 不能为空");
    }

    private String examineValue(Object value) {
        return isAllowNullValues() ? convertString(value) : Objects.requireNonNull(convertString(value), "value 不能为空");
    }

    private String convertString(Object obj){
        if (obj == null) {
            return null;
        }
        if (obj instanceof String) {
            return obj.toString();
        }
        if (obj instanceof Serializable) {
            return $.toSerializable((Serializable) obj);
        }
        return JSON.toJSONString(obj);
    }

}
