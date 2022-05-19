package com.example.javafirsttry;

import java.util.ArrayList;
import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList<String> list;
    private static LayoutInflater inflater = null;
    public ListViewAdapter(Activity activity, ArrayList<String> list) {
        this.activity = activity;
        this.list = list;
        inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    public int getCount() {
        return list.size();
    }
    public Object getItem(int position) {
        return position;
    }
    public long getItemId(int position) {
        return position;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_item, null);
        TextView name = (TextView) convertView.findViewById(R.id.name); // title
        name.setText((list.get(position)));
        return convertView;
    }
}
