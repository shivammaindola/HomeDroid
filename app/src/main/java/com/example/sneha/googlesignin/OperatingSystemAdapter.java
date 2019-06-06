package com.example.sneha.googlesignin;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class OperatingSystemAdapter extends ArrayAdapter<OperatingSystemList> {

    public OperatingSystemAdapter(Context context, ArrayList<OperatingSystemList> osList) {
        super(context, 0, osList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        OperatingSystemList currentOS = getItem(position);

        TextView OSNameTextView = (TextView) listItemView.findViewById(R.id.name);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.image);

        imageView.setImageResource(currentOS.getmImageResourceId());
        OSNameTextView.setText(currentOS.getName());

        return listItemView;
    }
}
