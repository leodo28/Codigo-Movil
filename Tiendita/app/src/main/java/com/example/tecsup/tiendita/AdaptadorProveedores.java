package com.example.tecsup.tiendita;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorProveedores extends BaseAdapter {
    protected Context activity;
    protected List<Proveedor> items;
    int layout;
    public AdaptadorProveedores (Context activity, List<Proveedor> items) {
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
    public void addAll(ArrayList<Proveedor> proveedor) {
        for (int i = 0; i < proveedor.size(); i++) {
            items.add(proveedor.get(i));
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
            LayoutInflater inf = LayoutInflater.from(activity);
            v = inf.inflate(R.layout.item_proveedor, null);
        }
        Proveedor dir = items.get(position);
        TextView razon = (TextView) v.findViewById(R.id.razon);
        razon.setText(dir.razon_social.toString());
        TextView ruc = (TextView) v.findViewById(R.id.ruc);
        ruc.setText(dir.ruc);
        TextView dire = (TextView) v.findViewById(R.id.direccion);
        dire.setText(dir.direccion);
        return v;
    }
}
