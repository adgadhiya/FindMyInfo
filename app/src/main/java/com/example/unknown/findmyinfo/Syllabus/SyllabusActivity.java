package com.example.unknown.findmyinfo.Syllabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unknown.findmyinfo.R;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SyllabusActivity extends AppCompatActivity {

    Button save;

    String sub_name,selected_field,selected_sem;

    ListView lv_syllabus;

    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("paper");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syllabus);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Syllabus");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lv_syllabus = (ListView) findViewById(R.id.lv_syllabus);

        Bundle bundle = getIntent().getExtras();

        sub_name = bundle.getString("SUB_NAME",null);
        selected_field = bundle.getString("SELECTED_FIELD",null);
        selected_sem = bundle.getString("SELECTED_SEM",null);

        save = (Button) findViewById(R.id.btn_save_syllabus);

        save.setVisibility(View.GONE);

        save.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseReference save_data = FirebaseDatabase.getInstance().getReference().child("paper")
                                .child(selected_sem).child(selected_field);

                        DatabaseReference data_ref = save_data.child("sub_syllabus").child(sub_name).child("syllabus");

                        Map<String,Object> map = new HashMap<String, Object>();
                        map.put("phase_1","phase_1\n" + sub_name + selected_field + "\n" + selected_sem + "\n\n\n" + " is selected");
                        map.put("phase_2","phase_2\n" + sub_name+"\n" + selected_field + "\n" + selected_sem + "\n\n\n" + " is selected");
                        map.put("phase_3","phase_3\n" + sub_name +"\n"+ selected_field + "\n" + selected_sem + "\n\n\n" + " is selected");
                        map.put("reference","reference\n" + sub_name+"\n" + selected_field + "\n" + selected_sem + "\n\n\n" + " is selected");

                        data_ref.updateChildren(map);

                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        DatabaseReference children = reference.child(selected_sem).child(selected_field).child("sub_syllabus").child(sub_name);
        DatabaseReference sub_name_ref = children.child("syllabus");

        Toast.makeText(SyllabusActivity.this, children.toString(), Toast.LENGTH_SHORT).show();

        FirebaseListAdapter<Sub_Syllabus_Phases> adapter = new FirebaseListAdapter<Sub_Syllabus_Phases>(
                this,
                Sub_Syllabus_Phases.class,
                R.layout.syllabus_layout,
                children
        ) {
            @Override
            protected void populateView(View v, Sub_Syllabus_Phases model, int position) {

                TextView first_sessional,second_sessional,third_sessional,reference_book;

                first_sessional = (TextView) v.findViewById(R.id.first_sessional1);
                second_sessional = (TextView) v.findViewById(R.id.second_sessional1);
                third_sessional = (TextView) v.findViewById(R.id.third_sessional1);
                reference_book = (TextView) v.findViewById(R.id.reference_book1);

                first_sessional.setText(model.getPhase_1());
                second_sessional.setText(model.getPhase_2());
                third_sessional.setText(model.getPhase_3());
                reference_book.setText(model.getReference());

            }
        };

        lv_syllabus.setAdapter(adapter);

    }
}
