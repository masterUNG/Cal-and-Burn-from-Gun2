package com.example.admin.calandburn;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Admin on 3/5/2559.
 */
public class HistoryAdapter extends BaseAdapter{

    private Context context;
    private String[] foodStrings, amountStrings, kcalStrings;

    public HistoryAdapter(Context context, String[] foodStrings, String[] amountStrings, String[] kcalStrings) {
        this.context = context;
        this.foodStrings = foodStrings;
        this.amountStrings = amountStrings;
        this.kcalStrings = kcalStrings;
    }

    @Override
    public int getCount() {
        return foodStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.history_listview, viewGroup, false);

        TextView foodTextView = (TextView) view1.findViewById(R.id.textView74);
        foodTextView.setText(foodStrings[i]);

        TextView amountTextView = (TextView) view1.findViewById(R.id.textView75);
        amountTextView.setText(amountStrings[i]);

        TextView kcalTextView = (TextView) view1.findViewById(R.id.textView76);
        kcalTextView.setText(kcalStrings[i]);

        return view1;
    }
}  // Main Class
