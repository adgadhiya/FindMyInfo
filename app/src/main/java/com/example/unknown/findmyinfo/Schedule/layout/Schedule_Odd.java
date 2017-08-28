package com.example.unknown.findmyinfo.Schedule.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.unknown.findmyinfo.R;
import com.example.unknown.findmyinfo.Schedule.SchduleGetData;
import com.example.unknown.findmyinfo.Schedule.ScheduleViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * A simple {@link Fragment} subclass.
 */
public class Schedule_Odd extends Fragment {

    RecyclerView lv_odd;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("schedule");
    DatabaseReference child_odd = reference.child("odd");

    public Schedule_Odd() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_schedule__odd,container, false);
        lv_odd = (RecyclerView) view.findViewById(R.id.lv_odd);

        lv_odd.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        lv_odd.setHasFixedSize(true);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<SchduleGetData,ScheduleViewHolder> adapter =
                new FirebaseRecyclerAdapter<SchduleGetData, ScheduleViewHolder>
                        (
                                SchduleGetData.class,
                                R.layout.schedule_view,
                                ScheduleViewHolder.class,
                                child_odd
                        ) {
            @Override
            protected void populateViewHolder(ScheduleViewHolder viewHolder, SchduleGetData model, int position) {
                            viewHolder.tv_date.setText(model.getDate());
                            viewHolder.tv_programme.setText(model.getProgramme());
            }
        };

        lv_odd.setAdapter(adapter);

    }

}
