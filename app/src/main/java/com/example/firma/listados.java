package com.example.firma;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.firma.atrubutos.signaturess;

import java.util.List;

public class listados extends ArrayAdapter {
    private List<signaturess> listar;
    private Context context;
    private int resourceLayout;

    public listados(@NonNull Context context, int resource, @NonNull List<signaturess> objects) {
        super(context, resource, objects);
        this.context = context;
        this.listar = objects;
        this.resourceLayout = resource;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        if(view==null){
            view = LayoutInflater.from(context).inflate(resourceLayout,null);
        }
        signaturess signaturess = listar.get(position);

        ImageView imageView = view.findViewById(R.id.image);
        imageView.setImageBitmap(signaturess.getFirmaDigital());
        TextView id = view.findViewById(R.id.txtId);
        id.setText(signaturess.getId());

        TextView ruta = view.findViewById(R.id.txtDescriptionListado);
        ruta.setText(signaturess.getDescripcion());

        return view;
    }

}
