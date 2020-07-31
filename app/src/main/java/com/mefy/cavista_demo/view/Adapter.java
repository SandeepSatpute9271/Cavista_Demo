package com.mefy.cavista_demo.view;


import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.mefy.cavista_demo.R;
import com.mefy.cavista_demo.model.bean.Images;
import com.mefy.cavista_demo.utils.Finals;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    private static final String TAG = "Adapter";
    private LayoutInflater inflater;
    private Context context;
    private ArrayList<Images> images;

    public Adapter(Context context, ArrayList<Images> images) {
        inflater = LayoutInflater.from(context);
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);
        }
        else{
            imageView = (ImageView) convertView;
        }
        Glide
                .with(context)
                .load(images.get(position).getLink())
                .placeholder(R.drawable.ic_baseline_local_florist_24)
                .error(R.drawable.ic_baseline_error_outline_24)
                .thumbnail(0.1f)
                .into(imageView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Details.class);
                intent.putExtra(Finals.SELECTED_IMAGE, images.get(position));
                context.startActivity(intent);
            }
        });
        return imageView;
    }
}