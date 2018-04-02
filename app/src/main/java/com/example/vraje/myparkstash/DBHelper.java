package com.example.vraje.myparkstash;
/**
 * Created by vrajesh on 3/27/2018.
 */
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
public class DBHelper extends android.database.sqlite.SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Mymap.db";
    public static final String LONG_LAT_TABLE_NAME = "long_lat";
    public static final String LONG_LAT_COLUMN_ID = "id";
    public static final String LONG_LAT_COLUMN_LONGITUTE = "longitute";
    public static final String CLONG_LAT_COLUMN_LATITUTE= "latitute";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table long_lat " +
                        "(id integer primary key, longitute double,latitute double)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS long_lat");
        onCreate(db);
    }

    public boolean insertdata (double longitute, double latitute) {              // insert LongLat to Database
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("longitute", longitute);
        contentValues.put("latitute", latitute);
        db.insert("long_lat", null, contentValues);
        return true;
    }
    public void deletedata () {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ LONG_LAT_TABLE_NAME);

    }

    public ArrayList<MyObject> getLetLong() {                              // Get data from DB in Arraylist

        ArrayList<MyObject> list = new ArrayList<MyObject>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + LONG_LAT_TABLE_NAME;

        SQLiteDatabase db = this.getReadableDatabase();
        try {

            Cursor cursor = db.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        MyObject obj = new MyObject();
                        //only one column
                        obj.setId(cursor.getInt(0));
                        obj.setlong(cursor.getDouble(1));
                        obj.setlat(cursor.getDouble(2));
                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { db.close(); } catch (Exception ignore) {}
        }

        return list;
    }
}
