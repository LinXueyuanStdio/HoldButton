package com.time.cat.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription 外环
 * @usage null
 */
public class ProgressPie extends View {
    private Paint paint;
    private RectF rectF;
    private float angle;

    public ProgressPie(Context context) {
        this(context, null);
    }

    public ProgressPie(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ProgressPie(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.paint = new Paint();
        this.rectF = new RectF();
        this.angle = 0.0f;
        init(context);
    }

    public void setColor(@ColorInt int color) {
        this.paint.setColor(color);
        invalidate();
    }

    public void setSweepAngle(float angle) {
        this.angle = angle;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.rectF.right = (float) getMeasuredWidth();
        this.rectF.bottom = (float) getMeasuredHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawArc(this.rectF, 270.0f, this.angle, true, this.paint);
    }

    private void init(Context context) {
        this.paint.setColor(context.getResources().getColor(R.color.run_stop));
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
    }
}
