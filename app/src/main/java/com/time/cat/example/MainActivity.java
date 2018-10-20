package com.time.cat.example;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.time.cat.demo.R;
import com.time.cat.demo.button.BurstLinkButton;
import com.time.cat.demo.indicator.PatternIndicatorView;
import com.time.cat.demo.lock.GestureLock;
import com.time.cat.demo.lock.GestureLockView;
import com.time.cat.demo.lock.MyStyleLockView;
import com.time.cat.demo.lock.NexusStyleLockView;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class MainActivity extends Activity {
    GestureLock gestureView;
    View head;
    View body;
    View tail;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        BurstLinkButton burstLinkButton = findViewById(R.id.stopButton);
        burstLinkButton.setOnActionListener(new BurstLinkButton.ActionListener() {
            @Override
            public void onActionDown() {

            }

            @Override
            public void onActionUp() {

            }

            @Override
            public void onGiveUpEnd() {

            }

            @Override
            public void onClick() {

            }

            @Override
            public void onLongClick() {

            }
        });


        gestureView = (GestureLock) findViewById(R.id.gesture_lock);
        gestureView.setAdapter(new GestureLock.GestureLockAdapter() {
            @Override
            public int getDepth() {
                return 3;
            }

            @Override
            public int[] getCorrectGestures() {
                return new int[]{0, 3, 6, 7, 8, 5, 2, 1, 4};
            }

            @Override
            public int getUnmatchedBoundary() {
                return 5;
            }

            @Override
            public int getBlockGapSize() {
                return 10;
            }

            @Override
            public GestureLockView getGestureLockViewInstance(Context context, int position) {
                if (position % 2 == 0) {
                    return new MyStyleLockView(context);
                } else {
                    return new NexusStyleLockView(context);
                }
            }
        });
        gestureView.setOnGestureEventListener(new GestureLock.OnGestureEventListener() {

            @Override
            public void onGestureEvent(boolean matched) {
                Toast.makeText(MainActivity.this, "Match:" + matched, Toast.LENGTH_SHORT).show();
                gestureView.clear();
            }

            @Override
            public void onUnmatchedExceedBoundary() {
                Toast.makeText(MainActivity.this, "输入5次错误!30秒后才能输入", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBlockSelected(int position) {
                Log.d("position", position + "");
            }

        });

        final LinearLayout linearLayout = findViewById(R.id.dream);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        head = View.inflate(this, R.layout.item_burst_link_head, null);
        linearLayout.addView(head);

        body = View.inflate(this, R.layout.item_burst_link_body, null);
        final PatternIndicatorView patternIndicatorView = body.findViewById(R.id.lock);
        patternIndicatorView.setFillColor(getResources().getColor(android.R.color.holo_blue_light))
                .setNormalColor(getResources().getColor(android.R.color.holo_green_light))
                .setHitColor(getResources().getColor(R.color.colorPrimaryDark))
                .setErrorColor(getResources().getColor(android.R.color.holo_red_light))
                .setLineWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f,
                        getResources().getDisplayMetrics()))
                .buildWithDefaultStyle();
        final List<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        patternIndicatorView.updateState(list, false);
        linearLayout.addView(body);

        tail = View.inflate(this, R.layout.item_burst_link_tail, null);
        linearLayout.addView(tail);

        Button button = tail.findViewById(R.id.add);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = linearLayout.getChildCount();
                View body = View.inflate(MainActivity.this,
                        R.layout.item_burst_link_body, null);
                final PatternIndicatorView patternIndicatorView = body.findViewById(R.id.lock);
                patternIndicatorView.setFillColor(getResources().getColor(android.R.color.holo_blue_light))
                        .setNormalColor(getResources().getColor(android.R.color.holo_green_light))
                        .setHitColor(getResources().getColor(R.color.colorPrimaryDark))
                        .setErrorColor(getResources().getColor(android.R.color.holo_red_light))
                        .setLineWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2f,
                                getResources().getDisplayMetrics()))
                        .buildWithDefaultStyle();
                final List<Integer> list = new ArrayList<>();
                list.add(0);
                list.add(1);
                list.add(2);
                list.add(3);
                list.add(4);
                list.add(5);
                list.add(6);
                list.add(7);
                list.add(8);
                patternIndicatorView.updateState(list, false);

                linearLayout.addView(body, count - 2);
            }
        });
        Button delete = tail.findViewById(R.id.delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = linearLayout.getChildCount();
                if (count <= 3) return;
                linearLayout.removeViewAt(count - 2);
            }
        });
    }
}
