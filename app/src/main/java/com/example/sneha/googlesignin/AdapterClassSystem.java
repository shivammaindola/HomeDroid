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

import java.util.ArrayList;

public class AdapterClassSystem  extends RecyclerView.Adapter<AdapterClassSystem.SearchViewHolder> {
    ArrayList<String> Name;
    ArrayList<String> Username;
    Context context;
    private DatabaseSystem helper;
    public AdapterClassSystem(ArrayList<String> nameList, ArrayList<String> emailNameList, Context context) {
        Name= nameList;
        Username= emailNameList;
        this.context = context;
    }


    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView userdetails,namee, agee,title;
        ImageView edit,delete,logo;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            logo=(ImageView)itemView.findViewById(R.id.logo);
            title=(TextView)itemView.findViewById(R.id.title);
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
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);

        return new AdapterClassSystem.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        final int p=i;
        searchViewHolder.title.setText("System Login");
        searchViewHolder.namee.setText(Name.get(i));
        searchViewHolder.agee.setText(Username.get(i));
        searchViewHolder.logo.setImageResource(R.drawable.system);
        final String name = Name.get(i);
        final String username = Username.get(i);

        searchViewHolder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent profileIntent = new Intent(context, DisplaySystemDetail.class);
                profileIntent.putExtra("systemname", name);
                profileIntent.putExtra("username", username);
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
                                helper=new DatabaseSystem(context);
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("system_table","name =? and username =?",new String[]{Name.get(p),Username.get(p)});
                                Name.remove(p);
                                Username.remove(p);
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

                Intent profileIntent = new Intent(context, EditSystemLogins.class);
                profileIntent.putExtra("systemname",Name.get(p));
                profileIntent.putExtra("username",Username.get(p));
                context.startActivity(profileIntent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return Name.size();

    }
}
