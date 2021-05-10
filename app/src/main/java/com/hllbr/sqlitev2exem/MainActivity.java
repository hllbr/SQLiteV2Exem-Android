package com.hllbr.sqlitev2exem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {


            //Veritabanı oluştu
            SQLiteDatabase database = this.openOrCreateDatabase("Coins", MODE_PRIVATE, null);
            //Tablo oluşturuldu
            database.execSQL("CREATE TABLE IF NOT EXISTS coinlist(id INTEGER PRIMARY KEY,name VARCHAR,ceo VARCHAR)");
            //Tabloya Veri Ekleme
            database.execSQL("INSERT INTO coinlist(name,ceo) VALUES('bitcoin','sakashi natakomo')");
            database.execSQL("INSERT INTO coinlist(name,ceo)VALUES('TRON','JUST SUN')");
            database.execSQL("INSERT INTO coinlist(name,ceo)VALUES('SUN','JUST SUN')");
            //Veri Güncellemek
            database.execSQL("UPDATE coinlist SET ceo ='sun just' WHERE name = 'SUN'");
            database.execSQL("UPDATE coinlist SET ceo = 'unknow' WHERE name = 'bitcoin'");

            //Veri Silmek =
            //database.execSQL("DELETE FROM coinlist WHERE id = 2");
            //Veri Okumak =
            Cursor cursor = database.rawQuery("SELECT * FROM coinlist", null);//koşulsuz veri okuma

            Cursor cursor1 = database.rawQuery("SELECT * FROM coinlist WHERE name LIKE '%N'", null);
            Cursor cursor2 = database.rawQuery("SELECT * FROM coinlist WHERE id = 3 ", null);
            //....
            int nameIx = cursor.getColumnIndex("name");
            int ceoIx = cursor.getColumnIndex("ceo");
            int idIx = cursor.getColumnIndex("id");
            while(cursor.moveToNext()){


            System.out.println("Coin name " + cursor.getString(nameIx));
            System.out.println("Coin Ceo " + cursor.getString(ceoIx));
            System.out.println("Coin Number " + cursor.getString(idIx));
            }
            cursor.close();
            cursor1.close();
            cursor2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}