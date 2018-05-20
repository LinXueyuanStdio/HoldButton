package com.time.cat.demo;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class StopButtonRunnable implements Runnable {
    private final StopButton button;

    private StopButtonRunnable(StopButton stopButton) {
        this.button = stopButton;
    }

    public static Runnable a(StopButton stopButton) {
        return new StopButtonRunnable(stopButton);
    }

    @Override
    public void run() {
        StopButton.a(this.button);
    }
}