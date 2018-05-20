package com.time.cat.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Collection;


/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription 主button
 * @usage null
 */
public class StopButton extends RelativeLayout {

    public static final float DEFAULT_MIN_SCALE = 1.0f;

    private OnEndListener onEndListener;
    private ActionListener ActionListener;
    View barBackground;
    View borderView;
    RelativeLayout buttonLayout;
    private AnimatorSet c;
    private AnimatorSet d;
    private AnimatorSet e;
    private ValueAnimator f;
    private ValueAnimator g;
    private float h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private DelayRunnableUtils o;
    ProgressPie progressPie;
    TextView textTitle;

    public interface ActionListener {
        void onClick();

        void afterClick();
    }

    public interface OnEndListener {
        void onEnd();
    }

    public void setOnEndListener(OnEndListener onEndListenerVar) {
        this.onEndListener = onEndListenerVar;
    }

    public void setActionListener(ActionListener actionListenerVar) {
        this.ActionListener = actionListenerVar;
    }

    public StopButton(Context context) {
        this(context, null);
    }

    public StopButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public StopButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new AnimatorSet();
        this.d = new AnimatorSet();
        this.e = new AnimatorSet();
        this.h = 0.0f;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = false;
        a(context);
    }

    private void a(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.view_stop_button, this, true);
        progressPie = inflate.findViewById(R.id.pie_progress);
        textTitle = inflate.findViewById(R.id.text_title);
        buttonLayout = inflate.findViewById(R.id.layout_button);
        borderView = inflate.findViewById(R.id.view_border);
        barBackground = inflate.findViewById(R.id.bg_bar);
        inflate.findViewById(R.id.button_stop_click_area).setOnTouchListener(StopButtonOnTouch.a(this));
        this.o = new DelayRunnableUtils(StopButtonRunnable.a(this), 3000);
        b(context);
    }

    static /* synthetic */ boolean a(StopButton stopButton, View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            stopButton.a();
            return true;
        } else if (motionEvent.getAction() != 1) {
            return false;
        } else {
            stopButton.b();
            return true;
        }
    }

    static /* synthetic */ void a(StopButton stopButton) {
        stopButton.k = false;
        stopButton.textTitle.setText("停止");
    }

    private void b(Context context) {
        float b = ViewUtils.b(context, (int) R.dimen.run_stop_button_progress_size) / ViewUtils.b(context, (int) R.dimen.run_stop_button_size);
        float b2 = ViewUtils.b(context, (int) R.dimen.run_stop_button_layout_shrink_size) / ViewUtils.b(context, (int) R.dimen.run_stop_button_layout_normal_size);
        AnimatorSet animatorSet = this.c;
        Animator[] animatorArr = new Animator[6];
        animatorArr[0] = ObjectAnimator.ofFloat(this.buttonLayout, View.SCALE_X.getName(), b2);
        animatorArr[1] = ObjectAnimator.ofFloat(this.buttonLayout, View.SCALE_Y.getName(), b2);
        animatorArr[2] = ObjectAnimator.ofFloat(this.barBackground, View.SCALE_X.getName(), b);
        animatorArr[3] = ObjectAnimator.ofFloat(this.barBackground, View.SCALE_Y.getName(), b);
        animatorArr[4] = ObjectAnimator.ofFloat(this.progressPie, View.SCALE_X.getName(), b);
        animatorArr[5] = ObjectAnimator.ofFloat(this.progressPie, View.SCALE_Y.getName(), b);
        animatorSet.playTogether(animatorArr);
        this.c.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                n = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                d();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        this.c.setDuration(150);
        this.f = ValueAnimator.ofFloat(this.h, 360.0f);
        this.f.addUpdateListener(valAnim.getInstance(this));
        this.f.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (g != null && g.isStarted()) {
                    g.cancel();
                }
                j = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!j && !e.isStarted()) {
                    e.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                j = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        this.g = ValueAnimator.ofFloat(this.h, 0.0f);
        this.g.addUpdateListener(StopButtonAnim.a(this));
        this.g.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (f != null && f.isStarted()) {
                    f.cancel();
                }
            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        AnimatorSet animatorSet2 = this.e;
        Animator[] animatorArr2 = new Animator[6];
        animatorArr2[0] = ObjectAnimator.ofFloat(this.buttonLayout, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animatorArr2[1] = ObjectAnimator.ofFloat(this.buttonLayout, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animatorArr2[2] = ObjectAnimator.ofFloat(this.barBackground, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animatorArr2[3] = ObjectAnimator.ofFloat(this.barBackground, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animatorArr2[4] = ObjectAnimator.ofFloat(this.progressPie, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animatorArr2[5] = ObjectAnimator.ofFloat(this.progressPie, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animatorSet2.playTogether(animatorArr2);
        this.e.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                k = true;

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                textTitle.setText("停止");
                setSweepAngle(0.0f);
                borderView.setVisibility(View.INVISIBLE);
                if (l) {
                    k = false;
                }
                if (onEndListener != null) {
                    onEndListener.onEnd();
                }
                if (ActionListener != null) {
                    ActionListener.afterClick();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        this.e.setDuration(150);
        Collection childAnimations = this.e.getChildAnimations();
        childAnimations.add(this.g);
        this.d.playTogether(childAnimations);
        this.d.setDuration(150);
        this.d.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                i = false;

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!i) {
                    borderView.setVisibility(View.INVISIBLE);
                    if (!(ActionListener == null || c.isStarted() || e.isStarted() || f.isStarted())) {
                        ActionListener.afterClick();
                    }
                }
                o.a();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                i = true;

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    static /* synthetic */ void b(StopButton stopButton, ValueAnimator valueAnimator) {
        float floatValue = (Float) valueAnimator.getAnimatedValue();
        if (floatValue >= 90.0f) {
            stopButton.n = false;
            if (!(!stopButton.l || stopButton.d.isStarted() || stopButton.e.isStarted())) {
                stopButton.c();
            }
        }
        stopButton.setSweepAngle(floatValue);
    }

    private void a() {
        if (this.c.isStarted() || this.n || this.e.isStarted()) {
            this.m = true;
            return;
        }
        if (this.ActionListener != null) {
            this.ActionListener.onClick();
        }
        this.l = false;
        this.textTitle.setText("长按");
        this.o.b();
        this.borderView.setVisibility(View.VISIBLE);
        if (this.d != null && this.d.isStarted()) {
            this.d.cancel();
        }
        this.c.start();
    }

    private void b() {
        this.l = true;
        if (this.k) {
            this.k = false;
        } else if (this.m || this.c.isStarted() || this.n || this.e.isStarted()) {
            this.m = false;
        } else {
            c();
        }
    }

    private void c() {
        this.g.setFloatValues(this.h, 0.0f);
        this.d.start();
    }

    private void d() {
        this.f.setFloatValues(this.h, 360.0f);
        this.f.setDuration((long) (((360.0f - this.h) / 360.0f) * 850.0f));
        this.f.start();
    }

    public void setSweepAngle(float f) {
        this.h = f;
        this.progressPie.setSweepAngle(this.h);
    }
}


