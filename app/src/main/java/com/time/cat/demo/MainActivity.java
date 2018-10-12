package com.time.cat.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @author dlink
 * @email linxy59@mail2.sysu.edu.cn
 * @date 2018/5/20
 * @discription null
 * @usage null
 */
public class MainActivity extends Activity {
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
    }
}
