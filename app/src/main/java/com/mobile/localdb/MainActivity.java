package com.mobile.localdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mobile.localdb.adapter.TableAdapter;
import com.mobile.localdb.model.Cost;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTable();
    }

    public void onOpenAddNewLayout(View view) {
        Intent intent = new Intent(view.getContext(), AddNewActivity.class);
        startActivityForResult(intent, 0);
    }

    public void onOpeningTotal(View view) {
        Intent intent = new Intent(view.getContext(), TotalActivity.class);
        startActivityForResult(intent, 0);
    }

    private void initTable() {
        List<Cost> data = Arrays.asList(new Cost("testing cost", true, 45.20, 1), new Cost("test2", false, 6., 1));

        ListView table = (ListView) findViewById(R.id.dataTable);
        TableAdapter adapter = new TableAdapter(data, this);
        table.setAdapter(adapter);

        //adapter.notifyDataSetChanged();
    }
}
