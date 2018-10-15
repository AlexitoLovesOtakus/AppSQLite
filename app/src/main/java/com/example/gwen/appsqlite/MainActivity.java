package com.example.gwen.appsqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnGuardar , btnEliminar , btnModificar , btnBuscar;
    EditText etId , etNombre , etTelefono ,etCorreo , etRed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar=findViewById(R.id.btnGuardar);
        btnBuscar=findViewById(R.id.btnBuscar);
        btnModificar=findViewById(R.id.btnActualizar);
        btnEliminar=findViewById(R.id.btnEliminar);

        etId=findViewById(R.id.etId);
        etNombre=findViewById(R.id.etNombre);
        etTelefono=findViewById(R.id.etTelefono);
        etCorreo=findViewById(R.id.etCorreo);
        etRed=findViewById(R.id.etRed);

        final AyudaBD ayudaBD=new AyudaBD(getApplicationContext());

       btnGuardar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SQLiteDatabase db =ayudaBD.getWritableDatabase();
               ContentValues valores=new ContentValues();
               valores.put(AyudaBD.DatosTabla.COLUMNA_ID,etId.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_NOMBRE,etNombre.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_TELEFONO,etTelefono.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_CORREO,etCorreo.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_RED_SOCIAL,etRed.getText().toString());

               Long IdGuardado =db.insert(AyudaBD.DatosTabla.NOMBRE_TABLA, AyudaBD.DatosTabla.COLUMNA_ID,valores);
               Toast.makeText(getApplicationContext(),"se guardo el registro "+IdGuardado ,  Toast.LENGTH_LONG).show();

           }
       });

       btnEliminar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SQLiteDatabase db=ayudaBD.getWritableDatabase();
               String selection= AyudaBD.DatosTabla.COLUMNA_ID+"=?";
               String []argsel={etId.getText().toString()};

               db.delete(AyudaBD.DatosTabla.NOMBRE_TABLA,selection,argsel);

           }
       });

       btnModificar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SQLiteDatabase db=ayudaBD.getWritableDatabase();
               ContentValues valores=new ContentValues();

               valores.put(AyudaBD.DatosTabla.COLUMNA_NOMBRE,etNombre.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_TELEFONO,etTelefono.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_CORREO,etCorreo.getText().toString());
               valores.put(AyudaBD.DatosTabla.COLUMNA_RED_SOCIAL,etRed.getText().toString());

               String []argsel={etId.getText().toString()};
               String selection= AyudaBD.DatosTabla.COLUMNA_ID+"=?";

               int count=db.update(AyudaBD.DatosTabla.NOMBRE_TABLA,valores,selection,argsel);

           }
       });

       btnBuscar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               SQLiteDatabase db=ayudaBD.getReadableDatabase();
               String []argsel={etNombre.getText().toString()};
               String[] projection={AyudaBD.DatosTabla.COLUMNA_ID, AyudaBD.DatosTabla.COLUMNA_TELEFONO, AyudaBD.DatosTabla.COLUMNA_CORREO, AyudaBD.DatosTabla.COLUMNA_RED_SOCIAL};
               Cursor c=db.query(AyudaBD.DatosTabla.NOMBRE_TABLA,projection, AyudaBD.DatosTabla.COLUMNA_NOMBRE+"=?",argsel,null,null,null);

               c.moveToFirst();
               etId.setText(c.getString(0));
               etTelefono.setText(c.getString(1));
               etCorreo.setText(c.getString(2));
               etRed.setText(c.getString(3));

           }
       });


    }
}
