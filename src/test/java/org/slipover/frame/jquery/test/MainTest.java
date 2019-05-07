package org.slipover.frame.jquery.test;

import org.slipover.frame.jquery.constant.CharConstant;
import org.slipover.frame.jquery.extend.$Date;
import org.slipover.frame.share.exception.ServerError;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
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
        System.out.println("执行开始-------------------->");

        for (int i = 0; i < 50; i++) {
            final String task = "task_" + i;
            $.async(() -> {
                $.tryCatch(() -> {
                    System.out.println(Thread.currentThread().getName() + "开始执行：" + task);
                    Thread.sleep(100L);
                    System.out.println(Thread.currentThread().getName() + "执行结束：" + task);
                    System.out.println(Thread.currentThread().getName() + " 执行任务：" + task);
                }).orCatch(e -> System.out.println(Thread.currentThread().getName() + " 执行任务异常，e = " + e));
            });
        }

        $.tryCatch(() -> Thread.sleep(1000L));

        System.out.println("执行结束<--------------------");
    }

}
