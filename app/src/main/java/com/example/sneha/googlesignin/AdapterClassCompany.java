package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterClassCompany extends RecyclerView.Adapter<AdapterClassCompany.SearchViewHolder> {

    ArrayList<String> ventureNameList;
    Context context;
    static final String KEY_INTENT_COMPANY = "intent_key_for_company";
    DatabaseHelper13 database;

    public AdapterClassCompany(ArrayList<String> ventureNameList, Context context){
        this.ventureNameList = ventureNameList;
        this.context = context;
    }

    @Override
    public AdapterClassCompany.SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layoutholder, parent, false);
        return new AdapterClassCompany.SearchViewHolder(view);
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

    @Override
    public void onBindViewHolder(AdapterClassCompany.SearchViewHolder holder, int i) {

        final int p = i;
        final String name = ventureNameList.get(i);
        holder.logo.setImageResource(R.drawable.compony_profile);
        holder.title.setText("Company Profile");
        holder.head.setText(ventureNameList.get(i));
        holder.name.setVisibility(View.INVISIBLE);

        holder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DisplayCompanyProfile.class);
                intent.putExtra(KEY_INTENT_COMPANY, name);
                context.startActivity(intent);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditCompanyProfile.class);
                intent.putExtra(AdapterClassCompany.KEY_INTENT_COMPANY, name);
                context.startActivity(intent);
            }
        });

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
                                database = new DatabaseHelper13(context);
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.delete(DatabaseHelper13.TABLE_NAME," NameVenture =?",new String[]{ventureNameList.get(p)});
                                ventureNameList.remove(p);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

            }
        });


    }


    @Override
    public int getItemCount() {
        return ventureNameList.size();
    }


}
