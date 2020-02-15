package com.example.android.beach_agri;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import static android.content.ContentValues.TAG;

public class Retrievebitmap extends AsyncTask<String,Void, Bitmap> {
    @Override
    protected Bitmap doInBackground(String... strings) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(strings[0]);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        ;
    }
}
