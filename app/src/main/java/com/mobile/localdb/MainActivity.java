package com.mobile.localdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.mobile.localdb.adapter.TableAdapter;
import com.mobile.localdb.model.Cost;
import com.mobile.localdb.model.DatabaseOperations;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseOperations db;
    private List<Cost> data;
    private TableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.db = DatabaseOperations.getInstance();
        this.db.init(openOrCreateDatabase("cost_accounting", MODE_PRIVATE, null));

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

    public void onDeleteSelectedItems(View view) {
        for (Integer id : adapter.getSelected()) {
            db.removeCost(id);
        }
        adapter.clearSelected();
        updateData();
    }

    private void initTable() {
        db.createCost("testing", true, 45.20);
        db.createCost("test2", false, 6.);
        data = db.getData();

        ListView table = (ListView) findViewById(R.id.dataTable);
        adapter = new TableAdapter(data, this);
        table.setAdapter(adapter);
    }

    public void updateData() {
        data.clear();
        data.addAll(db.getData());
        adapter.notifyDataSetChanged();
    }
}
