package com.example.unknown.findmyinfo.Schedule;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.unknown.findmyinfo.R;

/**
 * Created by UNKNOWN on 7/24/2016.
 */
public class ScheduleViewHolder extends RecyclerView.ViewHolder {

   public  TextView tv_date,tv_programme;

    public ScheduleViewHolder(View itemView) {
        super(itemView);
        tv_date = (TextView) itemView.findViewById(R.id.tv_date);
        tv_programme = (TextView) itemView.findViewById(R.id.tv_programme);
    }
}
