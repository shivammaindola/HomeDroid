package com.example.sneha.googlesignin;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AdapterClassOther extends RecyclerView.Adapter<AdapterClassOther.SearchViewHolder> {

    DatabaseHelper11 database;
    ArrayList<String> question;
    ArrayList<String> answer;
    ArrayList<String> form;
    Context context;
    static String KEY_INTENT = "key_for_intent_here";

    public AdapterClassOther(ArrayList<String> question, ArrayList<String> answer, ArrayList<String> form, Context context){
        this.question = question;
        this.answer = answer;
        this.form = form;
        this.context = context;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder, viewGroup, false);

        return new AdapterClassOther.SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int i) {
        final int p = i;
        final String name = question.get(i);

        holder.namee.setText(question.get(i));
        holder.agee.setText(answer.get(i));
        holder.title.setText(form.get(i));
        holder.logo.setImageResource(R.drawable.other);
        holder.userdetails.setVisibility(View.INVISIBLE);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditOther.class);
                intent.putExtra(KEY_INTENT, name);
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
                                database = new DatabaseHelper11(context);
                                SQLiteDatabase db = database.getWritableDatabase();
                                db.delete("other_table","question =?",new String[]{question.get(p)});

                                question.remove(p);
                                answer.remove(p);
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
        return question.size();
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
}
