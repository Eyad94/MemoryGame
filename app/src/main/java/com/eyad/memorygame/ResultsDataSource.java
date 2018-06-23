package com.eyad.memorygame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResultsDataSource {
    private final static Long ERROR_INSERT = -1l;

    // Database fields
    private SQLiteDatabase _database;
    private MySQLiteHelper _dbHelper;

    //All colums of the DB
    private String[] _allColumns =
            {MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_LATITUDE, MySQLiteHelper.COLUMN_LONGITUDE, MySQLiteHelper.COLUMN_MATCHES};


    //Constructor
    public ResultsDataSource(Context context) {
        _dbHelper = new MySQLiteHelper(context);
    }


    //open and close the DB
    public void open() throws SQLException {
        _database = _dbHelper.getWritableDatabase();
    }


    public void close() {
        _dbHelper.close();
    }


    public void clearDatabase() {
        String clearDBQuery = "DELETE FROM "+"data";
        _database.execSQL(clearDBQuery);
    }

    public void insertResult(Result resultToSave) {

        if (resultToSave != null) {
            String id = resultToSave.getId();
            String name = resultToSave.getName();
            int matches = resultToSave.getMatches();
            String latitude = resultToSave.getLatitude();
            String longitude = resultToSave.getLongitude();
            ContentValues values = new ContentValues();

            if (name != null) {
                values.put(MySQLiteHelper.COLUMN_ID, id);
                values.put(MySQLiteHelper.COLUMN_NAME, name);
                values.put(MySQLiteHelper.COLUMN_LATITUDE, latitude);
                values.put(MySQLiteHelper.COLUMN_LONGITUDE, longitude);
                values.put(MySQLiteHelper.COLUMN_MATCHES, matches);

                long returnedResponse = _database.insert(MySQLiteHelper.RESULTS_TABLE_NAME, null, values);
                if(ERROR_INSERT == returnedResponse) {
                    Log.d("Text", "Lior: DB: ERROR in inserting Result: name: " + name);
                } else {
                    Log.d("Text", "Lior: DB: inserted new Result: name: " + name );
                }
            }
        }
    }



    @SuppressWarnings("finally")
    private List getAllResults() {

        List results = new ArrayList();

        Cursor cursor = null;
        try {

            cursor = _database.query(MySQLiteHelper.RESULTS_TABLE_NAME,
                    _allColumns, null, null, null, null, null);

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                Result result = createPersonFromCursor(cursor);
                results.add(result);
                cursor.moveToNext();
            }

        } catch(Exception e) {

            e.printStackTrace();

        } finally {	// Make sure to close the cursor

            if(cursor != null) {

                cursor.close();

            }
            return results;
        }
    }


    private List getResultsSorted() {
        List results = getAllResults();
        Collections.sort(results);
        if (results.size() > 10) {
            List tenResults = new ArrayList();
            for (int i = 0; i < 10; i++) {
                Result r = (Result)results.get(i);
                tenResults.add(r);
            }
            return tenResults;
        }
        return results;
    }


    public ArrayList<String> getResults() {
        List list = getResultsSorted();
        ArrayList<String> arrayList = new ArrayList();
        for (int i=0; i<list.size(); i++){
            String s1 = ((Result) list.get(i)).getName();
            String s2 = ((Result) list.get(i)).getMatches() + "";
            String s3 = ((Result) list.get(i)).getLatitude();
            String s4 = ((Result) list.get(i)).getLongitude();
            arrayList.add(s1 + "\n" +"Number of matches: " + s2 + "\n" +"Latitude: " + s3 +  "\n" +"Longitude: " + s4);
        }
        return arrayList;
    }


    private Result createPersonFromCursor(Cursor cursor) {
        Result result = new Result(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getInt(4));
        return result;
    }
}