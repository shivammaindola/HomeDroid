package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.NameList;

import java.util.ArrayList;

public class AdapterClassDrivingLicense extends RecyclerView.Adapter<AdapterClassDrivingLicense.SearchViewHolder> {

    // Initializations....
    ArrayList<String> nameList;
    ArrayList<String> numList;
    Context context;
    DatabaseHelper12 database;
    static public String EDIT_DRIVING_CLICKED ="INDEX_DRIVING_LICENSE_EDIT_CLICKED";


    //Constructor...
    public AdapterClassDrivingLicense(ArrayList<String> nameList, ArrayList<String> numList, Context context){
        this.nameList = nameList;
        this.numList = numList;
        this.context = context;
    }

    // OnCreate: Inflating the layout holder layout to adapter....
    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);
        return new AdapterClassDrivingLicense.SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull AdapterClassDrivingLicense.SearchViewHolder holder, int position) {

        final int index = position;
        holder.title.setText("Driving \nLicense");
        holder.logo.setImageResource(R.drawable.driving_license_icon);
        holder.name.setText(numList.get(position));
        holder.head.setText(nameList.get(position));

        // Clicking View....
        holder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "HurrayDone", Toast.LENGTH_SHORT).show();
                Intent profileIntent = new Intent(context, DisplayDrivingLicense.class);
                profileIntent.putExtra(EDIT_DRIVING_CLICKED, numList.get(index));
                context.startActivity(profileIntent);
            }
        });

        // Clcking Delete icon....
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.alert_light_frame)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really want to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                database = new DatabaseHelper12(context);
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.delete(DatabaseHelper12.TABLE_NAME," license_number =?",new String[]{numList.get(index)});
                                numList.remove(index);
                                nameList.remove(index);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        // Clicking Edit icon.....
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(context, EditDrivingLicense.class);
                profileIntent.putExtra(EDIT_DRIVING_CLICKED, numList.get(index));
                context.startActivity(profileIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return nameList.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView head, userdetails,name,title;
        ImageView edit,delete,logo;

        public SearchViewHolder(View itemView) {
            super(itemView);

            // Initializations....
            head = (TextView) itemView.findViewById(R.id.adapterHeading);
            name = (TextView) itemView.findViewById(R.id.adapterName);
            userdetails = (TextView)itemView.findViewById(R.id.view);
            delete = (ImageView)itemView.findViewById(R.id.delete);
            edit = (ImageView)itemView.findViewById(R.id.edit);
            logo = (ImageView)itemView.findViewById(R.id.logo);
            title = (TextView) itemView.findViewById(R.id.title);

        }
    }
}
