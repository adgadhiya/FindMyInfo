package com.example.unknown.findmyinfo;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unknown.findmyinfo.Holidays.HolidaysActivity;
import com.example.unknown.findmyinfo.MainScreen.MyPrefClass;
import com.example.unknown.findmyinfo.Result.ResultActivity;
import com.example.unknown.findmyinfo.Schedule.ScheduleActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton syllabus, papers, schedule, holiday;
    private Button result;
    SharedPreferences preferences;
    String btn_clicked = "a";
   DatabaseReference reference = FirebaseDatabase.getInstance().getReference();


    Intent intent;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(R.string.app_name);

        syllabus = (ImageButton) findViewById(R.id.btn_syllabus);
        papers = (ImageButton) findViewById(R.id.btn_papers);
        result = (Button) findViewById(R.id.btn_result);
        schedule = (ImageButton) findViewById(R.id.btn_schedule);
        holiday = (ImageButton) findViewById(R.id.btn_holidays);

        syllabus.setOnClickListener(this);
        papers.setOnClickListener(this);
        result.setOnClickListener(this);
        schedule.setOnClickListener(this);
        holiday.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.settings:
                startActivity(new Intent(MainActivity.this, MyPrefClass.class));
                break;
            case R.id.about_us:
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        String sem = preferences.getString("semester_pref", null);
        String field = preferences.getString("field_pref", null);

        if (v.getId() == R.id.btn_syllabus) {
            btn_clicked = "syllabus";
        } else if (v.getId() == R.id.btn_papers) {
            btn_clicked = "papers";
        }


        bundle = new Bundle();
        bundle.putString("BTN_CLICKED", btn_clicked);

        ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);

        if ((manager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTING ||
                manager.getNetworkInfo(0).getState() == NetworkInfo.State.DISCONNECTED) &&
                (manager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTING ||
                        manager.getNetworkInfo(1).getState() == NetworkInfo.State.DISCONNECTED)) {
            Snackbar.make(v, "No Internet Connection", Snackbar.LENGTH_LONG)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            startActivity(intent);
                        }
                    })
                    .setActionTextColor(Color.YELLOW)
                    .show();
            return;
        }

        if (btn_clicked.contains("papers") || btn_clicked.contains("syllabus")) {

            if (sem == null || field == null) {
                Snackbar.make(v, "Select Field and Semester ", Snackbar.LENGTH_LONG)
                        .setAction("Settings", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                startActivity(new Intent(MainActivity.this, MyPrefClass.class));
                            }
                        })
                        .setActionTextColor(Color.YELLOW)
                        .show();
                return;
            }
        }

        switch (v.getId()) {
            case R.id.btn_syllabus:
                intent = new Intent(MainActivity.this, SubjectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_papers:
                intent = new Intent(MainActivity.this, SubjectActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.btn_result:
                intent = new Intent(MainActivity.this, ResultActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_schedule:
                intent = new Intent(MainActivity.this, ScheduleActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_holidays:
                intent = new Intent(MainActivity.this, HolidaysActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

}
