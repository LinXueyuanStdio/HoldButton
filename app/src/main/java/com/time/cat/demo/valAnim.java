package com.time.cat.demo;

import android.animation.ValueAnimator;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class valAnim implements ValueAnimator.AnimatorUpdateListener {
    StopButton button;

    private valAnim(StopButton stopButton) {
        this.button = stopButton;
    }

    public static ValueAnimator.AnimatorUpdateListener getInstance(StopButton stopButton) {
        return new valAnim(stopButton);
    }

    @Override
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        StopButton.b(this.button, valueAnimator);
    }
}