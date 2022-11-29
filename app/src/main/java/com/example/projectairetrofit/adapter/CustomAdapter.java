package com.example.projectairetrofit.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.projectairetrofit.R;

public class CustomAdapter extends BaseAdapter {

    Context context;
    int img[];

    LayoutInflater inflater;

    public CustomAdapter(Context context, int[] img){
        this.context= context;
        this.img= img;
        inflater=(LayoutInflater.from(context.getApplicationContext()));
    }
    @Override
    public int getCount() {
        return img.length;
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
        convertView= inflater.inflate(R.layout.spinner_item, null);
        ImageView icon= ( ImageView) convertView.findViewById(R.id.imageView);
        icon.setImageResource(img[position]);

        return convertView;
    }
}
