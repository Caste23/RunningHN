package com.example.firma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

public class ver_imagen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imagen);

        setTitle("Visualizar Imagen de la firma");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView img = (ImageView) findViewById(R.id.imageView);
        img.setImageBitmap(Listado.bitmap());
    }
}