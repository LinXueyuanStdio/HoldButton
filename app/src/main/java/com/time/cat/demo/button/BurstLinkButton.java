package com.time.cat.demo.button;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.time.cat.demo.R;
import com.time.cat.demo.util.DelayRunnableUtils;

import java.util.ArrayList;


/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription 主button
 * @usage null
 */
public class BurstLinkButton extends RelativeLayout implements ValueAnimator.AnimatorUpdateListener {

    public static final float DEFAULT_MIN_SCALE = 1.0f;

    private ActionListener onActionListener;
    View barBackground;
    View borderView;
    RelativeLayout buttonLayout;
    private AnimatorSet circleAnimSet;
    private AnimatorSet resetAnimSet;
    private AnimatorSet centerScalaAnimSet;
    private ValueAnimator circleAnim;
    private ValueAnimator centerAnim;
    private float curAngle = 0.0f;
    private boolean otherAnimRunning = false;
    private boolean onCenterAnimRunning = false;
    private boolean onCenterScalaAnimRunning = false;
    private boolean onActionUp = false;
    private boolean animRunning = false;
    private boolean onCircleAnimStart = false;
    float mTextSize;
    private DelayRunnableUtils delayRunnableUtils;
    ProgressPie progressPie;
    TextView textTitle;

    private Paint mPaint;

    private Paint mBackgroundPaint;

    private int mBackgroundColor = Color.parseColor("#b4282d");

    private int mShadowColor = Color.parseColor("#40000000");

    private int mDotColor = Color.WHITE;

    public interface ActionListener {

        void onActionDown();

        void onActionUp();

        void onGiveUpEnd();

        void onClick();

        void onLongClick();

    }

    public void setOnActionListener(ActionListener actionListenerVar) {
        onActionListener = actionListenerVar;
    }

    public BurstLinkButton(Context context) {
        this(context, null);
    }

    public BurstLinkButton(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BurstLinkButton(Context context, AttributeSet attributeSet, int defStyle) {
        super(context, attributeSet, defStyle);
        TypedArray ta = context.obtainStyledAttributes(attributeSet, R.styleable.BurstLinkButton);
        mBackgroundColor = ta.getColor(R.styleable.BurstLinkButton_background_color, Color.parseColor("#b4282d"));
        mShadowColor = ta.getColor(R.styleable.BurstLinkButton_shadow_color, Color.parseColor("#40000000"));
        mDotColor = ta.getColor(R.styleable.BurstLinkButton_dot_color, Color.WHITE);
        mTextSize = ta.getDimension(R.styleable.BurstLinkButton_text_size, context.getResources().getDimension(R.dimen.button_text_size));
        ta.recycle();

        initUtils();
        initView(context);
        initAnim(context);
        initPaint();
    }

    private void initUtils() {
        delayRunnableUtils = new DelayRunnableUtils(new Runnable() {
            @Override
            public void run() {
                reset();
            }
        }, 3000);
    }

    private void initView(Context context) {
        View root = LayoutInflater.from(context).inflate(R.layout.view_burst_link_button, this, true);
        progressPie = root.findViewById(R.id.pie_progress);
        textTitle = root.findViewById(R.id.text_title);
        buttonLayout = root.findViewById(R.id.layout_button);
        borderView = root.findViewById(R.id.view_border);
        barBackground = root.findViewById(R.id.bg_bar);
//        textTitle.setTextSize(mTextSize);
        root.findViewById(R.id.button_stop_click_area).setOnTouchListener(new OnTouchListener() {
            long curTime;

            @Override
            public boolean onTouch(View v, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    curTime = System.currentTimeMillis();
                    onActionStart();
                    return true;
                } else if (motionEvent.getAction() != MotionEvent.ACTION_UP) {
                    performClick();
                    return false;
                } else {
                    onActionUp();
                    long time = System.currentTimeMillis();
                    if (time - curTime < 100 && onActionListener != null) {
                        onActionListener.onClick();
                    }
                    if (onActionListener != null) {
                        onActionListener.onActionUp();
                    }
                    return true;
                }
            }
        });
    }

    private void initAnim(Context context) {
        circleAnimSet = new AnimatorSet();
        resetAnimSet = new AnimatorSet();
        centerScalaAnimSet = new AnimatorSet();
        float progress_size = context.getResources().getDimension(R.dimen.run_stop_button_progress_size) / context.getResources().getDimension(R.dimen.run_stop_button_size);
        float layout_shrink_size = context.getResources().getDimension(R.dimen.run_stop_button_layout_shrink_size) / context.getResources().getDimension(R.dimen.run_stop_button_layout_normal_size);
        AnimatorSet animatorSet = circleAnimSet;
        Animator[] animatorArr = new Animator[6];
        animatorArr[0] = ObjectAnimator.ofFloat(buttonLayout, View.SCALE_X.getName(), layout_shrink_size);
        animatorArr[1] = ObjectAnimator.ofFloat(buttonLayout, View.SCALE_Y.getName(), layout_shrink_size);
        animatorArr[2] = ObjectAnimator.ofFloat(barBackground, View.SCALE_X.getName(), progress_size);
        animatorArr[3] = ObjectAnimator.ofFloat(barBackground, View.SCALE_Y.getName(), progress_size);
        animatorArr[4] = ObjectAnimator.ofFloat(progressPie, View.SCALE_X.getName(), progress_size);
        animatorArr[5] = ObjectAnimator.ofFloat(progressPie, View.SCALE_Y.getName(), progress_size);
        animatorSet.playTogether(animatorArr);
        circleAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                onCircleAnimStart = true;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                circleHolding();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        circleAnimSet.setDuration(150);
        circleAnim = ValueAnimator.ofFloat(curAngle, 360.0f);
        circleAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = (Float) valueAnimator.getAnimatedValue();
                if (floatValue >= 90.0f) {
                    onCircleAnimStart = false;
                    if (!(!onActionUp || resetAnimSet.isStarted() || centerScalaAnimSet.isStarted())) {
                        giveUpAndReset();
                    }
                }
                setSweepAngle(floatValue);
            }
        });
        circleAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (centerAnim != null && centerAnim.isStarted()) {
                    centerAnim.cancel();
                }
                onCenterAnimRunning = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!onCenterAnimRunning && !centerScalaAnimSet.isStarted()) {
                    centerScalaAnimSet.start();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                onCenterAnimRunning = true;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        centerAnim = ValueAnimator.ofFloat(curAngle, 0.0f);
        centerAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setSweepAngle((Float) animation.getAnimatedValue());
            }
        });
        centerAnim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                if (circleAnim != null && circleAnim.isStarted()) {
                    circleAnim.cancel();
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
        AnimatorSet animatorSet2 = centerScalaAnimSet;
        Animator[] animators = new Animator[6];
        animators[0] = ObjectAnimator.ofFloat(buttonLayout, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animators[1] = ObjectAnimator.ofFloat(buttonLayout, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animators[2] = ObjectAnimator.ofFloat(barBackground, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animators[3] = ObjectAnimator.ofFloat(barBackground, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animators[4] = ObjectAnimator.ofFloat(progressPie, View.SCALE_X.getName(), DEFAULT_MIN_SCALE);
        animators[5] = ObjectAnimator.ofFloat(progressPie, View.SCALE_Y.getName(), DEFAULT_MIN_SCALE);
        animatorSet2.playTogether(animators);
        centerScalaAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                onCenterScalaAnimRunning = true;

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                textTitle.setText("停止");
                setSweepAngle(0.0f);
                borderView.setVisibility(View.INVISIBLE);
                if (onActionUp) {
                    onCenterScalaAnimRunning = false;
                }
                if (onActionListener != null) {
                    onActionListener.onLongClick();
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        centerScalaAnimSet.setDuration(150);
        ArrayList<Animator> childAnimations = centerScalaAnimSet.getChildAnimations();
        childAnimations.add(centerAnim);
        resetAnimSet.playTogether(childAnimations);
        resetAnimSet.setDuration(150);
        resetAnimSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                otherAnimRunning = false;

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (!otherAnimRunning) {
                    borderView.setVisibility(View.INVISIBLE);
                    if (!(onActionListener == null
                            || circleAnimSet.isStarted()
                            || centerScalaAnimSet.isStarted()
                            || circleAnim.isStarted())) {
                        onActionListener.onGiveUpEnd();
                    }
                }
                delayRunnableUtils.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                otherAnimRunning = true;

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void initPaint() {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(mDotColor);
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setAntiAlias(true);
        mBackgroundPaint.setDither(true);
        mBackgroundPaint.setColor(mBackgroundColor);
        mBackgroundPaint.setShadowLayer(15, 0, 0, mShadowColor);
    }

    public void onActionStart() {
        invalidate();
        if (circleAnimSet.isStarted() || onCircleAnimStart || centerScalaAnimSet.isStarted()) {
            animRunning = true;
            return;
        }
        if (onActionListener != null) {
            onActionListener.onActionDown();
        }
        onActionUp = false;
        textTitle.setText("长按");
        delayRunnableUtils.stop();
        borderView.setVisibility(View.VISIBLE);
        if (resetAnimSet != null && resetAnimSet.isStarted()) {
            resetAnimSet.cancel();
        }
        circleAnimSet.start();
    }

    public void onActionUp() {
        invalidate();
        onActionUp = true;
        if (onCenterScalaAnimRunning) {
            onCenterScalaAnimRunning = false;
        } else if (animRunning || circleAnimSet.isStarted() || onCircleAnimStart || centerScalaAnimSet.isStarted()) {
            animRunning = false;
        } else {
            giveUpAndReset();
        }
    }

    public void giveUpAndReset() {
        textTitle.setText("停止");
        centerAnim.setFloatValues(curAngle, 0.0f);
        resetAnimSet.start();
    }

    private void reset() {
        onCenterScalaAnimRunning = false;
        textTitle.setText("停止");
    }

    private void circleHolding() {
        circleAnim.setFloatValues(curAngle, 360.0f);
        circleAnim.setDuration((long) (((360.0f - curAngle) / 360.0f) * 850.0f));
        circleAnim.start();
    }

    @Override
    public void setBackgroundColor(int color) {
        mBackgroundColor = color;
        mBackgroundPaint.setColor(color);
        invalidate();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        invalidate();
    }

    public void setSweepAngle(float f) {
        curAngle = f;
        progressPie.setSweepAngle(curAngle);
    }

    public void setDotColor(int color) {
        mDotColor = color;
        mPaint.setColor(mDotColor);
        invalidate();
    }

    public Paint getBackgroundPaint() {
        return mBackgroundPaint;
    }

    public void setShadowColor(int color) {
        mShadowColor = color;
        mBackgroundPaint.setShadowLayer(15, 0, 0, color);
        invalidate();
    }
}


