package com.example.fiszkonator;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String DB_PATH = "";
    private static String DB_NAME = "MedBaza.db";
    private SQLiteDatabase myDB;
    private Context mContext = null;

    public DatabaseHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
        DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        mContext = context;
    }

    public synchronized void close() {
        if (myDB != null) {
            myDB.close();
        }
        super.close();
    }

    /***
     * Check if the database is exist on device or not
     * @return
     */
    private boolean checkDataBase() {
        SQLiteDatabase tempDB = null;
        try {
            String myPath = DB_PATH + DB_NAME;
            tempDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e) {
            Log.e("tle99 - check", e.getMessage());
        }
        if (tempDB != null)
            tempDB.close();
        return tempDB != null ? true : false;
    }

    /***
     * Copy database from source code assets to device
     * @throws IOException
     */
    public void copyDataBase() throws IOException {
        try {
            InputStream myInput = mContext.getAssets().open(DB_NAME);
            String outputFileName = DB_PATH + DB_NAME;
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;

            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }

            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (Exception e) {
            Log.e("tle99 - copyDatabase", e.getMessage());
        }

    }

    /***
     * Check if the database doesn't exist on device, create new one
     * @throws IOException
     */
    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();

        if (dbExist) {

        } else {
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                Log.e("tle99 - create", e.getMessage());
            }
        }
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<String> getAllSubjects(){
        List<String> listUsers = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        String TB_NAME = "Przedmiot";

        try {
            c = db.rawQuery("SELECT * FROM " + TB_NAME , null);
            if(c == null) return null;

            String name;
            c.moveToFirst();
            do {
                name = c.getString(1);
                listUsers.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }

        db.close();

        return listUsers;
    }

    public Integer getQuestionsNumber(String idPrzedmiotu, String year){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        String TB_NAME = "Pytanie";
        String countStr="0";

        try {
            if (year == "") {
                c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + " WHERE IdPrzedmiotu = " + idPrzedmiotu, null);
            } else {
                c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + " WHERE IdPrzedmiotu = " + idPrzedmiotu + " AND rok = " + year, null);
            }
            if(c == null) return null;

            c.moveToFirst();
            countStr = c.getString(0);
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return Integer.parseInt(countStr);
    }

    public List<String> getQuestionsYears(String idPrzedmiotu){
        List<String> listYears = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        listYears.add("ALL");
        try {
            c = db.rawQuery("SELECT DISTINCT rok FROM Pytanie WHERE IdPrzedmiotu = " + idPrzedmiotu, null);
            if(c == null) return listYears;
            String name;
            c.moveToFirst();
            do {
                name = c.getString(0);
                listYears.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return listYears;
    }

}
