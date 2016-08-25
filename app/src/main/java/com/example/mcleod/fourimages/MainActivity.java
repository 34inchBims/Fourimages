package com.example.mcleod.fourimages;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.http.HttpResponseCache;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.NetworkInterface;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageview;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageview = (ImageView) findViewById(R.id.imageview1);
    }
    final View.OnClickListener mGlobal_OnClicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.button1:
                    new DownloadImage().execute(getString(R.string.myURl));
                    break;
                case R.id.button2:
                    new DownloadImage().execute(getString(R.string.myURL2));
                    break;
                case R.id.button3:
                    new DownloadImage().execute(getString(R.string.myURL3));
                    break;
                case R.id.button4:
                    new DownloadImage().execute(getString(R.string.myURL4));
                    break;

            }
        }
    };
    public void myClickHandler(View view) {

       mGlobal_OnClicklistener.onClick(view);
    }

    private void setImage (Drawable drawable){
        mImageview.setBackground(drawable);
    }

    public class DownloadImage extends AsyncTask<String, Integer, Drawable>{

        @Override
        protected Drawable doInBackground(String... arg0) {
            return downloadImage(arg0[0]);
        }

        protected void onPostExecute(Drawable image){
            setImage(image);
        }

        private Drawable downloadImage(String _url){
            URL url;
            InputStream in;
            BufferedInputStream buf;

            try{
                url = new URL(_url);
                in = url.openStream();
                buf = new BufferedInputStream(in);
                Bitmap bMap = BitmapFactory.decodeStream(buf);
                if (in != null){
                    in.close();
                }
                if (buf != null) {
                    buf.close();
                }

                return new BitmapDrawable(bMap);

            }catch  (Exception e){
                Log.e("Error reading file", e.toString());
            }return null;
        }
    }

}
