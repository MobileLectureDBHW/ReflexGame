package reflexgame.dietzm.de.reflexgame.db;

import android.provider.BaseColumns;

/**
 * Created by michael on 01.09.15.
 */
public class ReflexGameContract {




    public class HighscoreContract implements BaseColumns{

        public static final String TABLE_NAME = "Highscore";
        public static final String COLUMN_PLAYERNAME = "Playername";
        public static final String COLUMN_LEVEL = "Level";
        public static final String COLUMN_POINTS = "Points";

    }

}
