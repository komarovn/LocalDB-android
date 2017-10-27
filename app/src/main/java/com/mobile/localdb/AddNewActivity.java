package com.mobile.localdb;

import android.content.Intent;
import android.databinding.InverseBindingAdapter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.mobile.localdb.model.Cost;
import com.mobile.localdb.model.DatabaseOperations;

public class AddNewActivity extends AppCompatActivity {
    private DatabaseOperations db = DatabaseOperations.getInstance();
    private Cost newCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_layout);
        this.newCost = new Cost();
        this.newCost.setVersion(1);
    }

    public void onAddingNew(View view) {
        db.createCost(newCost);
        closePanel();
    }

    public void onCancelingAddNew(View view) {
        closePanel();
    }

    public void closePanel() {
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    //@InverseBindingAdapter(attribute = "android:text")
    public void setName(EditText view) {
        newCost.setName(view.getText().toString());
    }

    public void setAssetLiability(boolean isAsset) {
        newCost.setAsset(isAsset);
    }

    public void setAmount(double amount) {
        newCost.setAmount(amount);
    }
}
