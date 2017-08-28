package com.example.unknown.findmyinfo.Schedule;

import android.app.ProgressDialog;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.unknown.findmyinfo.DialohHandler;
import com.example.unknown.findmyinfo.R;
import com.example.unknown.findmyinfo.Schedule.layout.Schedule_Even;
import com.example.unknown.findmyinfo.Schedule.layout.Schedule_Odd;
import com.example.unknown.findmyinfo.Schedule.layout.ViewPagerAdapter;

public class ScheduleActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        setSupportActionBar((Toolbar)findViewById(R.id.toolbar1));
        getSupportActionBar().setTitle("Academic Calender");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        pager = (ViewPager) findViewById(R.id.syllabus_pager);
        tabLayout = (TabLayout) findViewById(R.id.syllabus_tabLayout);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragments(new Schedule_Odd(),"Odd Semester");
        adapter.addFragments(new Schedule_Even(),"Even Semester");
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Load();
    }

    private void Load() {
        // TODO Auto-generated method stub

        final DialohHandler handler = new DialohHandler(ScheduleActivity.this);

        new Thread(new Runnable() {
            public void run() {
                // TODO Auto-generated method stub
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
        }).start();
    }
}
