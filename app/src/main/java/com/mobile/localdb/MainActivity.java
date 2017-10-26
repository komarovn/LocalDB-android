package com.mobile.localdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onOpenAddNewLayout(View view) {
        Intent intent = new Intent(view.getContext(), AddNewActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onOpeningTotal(View view) {
        Intent intent = new Intent(view.getContext(), TotalActivity.class);
        startActivityForResult(intent, 0);
    }
}
