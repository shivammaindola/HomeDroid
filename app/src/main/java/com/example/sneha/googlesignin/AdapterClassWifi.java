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

public class AdapterClassWifi  extends RecyclerView.Adapter<AdapterClassWifi.SearchViewHolder> {
    ArrayList<String> NameList;
    ArrayList<String> EmailNameList;
    Context context;
    DatabaseHelper7 helper;

    public AdapterClassWifi(ArrayList<String> nameList, ArrayList<String> emailNameList, Context context) {

        NameList = nameList;
        EmailNameList = emailNameList;
        this.context = context;
    }

    AdapterClassWifi() {

    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView namee, agee,title,userdetails; ImageView logo,edit,delete;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            logo=itemView.findViewById(R.id.logo);
            title=itemView.findViewById(R.id.title);
            namee = (TextView) itemView.findViewById(R.id.adapterHeading);
            agee = (TextView) itemView.findViewById(R.id.adapterName);
            userdetails=(TextView)itemView.findViewById(R.id.view);
            delete=(ImageView)itemView.findViewById(R.id.delete);
            edit=(ImageView)itemView.findViewById(R.id.edit);
            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
            namee.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambriabols.ttf"));
            agee.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
            userdetails.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
        }

    }
    @NonNull
    @Override
    public AdapterClassWifi.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);

        return new AdapterClassWifi.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterClassWifi.SearchViewHolder searchViewHolder, int i) {
        final int p=i;
        searchViewHolder.namee.setText(NameList.get(i));
        searchViewHolder.agee.setText("");
        final String namee = NameList.get(i);
        searchViewHolder.title.setText("Wi-Fi");
        searchViewHolder.logo.setImageResource(R.drawable.wifipassword);

        searchViewHolder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "HurrayDone", Toast.LENGTH_SHORT).show();

                Intent profileIntent = new Intent(context, DisplayWifi.class);
                profileIntent.putExtra("wifiname", namee);
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
                                helper=new DatabaseHelper7(context);
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("wifi_table","name =? and pass =?",new String[]{NameList.get(p),EmailNameList.get(p)});
                                // db.delete("passport_table", "passport_num " + "= " + EmailNameList.get(p), null);
                                //   helper.delete(EmailNameList.get(p));

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

                Intent profileIntent = new Intent(context, EditWifi.class);
                profileIntent.putExtra("wifiname",NameList.get(p));
                context.startActivity(profileIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NameList.size();

    }
}
