package com.example.unknown.findmyinfo.Papers;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.unknown.findmyinfo.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PapersActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener, TabLayout.OnTabSelectedListener{

    TabLayout tabLayout;
    ListView listView;
    ViewPager viewPager;
    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    PapersViewPagerAdapter viewPagerAdapter;

    Paper_First_Phase_Frag paper_first_phase_frag;
    Paper_second_phase_Frag paper_second_phase_frag;
    Paper_Third_Phase_Frag paper_third_phase_frag;
    Paper_Final_Frag paper_final_frag;
    Paper_Block_Frag paper_block_frag;

    String sub_name,selected_field,selected_sem,selected_year;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_papers);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        listView = (ListView)findViewById(R.id.lv_years);
        viewPager = (ViewPager)findViewById(R.id.viewpager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);

        viewPagerAdapter = new PapersViewPagerAdapter(getSupportFragmentManager());

        Bundle bundle = getIntent().getExtras();

        sub_name = bundle.getString("SUB_NAME",null);
        selected_sem = bundle.getString("SELECTED_SEM",null);
        selected_field = bundle.getString("SELECTED_FIELD",null);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(sub_name.toUpperCase());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        actionBarDrawerToggle = new ActionBarDrawerToggle(PapersActivity.this,drawerLayout,R.string.app_name,R.string.app_name);

        paper_first_phase_frag = new Paper_First_Phase_Frag();
        paper_second_phase_frag = new Paper_second_phase_Frag();
        paper_third_phase_frag = new Paper_Third_Phase_Frag();
        paper_final_frag = new Paper_Final_Frag();
        paper_block_frag = new Paper_Block_Frag();

        viewPagerAdapter.addFragment(paper_first_phase_frag,"Sessional 1");
        viewPagerAdapter.addFragment(paper_second_phase_frag,"Sessional 2");
        viewPagerAdapter.addFragment(paper_third_phase_frag,"Sessioanl 3");
        viewPagerAdapter.addFragment(paper_final_frag,"External");
        viewPagerAdapter.addFragment(paper_block_frag,"Block");

        viewPager.setAdapter(viewPagerAdapter);

        tabLayout.setupWithViewPager(viewPager);

        reference = FirebaseDatabase.getInstance().getReference().child("paper");

        listView.setOnItemClickListener(this);
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        DatabaseReference chiReference = reference.child(selected_sem).child(selected_field).child(sub_name).child("year");

        FirebaseListAdapter<String> adapter = new FirebaseListAdapter<String>(
                PapersActivity.this,
                String.class,
                R.layout.years_list_layout,
                chiReference
        ) {
            @Override
            protected void populateView(View v, String model, int position) {
                TextView textView = (TextView) v.findViewById(R.id.tv_years);
                textView.setText(model);
            }
        };

        listView.setAdapter(adapter);
        listView.setItemChecked(0,true);

        selected_year = "2011";

        paper_first_phase_frag.getYear(selected_year,sub_name,this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START,true);
        }

        return true;
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        TextView view1 = (TextView) view.findViewById(R.id.tv_years);

        selected_year = view1.getText().toString();

        drawerLayout.closeDrawers();

        onTabSelected(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()));

    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

        switch (tab.getPosition()){

            case 0 :
                paper_first_phase_frag.getYear(selected_year,sub_name,this);
                break;
            case  1 :
                paper_second_phase_frag.getYear(selected_year,sub_name,this);
                break;
            case 2 :
                paper_third_phase_frag.getYear(selected_year,sub_name,this);
                break;
            case 3 :
                paper_final_frag.getYear(selected_year,sub_name,this);
                break;
            case 4 :
                paper_block_frag.getYear(selected_year,sub_name,this);
                break;
            default:
                paper_first_phase_frag.getYear(selected_year,sub_name,this);
                break;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
