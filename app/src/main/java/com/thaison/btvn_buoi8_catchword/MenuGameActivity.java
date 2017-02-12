package com.thaison.btvn_buoi8_catchword;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Shin on 7/23/2016.
 */
public class MenuGameActivity extends Activity implements View.OnClickListener{
    private TextView tvTitle;
    private ImageView ivPlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        initViews();
        setEvents();
    }

    private void initViews() {
        tvTitle = (TextView)findViewById(R.id.tv_title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Gabriola.ttf");
        tvTitle.setTypeface(typeface);
        ivPlay = (ImageView)findViewById(R.id.iv_play);
    }

    private void setEvents() {
        ivPlay.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, PlayGameActivity.class);
        startActivity(intent);
    }
}
