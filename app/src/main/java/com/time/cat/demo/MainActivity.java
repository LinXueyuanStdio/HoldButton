package com.time.cat.demo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

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
        stopButton.setOnEndListener(new StopButton.OnEndListener() {
            @Override
            public void onEnd() {
                Toast.makeText(MainActivity.this, "动画结束", Toast.LENGTH_SHORT).show();
            }
        });
        stopButton.setActionListener(new StopButton.ActionListener() {
            @Override
            public void onClick() {
                Toast.makeText(MainActivity.this, "OnClick", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void afterClick() {
                Toast.makeText(MainActivity.this, "afterClick", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
