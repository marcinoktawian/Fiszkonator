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
    private static String DB_NAME = "Fiszka.db";
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

    public List<String> getAllNames(String TB_NAME, String starter) {
        List<String> listUsers = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;

        try {
            if (!starter.equals("")) {
                listUsers.add("0");
                listUsers.add("ALL");

                c = db.rawQuery("SELECT distinct k.* FROM Kategoria k JOIN FISZKA f ON k.IdKategorii = f.IdKategorii JOIN Zestaw z ON z.IdZestawu=f.IdZestawu WHERE z.NazwaZestawu = \"" + starter + "\"", null);
            } else {
                c = db.rawQuery("SELECT * FROM " + TB_NAME, null);
            }
            if (c == null) return null;

            String name;
            String id;
            c.moveToFirst();

            do {
                id = c.getString(0);
                name = c.getString(1);
                listUsers.add(id);
                listUsers.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }

        db.close();
        return listUsers;
    }


    public Integer getQuestionsNumber(String idOpcji, String level, String learnLevel, String option) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        String TB_NAME = "Fiszka";
        String countStr = "0";
        String optionQuery;
        if (option.equals("Kategoria")) {
            optionQuery = " WHERE IdKategorii = " + idOpcji;
        } else {
            optionQuery = " WHERE IdZestawu = " + idOpcji;
        }

        try {
            if (level.equals("")) {
                if (learnLevel.equals("")) {
                    c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + optionQuery, null);
                } else {
                    c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + " f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki" + optionQuery + " AND s.PoziomNauczenia = " + learnLevel, null);
                }

            } else {
                if (learnLevel.equals("")) {
                    c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + optionQuery + " AND Poziom = \"" + level + "\"", null);
                } else {
                    c = db.rawQuery("SELECT COUNT(*) FROM " + TB_NAME + " f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki" + optionQuery + " AND f.Poziom = \"" + level + "\" AND s.PoziomNauczenia = " + learnLevel, null);
                }
            }
            if (c == null) return null;

            c.moveToFirst();
            countStr = c.getString(0);
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return Integer.parseInt(countStr);
    }

    public List<String> getQuestionsLevels(String idOpcji, String option) {
        List<String> listLevels = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        listLevels.add("ALL");
        try {
            if (option.equals("Kategoria")) {
                c = db.rawQuery("SELECT DISTINCT Poziom FROM Fiszka WHERE IdKategorii = " + idOpcji + " ORDER BY Poziom DESC", null);
            } else {
                c = db.rawQuery("SELECT DISTINCT Poziom FROM Fiszka WHERE IdZestawu = " + idOpcji + " ORDER BY Poziom DESC", null);
            }
            if (c == null) return listLevels;
            String name;
            c.moveToFirst();
            do {
                name = c.getString(0);
                listLevels.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return listLevels;
    }

    public List<String> getLearnLevels(String idOpcji, String option) {
        List<String> listLevels = new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c;
        listLevels.add("ALL");
        try {
            if (option.equals("Kategoria")) {
                c = db.rawQuery("SELECT DISTINCT s.PoziomNauczenia FROM Fiszka f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki WHERE f.IdKategorii = " + idOpcji + " ORDER BY s.PoziomNauczenia DESC", null);
            } else {
                c = db.rawQuery("SELECT DISTINCT s.PoziomNauczenia FROM Fiszka f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki WHERE f.IdZestawu = " + idOpcji + " ORDER BY s.PoziomNauczenia DESC", null);
            }
            if (c == null) return listLevels;
            String name;
            c.moveToFirst();
            do {
                name = c.getString(0);
                listLevels.add(name);
            } while (c.moveToNext());
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return listLevels;
    }

    public String getCountInLevel(String idOpcji, String option, String level) {
        SQLiteDatabase db = this.getWritableDatabase();
        String count = "0";
        Cursor c;
        try {
            if (option.equals("Kategoria")) {
                c = db.rawQuery("SELECT COUNT(*) FROM Fiszka f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki WHERE f.IdKategorii = " + idOpcji + " AND s.PoziomNauczenia = " + level, null);
            } else {
                c = db.rawQuery("SELECT COUNT(*) FROM Fiszka f JOIN Statystyki s ON f.IdFiszki = s.IdFiszki WHERE f.IdZestawu = " + idOpcji + " AND s.PoziomNauczenia = " + level, null);
            }
            if (c == null) return "0";
            c.moveToFirst();
            count = c.getString(0);
            c.close();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        db.close();
        return count;
    }

}
