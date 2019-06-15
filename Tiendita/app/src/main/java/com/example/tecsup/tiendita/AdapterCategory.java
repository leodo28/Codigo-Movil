package com.example.tecsup.tiendita;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tecsup.tiendita.Categoria;
import com.example.tecsup.tiendita.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class AdapterCategory extends BaseAdapter {
    protected Context activity;
    protected List<Categoria> items;
    public AdapterCategory (Context activity, List<Categoria> items) {
        this.activity = activity;
        this.items = items;
    }
    @Override
    public int getCount() {
        return items.size();
    }
    public void clear() {
        items.clear();
    }
    public void addAll(ArrayList<Categoria> category) {
        for (int i = 0; i < category.size(); i++) {
            items.add(category.get(i));
        }
    }
    @Override
    public Object getItem(int arg0) {
        return items.get(arg0);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_category, null);
        }
        Categoria dir = items.get(position);
        TextView title = (TextView) v.findViewById(R.id.category);
        title.setText(dir.id.toString());
        TextView description = (TextView) v.findViewById(R.id.texto);
        description.setText(dir.descripcion);
        ImageView imagen = (ImageView) v.findViewById(R.id.imageView);
        Glide.with(activity).load(dir.imagen_banner).into(imagen);
        return v;
    }
}