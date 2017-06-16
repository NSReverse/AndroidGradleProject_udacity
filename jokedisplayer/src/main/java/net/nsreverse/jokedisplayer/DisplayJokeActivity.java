package net.nsreverse.jokedisplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * This class handles the displaying of the supplied joke.
 *
 * @author Robert
 * Created on 6/14/2017
 */
public class DisplayJokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView jokeTextView = (TextView)findViewById(R.id.text_view_joke);

        if (getIntent() != null && getIntent().hasExtra(JOKE_KEY)) {
            jokeTextView.setText(getIntent().getStringExtra(JOKE_KEY));
        }
    }
}
