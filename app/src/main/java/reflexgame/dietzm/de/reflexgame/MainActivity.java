package reflexgame.dietzm.de.reflexgame;

import android.app.Activity;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import reflexgame.dietzm.de.reflexgame.db.ReflexGameDBHelper;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instanzieren der SharedPreferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Holen Playername aus den SharedPreferences
        String playername = sharedPref.getString("PLAYERNAME","");

        //Holen des Levels aus den SharedPreferences
        int level = sharedPref.getInt("LEVEL", 1);





        //Setzen des Playernames im EditText
        EditText playernameInp = (EditText)findViewById(R.id.playernameInp);
        playernameInp.setText(playername);

        //Setzen des Level in der Seekbar
        SeekBar levelInp = (SeekBar)findViewById(R.id.levelInp);
        levelInp.setProgress(level);

        //Toast ausgeben: Willommen + Playername
        Toast toast = Toast.makeText(getApplicationContext(),
                "Welcome back " + playername,
                Toast.LENGTH_LONG);
        toast.show();


        ReflexGameDBHelper dbHelper = new ReflexGameDBHelper(getApplicationContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();



    }





    public void buttonClicked(View view){

        //Holen des Playername aus EditText
        EditText playernameInp = (EditText)findViewById(R.id.playernameInp);

        //Holen des Levels aus der SeekBar
        SeekBar levelInp = (SeekBar)findViewById(R.id.levelInp);

        //Instanzieren der SharedPreferences
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPref.edit();

        //Schreiben den Playername in die SharedPreferences
        editor.putString("PLAYERNAME", playernameInp.getText().toString());

        //Schreiben das Level in die SharedPreferences
        editor.putInt("LEVEL", (int)levelInp.getProgress());


        editor.commit();


        Toast toast = Toast.makeText(getApplicationContext(),
                "Player " + playernameInp.getText().toString() + " registered",
                Toast.LENGTH_LONG);
        toast.show();


        ReflexGameDBHelper dbhelper = new ReflexGameDBHelper(getApplicationContext());
        dbhelper.addHighscoreEntry(
                playernameInp.getText().toString(),
                (int)levelInp.getProgress(),
                (int)(Math.random()*1000));

        dbhelper.getAllHighscoreEntries();






    }

















    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
