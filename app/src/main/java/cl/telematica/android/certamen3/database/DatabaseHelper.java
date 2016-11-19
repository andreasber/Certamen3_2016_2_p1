package cl.telematica.android.certamen3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.android.certamen3.models.Feed;

/**
 * Created by Andreas on 18.11.2016.
 */

public class DatabaseHelper {

    private static DatabaseHelper instance;

    private FeedDatabase feedDatabase;

    public static DatabaseHelper getInstance() {
        if(instance == null) {
            instance = new DatabaseHelper();
        }
        return instance;
    }


    public void deleteOneFromDatabase(Feed feed) {
        SQLiteDatabase db = feedDatabase.getWritableDatabase();
        String title = feed.getTitle();
        if (db != null) {
            db.beginTransaction();
            try {
                db.execSQL("DELETE FROM feeds WHERE title = '" + title + "'");
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }

    public void saveOneToDatabase(Feed feed){
        SQLiteDatabase db = feedDatabase.getWritableDatabase();

        if(db != null){
            db.beginTransaction();
            try {
                db.execSQL("INSERT OR IGNORE INTO feeds (title, link, author, publishedDate, content, image, isFavorite) VALUES ('" + feed.getTitle() + "', '" + feed.getLink() + "','" + feed.getAuthor() + "','" + feed.getPublishedDate() + "','" + feed.getContent() + "','" + feed.getImage() + "','" + feed.isFavorite() +  "')");
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }


    public void saveToDatabase(List<Feed> list) {
        SQLiteDatabase db = feedDatabase.getWritableDatabase();
        if (db != null) {
            db.beginTransaction();
            try {
                for (Feed feed : list) {
                    db.execSQL("INSERT OR IGNORE INTO feeds (title, link, author, publishedDate, content, image, isFavorite) VALUES ('" + feed.getTitle() + "', '" + feed.getLink() + "','" + feed.getAuthor() + "','" + feed.getPublishedDate() + "','" + feed.getContent() + "','" + feed.getImage() + "','" + feed.isFavorite() +  "')");
                }
            } finally {
                db.setTransactionSuccessful();
            }
            db.endTransaction();
            db.close();
        }
    }


    public List<Feed> getAllDBEntries() {
        SQLiteDatabase db = feedDatabase.getReadableDatabase();
        List<Feed> entries = new ArrayList<Feed>();
        Cursor cursor = db.rawQuery("SELECT * FROM feeds", null);

        if(cursor.moveToFirst()){
            do{
                String title = cursor.getString(1);
                String link = cursor.getString(2);
                String author = cursor.getString(3);
                String publishedDate = cursor.getString(4);
                String content = cursor.getString(5);
                String image = cursor.getString(6);
                boolean isFavorite;
                if(cursor.getInt(6)==1){
                    isFavorite = true;
                    entries.add(new Feed(title, link, author, publishedDate, content, image, isFavorite));
                } else {
                    isFavorite = true;
                    entries.add(new Feed(title, link, author, publishedDate, content, image, isFavorite));
                }
            } while(cursor.moveToNext());
        }
        cursor.close();
        return entries;
    }


    public void initDatabase (Context ctx){
        this.feedDatabase = new FeedDatabase(ctx);
    }

}
