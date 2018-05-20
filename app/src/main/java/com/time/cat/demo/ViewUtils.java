package com.time.cat.demo;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemAnimator;
import android.support.v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class ViewUtils {
    public static View a(ViewGroup viewGroup, int i) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, false);
    }

    public static View a(ViewGroup viewGroup, int i, boolean z) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(i, viewGroup, z);
    }

    public static View a(Context context, int i) {
        return LayoutInflater.from(context).inflate(i, null);
    }

    public static void a(TextView textView, String str) {
        if (textView != null && !TextUtils.isEmpty(str)) {
            textView.setText(str);
        }
    }

    public static int a(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int b(Context context) {
        return (int) b(context, (float) a(context));
    }

    public static int c(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int a(Context context, float f) {
        if (context == null) {
            return 0;
        }
        return (int) ((context.getResources().getDisplayMetrics().density * f) + 0.5f);
    }

    public static int a(int i) {
        return (int) TypedValue.applyDimension(2, (float) i, Resources.getSystem().getDisplayMetrics());
    }

    public static int e(Context context) {
        int a = a(context);
        int c = c(context);
        return a < c ? a : c;
    }

    public static void a(View view, int i) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.width = a(view.getContext(), (float) i);
        view.setLayoutParams(layoutParams);
    }

    public static int f(Context context) {
        int identifier = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static float g(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static float h(Context context) {
        return g(context) / 2.0f;
    }

    public static boolean i(Context context) {
        return false;
    }

    public static void b(View view, int i) {
        Drawable a = ContextCompat.getDrawable(view.getContext(), i);
        Rect rect = new Rect();
        a.getPadding(rect);
        int paddingTop = view.getPaddingTop() + rect.top;
        int paddingLeft = view.getPaddingLeft() + rect.left;
        int paddingRight = view.getPaddingRight() + rect.right;
        int paddingBottom = rect.bottom + view.getPaddingBottom();
        view.setBackgroundResource(i);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public static float b(Context context, int i) {
        return context.getResources().getDimension(i);
    }

    public static int c(Context context, int i) {
        return context.getResources().getDimensionPixelSize(i);
    }

    public static boolean j(Context context) {
        return ((long) b(context)) <= 570;
    }

    public static boolean a(Activity activity) {
        return activity != null && c(activity) > 0 && a((Context) activity) - c(activity) != 0;
    }

    public static int b(Activity activity) {
        return c(activity) - a((Context) activity);
    }

    public static float b(Context context, float f) {
        if (context == null) {
            return 0.0f;
        }
        return f / g(context);
    }

    public static void a(RecyclerView recyclerView) {
        ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        }
    }

    public static void b(View view, long j, int i, long j2) {
        AnimatorSet animatorSet = new AnimatorSet();
        Animator ofFloat = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, (float) a(view.getContext(), (float) i));
        animatorSet.play(ofFloat).with(ObjectAnimator.ofFloat(view, View.ALPHA, 0.0f));
        animatorSet.setDuration(j);
        animatorSet.setStartDelay(j2);
        animatorSet.start();
    }

    public static int c(Activity activity) {
        int i = 0;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.heightPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static int d(Activity activity) {
        int i = 0;
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        try {
            Class.forName("android.view.Display").getMethod("getRealMetrics", DisplayMetrics.class).invoke(defaultDisplay, new Object[]{displayMetrics});
            return displayMetrics.widthPixels;
        } catch (Exception e) {
            e.printStackTrace();
            return i;
        }
    }

    public static Rect a(ImageView imageView) {
        if (imageView == null || imageView.getDrawable() == null || (imageView.getScaleType() != ImageView.ScaleType.CENTER_INSIDE && imageView.getScaleType() != ScaleType.CENTER)) {
            return null;
        }
        float f;
        int min;
        int i;
        int i2;
        Drawable drawable = imageView.getDrawable();
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        float f2 = intrinsicHeight != 0 ? ((float) intrinsicWidth) / ((float) intrinsicHeight) : 0.0f;
        int width = (imageView.getWidth() - imageView.getPaddingLeft()) - imageView.getPaddingRight();
        int height = (imageView.getHeight() - imageView.getPaddingTop()) - imageView.getPaddingBottom();
        if (height != 0) {
            f = ((float) width) / ((float) height);
        } else {
            f = 0.0f;
        }
        if (f2 > f) {
            min = Math.min(intrinsicWidth, width);
            i = f2 != 0.0f ? (int) (((float) min) / f2) : 0;
            i2 = min;
        } else {
            i2 = Math.min(intrinsicHeight, height);
            int i3 = i2;
            i2 = (int) (f2 * ((float) i2));
            i = i3;
        }
        i2 = (width - i2) / 2;
        min = (height - i) / 2;
        return new Rect(imageView.getPaddingLeft() + i2, imageView.getPaddingTop() + min, (width - i2) - imageView.getPaddingBottom(), (height - min) - imageView.getPaddingRight());
    }

    public static boolean a(float f, View view, int i) {
        boolean z = true;
        Rect rect = new Rect();
        view.getLocalVisibleRect(rect);
        if (rect.height() == 0 || rect.width() == 0) {
            return false;
        }
        if (1 == i) {
            if (rect.top != 0 && rect.bottom != view.getHeight()) {
                return false;
            }
            if (((float) rect.height()) < ((float) view.getHeight()) * f) {
                z = false;
            }
            return z;
        } else if (i != 0) {
            return false;
        } else {
            if (((float) rect.width()) < ((float) view.getWidth()) * f) {
                z = false;
            }
            return z;
        }
    }

    public static void a(final View view, final Runnable runnable) {
        view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                runnable.run();
            }
        });
    }
}