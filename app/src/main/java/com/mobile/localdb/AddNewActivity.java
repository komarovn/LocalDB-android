package com.mobile.localdb;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
        newCost.setName(getName());
        newCost.setAsset(getAssetLiability());
        newCost.setAmount(getAmount());
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

    public String getName() {
        return ((EditText) findViewById(R.id.nameTextbox)).getText().toString();
    }

    public boolean getAssetLiability() {
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.assetLiabilityRadioGroup);
        int radioId = radioGroup.getCheckedRadioButtonId();
        return "Asset".equals(((RadioButton) radioGroup.findViewById(radioId)).getText().toString());
    }

    public double getAmount() {
        return Double.valueOf(((EditText) findViewById(R.id.amountTextBox)).getText().toString());
    }
}
