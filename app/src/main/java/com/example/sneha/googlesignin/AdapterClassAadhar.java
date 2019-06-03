package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterClassAadhar extends RecyclerView.Adapter<AdapterClassAadhar.SearchViewHolder> {
    ArrayList<String> NameList;
    ArrayList<String> EmailNameList;
    Context context;
    DatabaseHelper3 helper;

    public AdapterClassAadhar(ArrayList<String> nameList, ArrayList<String> emailNameList, Context context) {

        NameList = nameList;
        EmailNameList = emailNameList;
        this.context = context;
    }

    AdapterClassAadhar() {

    }
    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView logo,edit,delete;
        TextView namee, agee,userdetails,title;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            namee = (TextView) itemView.findViewById(R.id.adapterHeading);
            agee = (TextView) itemView.findViewById(R.id.adapterName);
            userdetails=(TextView)itemView.findViewById(R.id.view);
            delete=(ImageView)itemView.findViewById(R.id.delete);
            edit=(ImageView)itemView.findViewById(R.id.edit);
            logo=(ImageView)itemView.findViewById(R.id.logo);
            title = (TextView) itemView.findViewById(R.id.title);

        }
    }

    @NonNull
    @Override
    public AdapterClassAadhar.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);

        return new AdapterClassAadhar.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClassAadhar.SearchViewHolder searchViewHolder, int i) {
        final int p=i;
        searchViewHolder.namee.setText(NameList.get(i));
        searchViewHolder.agee.setText(EmailNameList.get(i));
        final String namee = EmailNameList.get(i);

        searchViewHolder.title.setText("Aadhaar");
        searchViewHolder.logo.setImageResource(R.drawable.aadhaar);

        searchViewHolder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "HurrayDone", Toast.LENGTH_SHORT).show();

                Intent profileIntent = new Intent(context, DisplayAadharDetails.class);
                profileIntent.putExtra("aadharcardnumber", EmailNameList.get(p));
                context.startActivity(profileIntent);
            }
        });
        searchViewHolder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(context)
                        .setIcon(android.R.drawable.alert_light_frame)
                        .setTitle("Confirm Delete")
                        .setMessage("Do you really want to delete ?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                helper = new DatabaseHelper3 (context);
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("Aadhar_table","number =?",new String[]{EmailNameList.get(p)});

                                EmailNameList.remove(p);
                                NameList.remove(p);
                                notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });
        searchViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent profileIntent = new Intent(context, EditAadhar.class);
                profileIntent.putExtra("aadharcardnumber",EmailNameList.get(p));
                context.startActivity(profileIntent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return NameList.size();

    }
}
