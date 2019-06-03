package com.example.sneha.googlesignin;

import android.app.AlertDialog;
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
import android.content.Context;

import java.util.ArrayList;

public class AdapterClassVoter extends  RecyclerView.Adapter<AdapterClassVoter.SearchViewHolder>{
    ArrayList<String> NameList;
    ArrayList<String> NUMLIST;
    Context context;
    DatabaseHelper1 helper;


    public AdapterClassVoter(ArrayList<String> NUMLIST,ArrayList<String> NameList,Context context){

        this.NameList=NameList;
        this.NUMLIST=NUMLIST;
        this.context=context;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView namee, userdetails,agee,title;
        ImageView edit,delete,logo;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            namee = (TextView) itemView.findViewById(R.id.adapterHeading);
            agee = (TextView) itemView.findViewById(R.id.adapterName);
            userdetails=(TextView)itemView.findViewById(R.id.view);
            delete=(ImageView)itemView.findViewById(R.id.delete);
            edit=(ImageView)itemView.findViewById(R.id.edit);
            logo=(ImageView)itemView.findViewById(R.id.logo);
            title = (TextView) itemView.findViewById(R.id.title);
            title.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
            namee.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambriabols.ttf"));
            agee.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
            userdetails.setTypeface(Typeface.createFromAsset(context.getAssets(), "cambria.ttf"));
        }

    }

    @NonNull
    @Override
    public AdapterClassVoter.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);

        return new AdapterClassVoter.SearchViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull AdapterClassVoter.SearchViewHolder searchViewHolder, int i) {
        final int p=i;
        searchViewHolder.namee.setText(NameList.get(i));
        searchViewHolder.agee.setText(NUMLIST.get(i));
        final String name = NUMLIST.get(i);
        searchViewHolder.title.setText("Voter ID");
        searchViewHolder.logo.setImageResource(R.drawable.votercard);

        searchViewHolder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "HurrayDone", Toast.LENGTH_SHORT).show();

                Intent profileIntent = new Intent(context, DisplayVoterDetails.class);
                profileIntent.putExtra("voterid_number", name);
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
                                helper = new DatabaseHelper1(context);
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("Voterid_table","voterid_number =?",new String[]{NUMLIST.get(p)});
                                // db.delete("passport_table", "passport_num " + "= " + EmailNameList.get(p), null);
                                //   helper.delete(EmailNameList.get(p));

                                NUMLIST.remove(p);
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

                Intent profileIntent = new Intent(context, EditVoter.class);
                profileIntent.putExtra("voterid_number",NUMLIST.get(p));
                context.startActivity(profileIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NameList.size();

    }
}
