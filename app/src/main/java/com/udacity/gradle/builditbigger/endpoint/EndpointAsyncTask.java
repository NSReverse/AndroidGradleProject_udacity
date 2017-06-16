package com.udacity.gradle.builditbigger.endpoint;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.jokebackend.myApi.MyApi;

import java.io.IOException;

/**
 * This class connects to the backend GCE module asynchronously.
 *
 * @author Robert
 * Created on 6/14/2017.
 */
public class EndpointAsyncTask extends AsyncTask<Context, Void, String> {

    private static MyApi myApiService = null;
    private Delegate myDelegate;
    @SuppressWarnings("FieldCanBeLocal") private Context myContext;

    private String error = null;

    /**
     * This interface must be implemented by the calling object to recieve callback events.
     */
    public interface Delegate {
        void taskFinishedWithJson(String json, String error);
    }

    /**
     * This method executes logic in the background.
     *
     * @param params A vararg set of parameters. Only the first is used to retrieve a supplied
     *               Context
     * @return A String representing the result of this task.
     */
    @Override
    protected String doInBackground(Context... params) {
        String rootAddress = "10.0.2.2";    // Default for testing. Cannot be in res/strings
        String rootAddressPort = "8080";    // Default for testing. Cannot be in res/strings

        if (params[0] != null) {
            myContext = params[0];                  // For getting resources.
            myDelegate = (Delegate)myContext;       // For callbacks.

            rootAddress = myContext.getString(R.string.local_dev_server_endpoint);
            rootAddressPort = myContext.getString(R.string.local_dev_server_endpoint_port);
        }

        if (myApiService == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://" + rootAddress + ":" + rootAddressPort + "/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request)
                                throws IOException {

                            request.setDisableGZipContent(true);
                        }
                    });

            myApiService = builder.build();
        }

        try {
            return myApiService.randomJoke().execute().getData();
        }
        catch (IOException ex) {
            error = ex.getMessage();

            return null;
        }
    }

    /**
     * This method handles post-execution tasks.
     *
     * @param s The result String of this task.
     */
    @Override
    protected void onPostExecute(String s) {
        if (myDelegate != null) {
            myDelegate.taskFinishedWithJson(s, error);
        }
    }
}
