package com.example.unknown.findmyinfo.Papers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by UNKNOWN on 7/25/2016.
 */
public class YearsViewHolder extends RecyclerView.ViewHolder{

    TextView textView;

    public YearsViewHolder(View itemView) {
        super(itemView);
        textView = (TextView) itemView.findViewById(android.R.id.text1);
    }
}