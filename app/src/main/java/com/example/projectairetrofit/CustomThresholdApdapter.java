package com.example.projectairetrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.airbnb.lottie.animation.content.Content;

public class CustomThresholdApdapter extends BaseAdapter {
    private Context context;
    private int arr[];
    private LayoutInflater inflater;

    public CustomThresholdApdapter(Context context, int arr[]){
        this.context= context;
        this.arr= arr;
        inflater=(LayoutInflater.from(context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return arr.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView= inflater.inflate(R.layout.spinner_item_threshold, null);


        return convertView;
    }
}
