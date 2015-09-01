package reflexgame.dietzm.de.reflexgame.db;

import android.content.Context;
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
}
