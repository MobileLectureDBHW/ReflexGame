package reflexgame.dietzm.de.reflexgame.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by michael on 01.09.15.
 */
public class ReflexGameDBHelper extends SQLiteOpenHelper {

    public ReflexGameDBHelper(Context context){
        super(context, "REFLEX_GAME_DB", null, 1);
    }

    public ReflexGameDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + ReflexGameContract.HighscoreContract.TABLE_NAME
                    +   " ( " +
                        " " + ReflexGameContract.HighscoreContract._ID + " INTEGER PRIMARY KEY,"+
                        " " + ReflexGameContract.HighscoreContract.COLUMN_PLAYERNAME + " NVARCHAR(200)," +
                        " " + ReflexGameContract.HighscoreContract.COLUMN_LEVEL + " INTEGER," +
                        " " + ReflexGameContract.HighscoreContract.COLUMN_POINTS + " INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void addHighscoreEntry(String player, int level, int pts){

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ReflexGameContract.HighscoreContract.COLUMN_PLAYERNAME, player);
        values.put(ReflexGameContract.HighscoreContract.COLUMN_LEVEL, level);
        values.put(ReflexGameContract.HighscoreContract.COLUMN_POINTS, pts);

        db.insert(ReflexGameContract.HighscoreContract.TABLE_NAME,null,values);

    }

    public void getAllHighscoreEntries(){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = new String[]{
            ReflexGameContract.HighscoreContract.COLUMN_PLAYERNAME,
            ReflexGameContract.HighscoreContract.COLUMN_LEVEL,
            ReflexGameContract.HighscoreContract.COLUMN_POINTS
        };

        String sort = ReflexGameContract.HighscoreContract.COLUMN_POINTS + " DESC";

        Cursor c = db.query(
             ReflexGameContract.HighscoreContract.TABLE_NAME,
             projection,
                null,
                null,
                null,
                null,
                sort
        );

        while(c.moveToNext()){
            String playername = c.getString(0);
            int level = c.getInt(1);
            int points = c.getInt(2);
            System.out.println("REFLEXGAME HIGHSCORE: " + playername + " " + level + " " + points);
        }



    }

    public void getHighscoreForPlayer(String playername){

        SQLiteDatabase db = getReadableDatabase();

        String[] projection = new String[]{
                ReflexGameContract.HighscoreContract.COLUMN_PLAYERNAME,
                ReflexGameContract.HighscoreContract.COLUMN_LEVEL,
                ReflexGameContract.HighscoreContract.COLUMN_POINTS
        };

        String where = ReflexGameContract.HighscoreContract.COLUMN_PLAYERNAME + " = ? " +
                        " AND " +
                        ReflexGameContract.HighscoreContract.COLUMN_LEVEL + " > ?";

        String[] whereArg = new String[]{ playername, "1" };

        String sort = ReflexGameContract.HighscoreContract.COLUMN_POINTS + " DESC";

        db.query(
                ReflexGameContract.HighscoreContract.TABLE_NAME,
                projection,
                where,
                whereArg,
                null,
                null,
                sort
        );




    }




}
