package com.example.tineshia.outout.sql;

/**
 * Created by Tineshia on 12/03/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.tineshia.outout.model.Plan;
import com.example.tineshia.outout.model.User;

import java.util.ArrayList;
import java.util.List;

    public class DatabaseHelper extends SQLiteOpenHelper {

        // Database Version
        private static final int DATABASE_VERSION = 1;

        // Database Name
        private static final String DATABASE_NAME = "UserManager.db";

        // User table name
        private static final String TABLE_USER = "user";
        // User table name
        private static final String TABLE_PLANLIST = "planlist";

        // User Table Columns names
        private static final String COLUMN_USER_ID = "user_id";
        private static final String COLUMN_USER_NAME = "user_name";
        private static final String COLUMN_USER_EMAIL = "user_email";
        private static final String COLUMN_USER_PASSWORD = "user_password";

        // PLANLIST Table Columns names
        private static final String COLUMN_PLANLIST_ID = "plan_id";
        private static final String COLUMN_PLANLIST_DATE = "plan_date";
        private static final String COLUMN_PLANLIST_EIDARR = "plan_eidArr";

        // create table sql query = USER
        private String CREATE_USER_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_USER_NAME + " TEXT,"
                + COLUMN_USER_EMAIL + " TEXT," + COLUMN_USER_PASSWORD + " TEXT" + ")";

        // create table sql query = PLANLIST
        private String CREATE_PLANLIST_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLANLIST + "("
                + COLUMN_PLANLIST_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLUMN_PLANLIST_DATE + " TEXT," + COLUMN_PLANLIST_EIDARR + " TEXT" + ")";

        // drop table sql query
        private String DROP_USER_TABLE = "DROP TABLE IF EXISTS " + TABLE_USER;
        private String DROP_PLANLIST_TABLE = "DROP TABLE IF EXISTS " + TABLE_PLANLIST;

        /**
         * Constructor
         *
         * @param context
         */
        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_USER_TABLE);
            db.execSQL(CREATE_PLANLIST_TABLE);
        }


        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            //Drop User Table if exist
            db.execSQL(DROP_USER_TABLE);
            db.execSQL(DROP_PLANLIST_TABLE);

            // Create tables again
            onCreate(db);

        }

        /**
         * This method is to create user record
         *
         * @param user
         */
        public void addUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // Inserting Row
            db.insert(TABLE_USER, null, values);
            db.close();
        }

        /**
         * This method is to fetch all user and return the list of user records
         *
         * @return list
         */
        public List<User> getAllUser() {
            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID,
                    COLUMN_USER_EMAIL,
                    COLUMN_USER_NAME,
                    COLUMN_USER_PASSWORD
            };
            // sorting orders
            String sortOrder =
                    COLUMN_USER_NAME + " ASC";

            List<User> userList = new ArrayList<User>();

            SQLiteDatabase db = this.getReadableDatabase();

            // query the user table
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,    //columns to return
                    null,        //columns for the WHERE clause
                    null,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    sortOrder); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    User user = new User();
                    user.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_USER_ID))));
                    user.setName(cursor.getString(cursor.getColumnIndex(COLUMN_USER_NAME)));
                    user.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_USER_EMAIL)));
                    user.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_USER_PASSWORD)));
                    // Adding user record to list
                    userList.add(user);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return userList;
        }

        /**
         * This method to update user record
         *
         * @param user
         */
        public void updateUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_USER_NAME, user.getName());
            values.put(COLUMN_USER_EMAIL, user.getEmail());
            values.put(COLUMN_USER_PASSWORD, user.getPassword());

            // updating row
            db.update(TABLE_USER, values, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }

        /**
         * This method is to delete user record
         *
         * @param user
         */
        public void deleteUser(User user) {
            SQLiteDatabase db = this.getWritableDatabase();
            // delete user record by id
            db.delete(TABLE_USER, COLUMN_USER_ID + " = ?",
                    new String[]{String.valueOf(user.getId())});
            db.close();
        }

        /**
         * This method to check user exist or not
         *
         * @param email
         * @return true/false
         */
        public boolean checkUser(String email) {

            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();

            // selection criteria
            String selection = COLUMN_USER_EMAIL + " = ?";

            // selection argument
            String[] selectionArgs = {email};

            // query user table with condition
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                      //filter by row groups
                    null);                      //The sort order
            int cursorCount = cursor.getCount();
            cursor.close();
            db.close();

            return cursorCount > 0;

        }

        /**
         * This method to check user exist or not
         *
         * @param email
         * @param password
         * @return true/false
         */
        public boolean checkUser(String email, String password) {

            // array of columns to fetch
            String[] columns = {
                    COLUMN_USER_ID
            };
            SQLiteDatabase db = this.getReadableDatabase();
            // selection criteria
            String selection = COLUMN_USER_EMAIL + " = ?" + " AND " + COLUMN_USER_PASSWORD + " = ?";

            // selection arguments
            String[] selectionArgs = {email, password};

            // query user table with conditions
            /**
             * Here query function is used to fetch records from user table this function works like we use sql query.
             * SQL query equivalent to this query function is
             * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
             */
            Cursor cursor = db.query(TABLE_USER, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order

            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            return cursorCount > 0;

        }


        //Write plan into database
        // PLANLIST Table Columns names
        public void addPlan(String date, String eidArr){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_PLANLIST_DATE, date);
            values.put(COLUMN_PLANLIST_EIDARR, eidArr);

            // Inserting Row
            db.insert(TABLE_PLANLIST, null, values);
            db.close();
        }

        public void updatePlan(String date, String eidArr){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COLUMN_PLANLIST_EIDARR, eidArr);

            // updating row
            db.update(TABLE_PLANLIST, values, COLUMN_PLANLIST_DATE + " = ?",
                    new String[]{String.valueOf(date)});
            db.close();
        }

        public void delPlan(String date){
            SQLiteDatabase db = this.getWritableDatabase();
            // delete user record by id
            db.delete(TABLE_PLANLIST, COLUMN_PLANLIST_DATE + " = ?",
                    new String[]{String.valueOf(date)});
            db.close();
        }

        public boolean checkPlan(String date){
            SQLiteDatabase db = this.getReadableDatabase();
            db.execSQL(CREATE_PLANLIST_TABLE);


            // array of columns to fetch
            String[] columns = {
                    COLUMN_PLANLIST_DATE
            };

            // selection criteria
            String selection = COLUMN_PLANLIST_DATE + " = ?" ;

            // selection arguments
            String[] selectionArgs = {date};

            // query plan with conditions
            Cursor cursor = db.query(TABLE_PLANLIST, //Table to query
                    columns,                    //columns to return
                    selection,                  //columns for the WHERE clause
                    selectionArgs,              //The values for the WHERE clause
                    null,                       //group the rows
                    null,                       //filter by row groups
                    null);                      //The sort order

            int cursorCount = cursor.getCount();

            cursor.close();
            db.close();
            return cursorCount > 0;
        }

        public List<Plan> getPlan(String date) {
            // array of columns to fetch
            String[] columns = {
                    COLUMN_PLANLIST_ID,
                    COLUMN_PLANLIST_DATE,
                    COLUMN_PLANLIST_EIDARR,
            };
            List<Plan> planlist = new ArrayList<Plan>();

            String selection = COLUMN_PLANLIST_DATE + " = ?" ;
            String[] selectionArgs = {date};

            SQLiteDatabase db = this.getReadableDatabase();

            Cursor cursor = db.query(TABLE_PLANLIST, //Table to query
                    columns,    //columns to return
                    selection,        //columns for the WHERE clause
                    selectionArgs,        //The values for the WHERE clause
                    null,       //group the rows
                    null,       //filter by row groups
                    null); //The sort order


            // Traversing through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    Plan plan = new Plan();
                    plan.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(COLUMN_PLANLIST_ID))));
                    plan.setDate(cursor.getString(cursor.getColumnIndex(COLUMN_PLANLIST_DATE)));
                    plan.setEidArr(cursor.getString(cursor.getColumnIndex(COLUMN_PLANLIST_EIDARR)));

                    // Adding plan to list
                    planlist.add(plan);
                } while (cursor.moveToNext());
            }
            cursor.close();
            db.close();

            // return user list
            return planlist;
        }

    }

