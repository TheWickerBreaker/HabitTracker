package com.thewickerbreaker.habittracker.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.thewickerbreaker.habittracker.data.DietContract.MealEntry;

import static com.thewickerbreaker.habittracker.data.DietContract.MealEntry.COLUMN_MEAL_TIME;

public class DietDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = DietDbHelper.class.getSimpleName();

    /**
     * Name of the database file
     */
    private static final String DATABASE_NAME = "diet.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link DietDbHelper}.
     *
     * @param context of the app
     */
    public DietDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the meal table
        String SQL_CREATE_MEAL_TABLE = "CREATE TABLE " + MealEntry.TABLE_NAME + " ("
                + MealEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + MealEntry.COLUMN_MEAL + " INTEGER NOT NULL DEFAULT 0, "
                + MealEntry.COLUMN_MEAL_DESCRIPTION + " TEXT NOT NULL, "
                + MealEntry.COLUMN_MEAL_CALORIES + " INTEGER NOT NULL, "
                + MealEntry.COLUMN_MEAL_PROTEIN + " INTEGER, "
                + MealEntry.COLUMN_MEAL_FAT + " INTEGER, "
                + MealEntry.COLUMN_MEAL_CARBS + " INTEGER, "
                + MealEntry.COLUMN_MEAL_TIME + " INTEGER NOT NULL DEFAULT (strftime('%s', 'now')));";

        Log.e("Fucker", COLUMN_MEAL_TIME);
        // Execute the SQL statement
        db.execSQL(SQL_CREATE_MEAL_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
