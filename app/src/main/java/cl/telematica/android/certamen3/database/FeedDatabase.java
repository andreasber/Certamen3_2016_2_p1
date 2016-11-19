package cl.telematica.android.certamen3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Andreas on 18.11.2016.
 */

public class FeedDatabase extends SQLiteOpenHelper {

    private static final String DB_NAME = "FeedDB";
    private static final int DB_version = 1;

    String sqlCreate = "CREATE TABLE IF NOT EXISTS 'feeds' ('id' INTEGER PRIMARY KEY, 'title' TEXT, 'link' TEXT, 'author' TEXT, 'publishedDate' TEXT, 'content' TEXT, 'image' TEXT, 'isFavorite' INTEGER)";


    public FeedDatabase(Context context) {
        super(context, DB_NAME, null, DB_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS feeds");
        onCreate(db);
    }

}
