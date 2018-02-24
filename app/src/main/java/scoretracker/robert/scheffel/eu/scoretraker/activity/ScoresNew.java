package scoretracker.robert.scheffel.eu.scoretraker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import scoretracker.robert.scheffel.eu.scoretraker.R;

public class ScoresNew extends AppCompatActivity {
    private static final String TAG = "Scores New";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "Create Activity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores_new);



    }
}
