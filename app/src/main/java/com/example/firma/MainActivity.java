package com.example.firma;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.firma.Conexion.conexion;
import com.example.firma.tablas.tablas;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    Bitmap bitmap;
    EditText descripcion;
    byte[] blob = null;

    private Bitmap_capture mSig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTitle("Añadir Firma");
        descripcion = (EditText)findViewById(R.id.txtnombre);

        LinearLayout mContent = (LinearLayout) findViewById(R.id.lin);
        mSig = new Bitmap_capture(this, null);
        mContent.addView(mSig, LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT);

        findViewById(R.id.btnGuardar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (descripcion.getText().toString().trim().length() == 0) {
                    descripcion.setError("Ingrese este campo");
                } else {
                    bitmap = mSig.getBitmap();
                    try {
                        //cambia el tamaño manteniendo la relacion del aspecto
                        setToImageView(getResizeBitmap(bitmap, 200));
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), "fallo en tener firmas", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });
        findViewById(R.id.btnver).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(),Listado.class);
                startActivity(in);
            }
        });


    }
    private Bitmap getResizeBitmap(Bitmap bitmap, int maxSize) {
        int wi = bitmap.getWidth();
        int he = bitmap.getHeight();

        if (wi<= maxSize && he<=maxSize){
            return bitmap;
        }

        float fabradio = (float)wi/(float)he;

        if(fabradio > 1){
            wi=maxSize;
            he = (int) (wi/fabradio);
        }else{
            he=maxSize;
            wi= (int) (he*fabradio);
        }

        return  Bitmap.createScaledBitmap(bitmap,wi,he,true);
    }
    private void setToImageView(Bitmap bitmap){
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        bitmap = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        byte[] blob = bytes.toByteArray();
        setBlob(blob);

        uploadImage();
    }
    public byte[] getBlob() {

        return blob;
    }
    public void setBlob(byte[] blob)
    {
        this.blob = blob;
    }
    private void uploadImage() {



        if (getBlob() != null) {
            conexion conexion = new conexion(this, tablas.NameDataBase, null, 1);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(tablas.firmas, getBlob());
            valores.put(tablas.nombre, descripcion.getText().toString());


            Long res = db.insert(tablas.tablaFirma, tablas.id, valores);
            db.close();


            Toast.makeText(getApplicationContext(), "Se ha guardado exitosamente." + res.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Seleccione la imagen." , Toast.LENGTH_SHORT).show();

        }
    }

}