package com.time.cat.demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.time.cat.demo.button.StopButton;
import com.time.cat.demo.lock.GestureLock;
import com.time.cat.demo.lock.GestureLockView;
import com.time.cat.demo.lock.MyStyleLockView;
import com.time.cat.demo.lock.NexusStyleLockView;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class MainActivity extends Activity {
    GestureLock gestureView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        StopButton stopButton = findViewById(R.id.stopButton);
        stopButton.setOnActionListener(new StopButton.ActionListener() {
            @Override
            public void onActionDown() {

            }

            @Override
            public void onActionUp() {

            }

            @Override
            public void onEnd() {

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
                return 7;
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
    }
}
