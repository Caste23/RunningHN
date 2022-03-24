package com.example.firma;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firma.Conexion.conexion;
import com.example.firma.R.layout;
import com.example.firma.atrubutos.signaturess;
import com.example.firma.tablas.tablas;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

public class Listado extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private List<signaturess> list = new ArrayList<>();
    private ListView listView;
    ListAdapter listAdapter;
    public static Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        setTitle("Listado de Firmas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listView =(ListView)findViewById(R.id.listview);
        listView.setOnItemClickListener(this);


        extrdatos();

        findViewById(R.id.main_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });

    }

    private void extrdatos(){

        conexion conexion = new conexion(this, tablas.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        Cursor cursor = db.rawQuery("Select * from "+ tablas.tablaFirma,null);

        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String description = cursor.getString(2);


            list.add(new signaturess(String.valueOf(id), description, buscarImagen(id)));
            listAdapter = new listados(this,R.layout.item_row, list);

            listView.setAdapter(listAdapter);
        }
        cursor.close();
    }
    public Bitmap buscarImagen(long id){
        conexion conexion = new conexion(this, tablas.NameDataBase, null, 1);
        SQLiteDatabase db = conexion.getReadableDatabase();

        String sql = "SELECT * FROM Firma where id = "+id;
        Cursor cursor = db.rawQuery(sql, new String[] {});
        Bitmap bitmap = null;
        if(cursor.moveToFirst()){
            byte[] blob = cursor.getBlob(1);
            ByteArrayInputStream bais = new ByteArrayInputStream(blob);
            bitmap = BitmapFactory.decodeStream(bais);
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        db.close();
        return bitmap;
    }

    public static int resultado=1;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == resultado && requestCode == RESULT_OK) {
            extrdatos();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent = new Intent(getApplicationContext(),ver_imagen.class);

        bitmap= list.get(position).getFirmaDigital();
        startActivity(intent);

    }

    public static Bitmap bitmap(){

        return bitmap;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
