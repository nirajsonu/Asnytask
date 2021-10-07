package com.neeraj.asnytask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
ImageView i;
ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        i=findViewById(R.id.i);
        new Downordimage().execute();
    }
    private class Downordimage extends AsyncTask<String,Void, Bitmap>
    {
        @Override
        protected void onPreExecute() {
            pd=new ProgressDialog(MainActivity.this);
            pd.setTitle("Please wait........");
            pd.setCancelable(false);
            pd.setIndeterminate(false);
            pd.setMessage("Downlording");
            pd.show();
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap=null;
                try {
                    InputStream inputStream=new java.net.URL("https://www.bigstockphoto.com/images/homepage/module-6.jpg").openStream();
                    bitmap= BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            pd.dismiss();
            i.setImageBitmap(bitmap);
        }
    }
}
