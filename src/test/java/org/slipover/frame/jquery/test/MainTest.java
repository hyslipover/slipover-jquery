package org.slipover.frame.jquery.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.slipover.frame.jquery.constant.CharConstant;
import org.slipover.frame.jquery.extend.$Date;
import org.slipover.frame.jquery.extend.$Optional;
import org.slipover.frame.share.exception.ServerError;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import redis.clients.jedis.Jedis;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.slipover.frame.jquery.JQuery.$;

public class MainTest {

    public static void main(String[] args) {
        System.out.println($(new User("asd|fa",25)).toGetParam());
    }

}
