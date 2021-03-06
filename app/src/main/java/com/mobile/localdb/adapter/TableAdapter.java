package com.mobile.localdb.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.mobile.localdb.R;
import com.mobile.localdb.model.Cost;

import java.util.ArrayList;
import java.util.List;

public class TableAdapter extends BaseAdapter {
    private List<Cost> data = new ArrayList<Cost>();
    private Activity owner;

    private List<Integer> selected = new ArrayList<Integer>();

    public TableAdapter(List<Cost> data, Activity owner) {
        super();
        this.data = data;
        this.owner = owner;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Integer> getSelected() {
        return selected;
    }

    public void clearSelected() {
        selected.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CostHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = owner.getLayoutInflater();
            convertView = inflater.inflate(R.layout.table_row, null);
            holder = new CostHolder();
            holder.setSelection((CheckBox) convertView.findViewById(R.id.rowCheckBox));
            holder.setName((TextView) convertView.findViewById(R.id.nameValueTextView));
            holder.setAssetLiability((TextView) convertView.findViewById(R.id.assetLiabilityValueTextView));
            holder.setAmount((TextView) convertView.findViewById(R.id.amountValueTextView));
            convertView.setTag(holder);
        } else {
            holder = (CostHolder) convertView.getTag();
        }

        Cost item = data.get(position);
        holder.getSelection().setTag(item.getId());
        setSelectionListener(holder.getSelection());
        holder.getName().setText(item.getName());
        holder.getAssetLiability().setText(item.isAsset() ? "Asset" : "Liability");
        holder.getAmount().setText(String.valueOf(item.getAmount()) + " $");

        return convertView;
    }

    private void setSelectionListener(final CheckBox selection) {
        selection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer item = (Integer) selection.getTag();
                if (selection.isChecked()) {
                    selected.add(item);
                } else {
                    selected.remove((Object) item);
                }
            }
        });
    }
}
