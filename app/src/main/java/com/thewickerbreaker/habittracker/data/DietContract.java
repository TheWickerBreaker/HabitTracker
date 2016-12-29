package com.thewickerbreaker.habittracker.data;

import android.provider.BaseColumns;

public class DietContract {
    private DietContract() {
    }

    public static final class MealEntry implements BaseColumns {
        public static final String TABLE_NAME = "meals";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_MEAL = "meal";
        public static final String COLUMN_MEAL_DESCRIPTION = "description";
        public static final String COLUMN_MEAL_CALORIES = "calories";
        public static final String COLUMN_MEAL_PROTEIN = "protein";
        public static final String COLUMN_MEAL_FAT = "fat";
        public static final String COLUMN_MEAL_CARBS = "carbohydrates";
        public static final String COLUMN_MEAL_TIME = "mealtime";

        /**
         * Possible values for the type of meal.
         */
        public static final int MEAL_SNACK = 0;
        public static final int MEAL_BREAKFAST = 1;
        public static final int MEAL_LUNCH = 2;
        public static final int MEAL_DINNER = 3;
    }
}
