package com.example.gwen.appsqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

public class AyudaBD extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION=1;
    public static final String DATABASE_NAME="contactos.db";


    public static abstract class DatosTabla implements BaseColumns{

        public static final String NOMBRE_TABLA="Contacto";
        public static final String COLUMNA_ID="id";
        public static final String COLUMNA_NOMBRE="nombre";
        public static final String COLUMNA_TELEFONO="telefono";
        public static final String COLUMNA_CORREO="correo";
        public static final String COLUMNA_RED_SOCIAL="red";

        private static final String TEXT_TYPE=" TEXT";
        private static final String COMMA_SEP= ",";
        private static final String CREAR_TABLA_1=

                "CREATE TABLE "+DatosTabla.NOMBRE_TABLA+" ("+
                        DatosTabla.COLUMNA_ID+" INTEGER PRIMARY KEY,"+
                        DatosTabla.COLUMNA_NOMBRE+TEXT_TYPE+COMMA_SEP+
                        DatosTabla.COLUMNA_TELEFONO+TEXT_TYPE+COMMA_SEP+
                        DatosTabla.COLUMNA_CORREO+TEXT_TYPE+COMMA_SEP+
                        DatosTabla.COLUMNA_RED_SOCIAL+TEXT_TYPE+" )";

        private static final String SQL_DELETE_ENTRIES="DROP TABLE IF EXISTS "+DatosTabla.NOMBRE_TABLA;







    }

    public AyudaBD(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(DatosTabla.CREAR_TABLA_1);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(DatosTabla.SQL_DELETE_ENTRIES);
        onCreate(db);

    }
}
