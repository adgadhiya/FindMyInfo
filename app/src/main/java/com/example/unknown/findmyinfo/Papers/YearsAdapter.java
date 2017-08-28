package com.example.unknown.findmyinfo.Papers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.unknown.findmyinfo.R;

import java.util.ArrayList;

/**
 * Created by UNKNOWN on 7/26/2016.
 */
public class YearsAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> arrayList = new ArrayList<>();

    public YearsAdapter(Context context){
       this.context = context;
   }

    public void addItem(String item){
        arrayList.add(item);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.years_list_layout,parent,false);
        }

        if (position == arrayList.size() - 1) {
            view.setSelected(true);
        }

        TextView textView = (TextView) view.findViewById(R.id.tv_years);
        textView.setText(arrayList.get(position));

        return null;
    }
}
