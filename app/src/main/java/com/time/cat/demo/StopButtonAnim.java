package com.time.cat.demo;

import android.animation.ValueAnimator;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class StopButtonAnim implements ValueAnimator.AnimatorUpdateListener {
    private final StopButton button;

    private StopButtonAnim(StopButton stopButton) {
        this.button = stopButton;
    }

    public static ValueAnimator.AnimatorUpdateListener a(StopButton stopButton) {
        return new StopButtonAnim(stopButton);
    }

    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        this.button.setSweepAngle((Float) valueAnimator.getAnimatedValue());
    }
}