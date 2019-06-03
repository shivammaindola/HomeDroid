package com.example.sneha.googlesignin;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BankListAdapter extends ArrayAdapter<BankList> {
    public BankListAdapter(Context context, ArrayList<BankList> banklist) {
        super(context, 0, banklist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        BankList currentBank = getItem(position);
        TextView bankNameTextView = (TextView) listItemView.findViewById(R.id.name);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.image);
        imageView.setImageResource(currentBank.getmImageResourceId());
        bankNameTextView.setText(currentBank.getName());

        return listItemView;
    }
}
