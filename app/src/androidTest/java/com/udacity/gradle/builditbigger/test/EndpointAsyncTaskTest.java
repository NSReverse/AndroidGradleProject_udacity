package com.udacity.gradle.builditbigger.test;

import android.content.Context;

import com.udacity.gradle.builditbigger.endpoint.EndpointAsyncTask;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.fail;

/**
 * Created by Robert on 6/15/2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class EndpointAsyncTaskTest {

    @Mock
    Context mContext;

    @Test
    public void testAsyncTask() {
        try {
            String result = new EndpointAsyncTask().execute((Context)null).get();
            assertNotNull(result);
        }
        catch (Exception ex) {
            fail("Exception has occurred. Failing test: " + ex.getMessage());
        }
    }
}