package com.example.sneha.googlesignin;

import android.app.AlertDialog;
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
import android.widget.Toast;

import java.util.ArrayList;

public class AdapterClassBanking  extends  RecyclerView.Adapter<AdapterClassBanking.SearchViewHolder> {
     ArrayList<String> BankNameList, CardList, HolderNameList, CardNumberList, CardTypeList;
     ArrayList<Integer> BankViewList, CardTypeLogoList;

    Context context;
    DatabaseHelper4 helper;
    public AdapterClassBanking(ArrayList<String> bankNameList,
                               ArrayList<String> cardList,
                               ArrayList<String> holderNameList,
                               ArrayList<String> cardNumberList,
                               ArrayList<String> cardTypeList,
                               ArrayList<Integer> bankViewList,
                               ArrayList<Integer> cardTypeLogoList,
                               Context context) {

        BankNameList = bankNameList;
        CardList = cardList;
        HolderNameList = holderNameList;
        CardNumberList = cardNumberList;
        CardTypeList = cardTypeList;
        BankViewList = bankViewList;
        CardTypeLogoList = cardTypeLogoList;
        this.context = context;
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView bankName, card, holderName, userdetails, cardNumber, cardType;
        ImageView edit, delete, bankView, cardTypeLogo;

        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            bankName = (TextView) itemView.findViewById(R.id.bankName);
            card = (TextView) itemView.findViewById(R.id.card);
            holderName = (TextView) itemView.findViewById(R.id.holderName);
            userdetails = (TextView) itemView.findViewById(R.id.view);
            cardNumber = (TextView) itemView.findViewById(R.id.cardNumber);
            cardType = (TextView) itemView.findViewById(R.id.cardType);
            edit = (ImageView) itemView.findViewById(R.id.edit);
            delete = (ImageView) itemView.findViewById(R.id.delete);
            bankView = (ImageView) itemView.findViewById(R.id.bankView);
            cardTypeLogo = (ImageView) itemView.findViewById(R.id.cardTypeLogo);

        }
    }
    @NonNull
    @Override
    public AdapterClassBanking.SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layoutholder_banking, viewGroup, false);

        return new AdapterClassBanking.SearchViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterClassBanking.SearchViewHolder searchViewHolder, int i) {
        final int p=i;

        String num = CardNumberList.get(i);
        String cardNum = "XXXX XXXX " + num.substring(num.length() - 4, num.length());
        String cardDebitCredit = CardList.get(i) + " Card";

        searchViewHolder.holderName.setText(HolderNameList.get(i));
        searchViewHolder.card.setText(cardDebitCredit);
        searchViewHolder.cardType.setText(CardTypeList.get(i));
        searchViewHolder.bankName.setText(BankNameList.get(i));
        searchViewHolder.cardNumber.setText(cardNum);
        searchViewHolder.cardTypeLogo.setImageResource(CardTypeLogoList.get(i));
        searchViewHolder.bankView.setImageResource(BankViewList.get(i));
        final String number = CardNumberList.get(i);

        searchViewHolder.userdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "HurrayDone", Toast.LENGTH_SHORT).show();

                Intent profileIntent = new Intent(context, DisplayBanking.class);
                profileIntent.putExtra("cardnumber", number);
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
                                helper=new DatabaseHelper4(context);
                                SQLiteDatabase db = helper.getWritableDatabase();
                                db.delete("Card_table","Debit_Number =?",new String[]{CardNumberList.get(p)});

                                HolderNameList.remove(p);
                                CardList.remove(p);
                                CardTypeList.remove(p);
                                BankNameList.remove(p);
                                CardNumberList.remove(p);
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
                Intent profileIntent = new Intent(context, EditBanking.class);
                profileIntent.putExtra("cardnumber",CardNumberList.get(p));
                context.startActivity(profileIntent);
            }
        });
    }




    @Override
    public int getItemCount() {
        return CardNumberList.size();
    }
}
