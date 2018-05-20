package com.time.cat.demo;

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
    private final long aLong;
    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
    private ScheduledFuture<?> scheduledFuture;
    private final Handler e = new Handler();
    private boolean f;

    public void a(Runnable runnable) {
        this.runnable = runnable;
    }

    public DelayRunnableUtils(Runnable runnable, long j) {
        this.runnable = runnable;
        this.aLong = j;
    }

    public void a() {
        b();
        this.scheduledFuture = this.scheduledExecutorService.schedule(new Runnable() {
            @Override
            public void run() {
                if (!f && !scheduledFuture.isCancelled()) {
                    e.post(runnable);
                }
            }
        }, this.aLong, TimeUnit.MILLISECONDS);
        this.f = false;
    }

    public void b() {
        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(true);
        }
        this.f = true;
    }
}
