package com.time.cat.demo.util;

import android.os.Handler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class DelayRunnableUtils {
    private Runnable runnable;
    private final long delayTime;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;
    private final Handler handler = new Handler();
    private boolean isStop;

    public DelayRunnableUtils(Runnable runnable, long delayTime) {
        this.runnable = runnable;
        this.delayTime = delayTime;
    }

    public void start() {
        stop();
        scheduledFuture = scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                if (!isStop && !scheduledFuture.isCancelled()) {
                    handler.post(runnable);
                }
            }
        }, delayTime, TimeUnit.MILLISECONDS);
        isStop = false;
    }

    public void stop() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        isStop = true;
    }
}
