package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.endpoint.EndpointAsyncTask;
import com.udacity.gradle.builditbigger.utils.ApplicationConfig;

import net.nsreverse.jokedisplayer.DisplayJokeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * This class is one of two entry points into the app. This entry point is for free downloaders.
 *
 * @author Robert
 * Created on 6/14/2017
 */
public class MainActivity extends AppCompatActivity
                          implements EndpointAsyncTask.Delegate {

    @BindView(R.id.progress_bar_indicator) ProgressBar mLoadingProgressBar;

    /**
     * This method creates/inflates the view and provides additional setup.
     *
     * @param savedInstanceState A Bundle containing saved state information (if previously
     *                           exists).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mLoadingProgressBar.setIndeterminate(true);
    }

    /**
     * This method inflates the Menu of the app. Unnecessary.
     *
     * @param menu The Menu to inflate the context menu to.
     * @return A boolean representing if the menu was successfully inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * This method handles when MenuItem was selected.
     *
     * @param item A MenuItem representing the selected item.
     * @return A boolean representing if the action was handled.
     */
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

    /**
     * This method is handled as an onClick method for the button contained in the fragment's
     * layout.
     *
     * @param view A View object representing the selected object.
     */
    public void tellJoke(View view) {
        mLoadingProgressBar.setVisibility(View.VISIBLE);
        new EndpointAsyncTask().execute(this);
    }

    /**
     * This method is a callback for the EndpointAsyncTask used to retrieve a joke.
     *
     * @param response A String representing the response sent from the server.
     * @param error A String representing if something went wrong. Null if no issues executing
     *              the task.
     */
    @Override
    public void taskFinishedWithJson(String response, String error) {
        if (error == null) {
            Intent intent = new Intent(this, DisplayJokeActivity.class);
            intent.putExtra(DisplayJokeActivity.JOKE_KEY, response);
            startActivity(intent);
        }
        else {
            if (ApplicationConfig.loggingEnabled) Timber.d(error);
        }

        mLoadingProgressBar.setVisibility(View.INVISIBLE);
    }
}
