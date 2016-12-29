package com.thewickerbreaker.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.thewickerbreaker.habittracker.data.DietContract.MealEntry;
import com.thewickerbreaker.habittracker.data.DietDbHelper;

public class MainActivity extends AppCompatActivity {

    private DietDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDbHelper = new DietDbHelper(this);
        insertData();
        testDisplay();

    }



    private void insertData() {
        // Gets the database in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        // and dummy data attributes are the values.
        ContentValues values = new ContentValues();
        values.put(MealEntry.COLUMN_MEAL, MealEntry.MEAL_SNACK);
        values.put(MealEntry.COLUMN_MEAL_DESCRIPTION, "French Toast Slam with 1 Egg");
        values.put(MealEntry.COLUMN_MEAL_CALORIES, "300");
        values.put(MealEntry.COLUMN_MEAL_PROTEIN, "14");
        values.put(MealEntry.COLUMN_MEAL_FAT, "14");
        values.put(MealEntry.COLUMN_MEAL_CARBS, "31");
        values.put(MealEntry.COLUMN_MEAL_TIME, "658");

        long newRowId = db.insert(MealEntry.TABLE_NAME, null, values);

    }

    private Cursor read() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                MealEntry._ID,
                MealEntry.COLUMN_MEAL,
                MealEntry.COLUMN_MEAL_DESCRIPTION,
                MealEntry.COLUMN_MEAL_CALORIES,
                MealEntry.COLUMN_MEAL_PROTEIN,
                MealEntry.COLUMN_MEAL_FAT,
                MealEntry.COLUMN_MEAL_CARBS,
                MealEntry.COLUMN_MEAL_TIME,
        };

        return db.query(
                MealEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null,
                null);


    }

    private void testDisplay(){

        Cursor cursor = read();

        TextView displayView = (TextView) findViewById(R.id.hello);


        try {

            displayView.setText("The meal table contains " + cursor.getCount() + " meals.\n\n");
            displayView.append(MealEntry._ID + " - " +
                    MealEntry.COLUMN_MEAL + " - " +
                    MealEntry.COLUMN_MEAL_DESCRIPTION + " - " +
                    MealEntry.COLUMN_MEAL_CALORIES + " - " +
                    MealEntry.COLUMN_MEAL_PROTEIN + " - " +
                    MealEntry.COLUMN_MEAL_FAT + " - " +
                    MealEntry.COLUMN_MEAL_CARBS + " - " +
                    MealEntry.COLUMN_MEAL_TIME + "\n");


            // Figure out the index of each column
            int idColumnIndex = cursor.getColumnIndex(MealEntry._ID);
            int mealColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL);
            int descriptionColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_DESCRIPTION);
            int caloriesColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_CALORIES);
            int proteinColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_PROTEIN);
            int fatColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_FAT);
            int carbsColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_CARBS);
            int timeColumnIndex = cursor.getColumnIndex(MealEntry.COLUMN_MEAL_TIME);


            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                int currentID = cursor.getInt(idColumnIndex);
                int currentMeal = cursor.getInt(mealColumnIndex);
                String currentDescription = cursor.getString(descriptionColumnIndex);
                int currentCalories = cursor.getInt(caloriesColumnIndex);
                int currentProtein = cursor.getInt(proteinColumnIndex);
                int currentFat = cursor.getInt(fatColumnIndex);
                int currentCarbs = cursor.getInt(carbsColumnIndex);
                int currentTime = cursor.getInt(timeColumnIndex);

                displayView.append(("\n" + currentID + " - " +
                        currentMeal + " - " +
                        currentDescription + " - " +
                        currentCalories + " - " +
                        currentProtein + " - " +
                        currentFat + " - " +
                        currentCarbs + " - " +
                        currentTime));
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }
}
