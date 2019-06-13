package com.example.sneha.googlesignin;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

import me.anwarshahriar.calligrapher.Calligrapher;

public class SocialMediaName extends AppCompatActivity {

    SocialMediaAdapter adapter;
    String activity;
    MaterialSearchView searchView;

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.searchmenu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_name);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"cambria.ttf",true);

        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        toolbar.setTitleTextAppearance(this,R.style.Cambria);

        searchView = (MaterialSearchView) findViewById(R.id.search_view);

        ListView listView = (ListView) findViewById(R.id.list);
        activity = getIntent().getExtras().getString("NAME");
        final ArrayList<SocialMediaList> social = new ArrayList<SocialMediaList>();

        social.add(new SocialMediaList("Facebook", R.drawable.facebook));
        social.add(new SocialMediaList("Instagram", R.drawable.instagram));
        social.add(new SocialMediaList("LinkedIn", R.drawable.linkedin));
        social.add(new SocialMediaList("Google+", R.drawable.google));
        social.add(new SocialMediaList("Twitter", R.drawable.twitter));

        final ArrayList<SocialMediaList> allSocial = new ArrayList<>(social);
        adapter = new SocialMediaAdapter(this, social);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                SocialMediaList social_num = social.get(i);

                if(activity.equals("socialSite")) {
                    SocialSites.socialName = social_num.getName();
                    SocialSites.pickSocial.setText(social_num.getName());
                    SocialSites.pickSocial.setVisibility(View.VISIBLE);
                    finish();
                }
                else if(activity.equals("EditSocialSite")){
                    EditSocial.socialName = social_num.getName();
                    EditSocial.pickSocial.setText(social_num.getName());
                    EditSocial.pickSocial.setVisibility(View.VISIBLE);
                    finish();
                }
            }
        });


        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {


            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String text) {
                ArrayList<SocialMediaList> filteredList = new ArrayList<>();

                if(text == null || text.length() == 0){
                    filteredList.addAll(allSocial);
                }else{
                    String filteredString = text.toLowerCase().trim();
                    for(SocialMediaList i : allSocial){
                        if(i.getName().toLowerCase().contains(filteredString)){
                            filteredList.add(i);
                        }
                    }
                }

                social.clear();
                social.addAll(filteredList);

                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

}
