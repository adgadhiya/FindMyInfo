package com.example.unknown.findmyinfo.Papers;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unknown.findmyinfo.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Paper_Block_Frag extends Fragment {

    TextView tv_paper_block;

    public Paper_Block_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_paper__block_, container, false);
        tv_paper_block = (TextView) view.findViewById(R.id.tv_paper_block);

        return view;
    }


    public void getYear(String selected_year, String sub_name,final Context context){


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        if(selected_year != null) {

            String selected_field = preferences.getString("field_pref",null);
            String selected_sem = preferences.getString("semester_pref",null);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("paper");
            DatabaseReference child = reference.child(selected_sem).child(selected_field).child(sub_name).child(selected_year).child("block");

            child.addValueEventListener(
                    new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot snapshot : dataSnapshot.getChildren()){

                                if(snapshot.getKey().contains("link")){
                                    Toast.makeText(context, snapshot.getValue().toString() , Toast.LENGTH_SHORT).show();
                                } else {
                                    tv_paper_block.setText(snapshot.getValue().toString());
                                }
                            }

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    }
            );
        }

    }
}
