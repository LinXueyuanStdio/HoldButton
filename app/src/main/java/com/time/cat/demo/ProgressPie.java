package com.time.cat.demo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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
    private float c;

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
        this.c = 0.0f;
        a(context);
    }

    public void setColor(int i) {
        this.paint.setColor(i);
        invalidate();
    }

    public void setSweepAngle(float f) {
        this.c = f;
        invalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.rectF.right = (float) getMeasuredWidth();
        this.rectF.bottom = (float) getMeasuredHeight();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawArc(this.rectF, 270.0f, this.c, true, this.paint);
    }

    private void a(Context context) {
        this.paint.setColor(context.getResources().getColor(R.color.run_stop));
        this.paint.setStyle(Paint.Style.FILL_AND_STROKE);
        this.paint.setAntiAlias(true);
        this.paint.setDither(true);
    }
}
