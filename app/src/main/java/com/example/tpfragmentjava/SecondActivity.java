package com.example.tpfragmentjava;

import android.content.Intent;
import android.os.Bundle;

public class SecondActivity extends LogableActivity implements CategoryIdProvider{
    private String categoryId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // retrieve intent from caller activity
        Intent intent = getIntent();
        categoryId = intent.getStringExtra(MainActivity.KEY_CATEGORY_ID);
    }

    public String getCategoryId() {
        return categoryId;
    }

    @Override
    public String getId() {
        return categoryId;
    }
}