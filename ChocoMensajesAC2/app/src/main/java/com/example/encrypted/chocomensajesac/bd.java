package com.example.encrypted.chocomensajesac;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KRLOS on 11/08/2017.
 */

public class bd extends SQLiteOpenHelper{

    Context ctx ;
    public bd(Context context) {
        super(context, "Bd", null, 1);
        ctx=context;
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL("CREATE TABLE num (id INTEGER PRIMARY KEY AUTOINCREMENT ,valor BOOLEAN NOT NULL);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int i, int i1) {
        bd.execSQL("DROP TABLE IF EXISTS num");
        onCreate(bd);
    }

    bd ayuda;
    SQLiteDatabase db;

    public void open (){
        ayuda = new bd(ctx);
        db=ayuda.getWritableDatabase();
    }
    public void close (){
        db.close();
    }
    public long insert (int valor)throws Exception{
        ContentValues valores = new ContentValues();
        valores.put("valor",valor);
        return db.insert("num",null,valores);
    }

    public String consultar()throws Exception{
        String cad ="";
        String [] columnas = new String[]{"valor"};
        Cursor c = db.query("num",columnas,null,null,null,null,null);
        for (c.moveToFirst();!c.isAfterLast();c.moveToNext()){
            cad =c.getString(c.getColumnIndex("valor"));

        }
        return cad;
    }
}
