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

public class SocialMediaAdapter extends ArrayAdapter<SocialMediaList> {
    public SocialMediaAdapter(Context context, ArrayList<SocialMediaList> social) {
        super(context, 0, social);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        SocialMediaList currentSocial = getItem(position);

        TextView OSNameTextView = (TextView) listItemView.findViewById(R.id.name);
        ImageView imageView=(ImageView)listItemView.findViewById(R.id.image);

        imageView.setImageResource(currentSocial.getmImageResourceId());
        OSNameTextView.setText(currentSocial.getName());

        return listItemView;
    }
}
