package com.example.unknown.findmyinfo.Schedule.layout;


import android.app.ProgressDialog;
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

public class Schedule_Even extends Fragment {

    RecyclerView lv_even;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("schedule");
    DatabaseReference child_even = reference.child("even");

    public Schedule_Even() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_schedule__even, container, false);
        lv_even = (RecyclerView) view.findViewById(R.id.lv_even);
        lv_even.setHasFixedSize(true);
        lv_even.setLayoutManager(new LinearLayoutManager(this.getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        ProgressDialog dialog = ProgressDialog.show(this.getActivity(),"","Loading Data....",true);
        dialog.setCancelable(false);

        FirebaseRecyclerAdapter<SchduleGetData,ScheduleViewHolder> adapter =
                new FirebaseRecyclerAdapter<SchduleGetData, ScheduleViewHolder>
                        (
                                SchduleGetData.class,
                                R.layout.schedule_view,
                                ScheduleViewHolder.class,
                                child_even
                        ) {
                    @Override
                    protected void populateViewHolder(ScheduleViewHolder viewHolder, SchduleGetData model, int position) {
                        viewHolder.tv_date.setText(model.getDate());
                        viewHolder.tv_programme.setText(model.getProgramme());
                    }
                };
        lv_even.setAdapter(adapter);

        dialog.dismiss();
    }


}
