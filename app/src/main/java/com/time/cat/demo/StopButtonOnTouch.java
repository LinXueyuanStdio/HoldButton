package com.time.cat.demo;

import android.view.MotionEvent;
import android.view.View;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class StopButtonOnTouch implements View.OnTouchListener {
    private final StopButton button;

    private StopButtonOnTouch(StopButton stopButton) {
        this.button = stopButton;
    }

    public static View.OnTouchListener a(StopButton stopButton) {
        return new StopButtonOnTouch(stopButton);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return StopButton.a(this.button, view, motionEvent);
    }
}
