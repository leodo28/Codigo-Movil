package com.santiago.canchas;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.santiago.canchas.Models.Cancha;

import java.util.List;

public class AdapterCancha extends BaseAdapter {

    public List<Cancha> canchas;
    Context context;
    int layout;

    public AdapterCancha(List<Cancha> canchas, Context context, int layout) {
        this.canchas = canchas;
        this.context = context;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return canchas.size();
    }

    @Override
    public Object getItem(int position) {
        return canchas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View View, ViewGroup parent) {
        View v = View;
        LayoutInflater lv = LayoutInflater.from(context);
        v= lv.inflate(layout,null);
        ImageView imagen = v.findViewById(R.id.imagen);
        Glide.with(v).load(canchas.get(position).getFoto()).into(imagen);
        TextView nombre = v.findViewById(R.id.nombre);
        nombre.setText(canchas.get(position).getNombre());
        TextView precio = v.findViewById(R.id.precio);
        precio.setText(canchas.get(position).getPrecio_hora()+"");
        TextView direccion = v.findViewById(R.id.direccion);
        direccion.setText(canchas.get(position).getDireccion());
        return v;
    }
}
