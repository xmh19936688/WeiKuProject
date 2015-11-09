package com.xmh.weiku.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xmh.weiku.R;

public class CoverActivity extends AppCompatActivity {

    private static final int ACTIVITY_SWITCH_DELAY_SECOND=1*1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cover);
    }

    @Override
    protected void onResume() {
        super.onResume();
        View view = new View(this);
        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(CoverActivity.this, MainActivity.class));
                overridePendingTransition(R.anim.anim_activity_left_in, R.anim.anim_activity_left_out);
            }
        }, ACTIVITY_SWITCH_DELAY_SECOND);
        finish();
    }
}
