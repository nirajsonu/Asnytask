package com.neeraj.asnytask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class api extends AppCompatActivity {
ArrayList<String> titles=new ArrayList<>();
ArrayAdapter arrayAdapter;
ListView l;
TextView t;
String s;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api);
        t=findViewById(R.id.t);
        down task=new down();
        try
        {
            task.execute("http://192.168.1.7/Neeraj/getAll.php");
        }catch (Exception e)
        {

        }
    }
    public class down extends AsyncTask<String,Void,String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(urls[0]);
                urlConnection= (HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                int data=inputStreamReader.read();
                while (data !=-1)
                {
                    char current=(char)data;
                    result +=current;
                    data=inputStreamReader.read();
                }
                s=result;
                return result;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONArray arrJsonArray = new JSONArray("AboveJsonString");
                for (int i = 0; i < arrJsonArray.length(); i++) {
                    JSONObject object = arrJsonArray.getJSONObject(i);
                    int id= Integer.parseInt(object.getString("id"));
                    String Name= object.getString("name");
                    String  Course=object.getString("course");
                }
                t.append(s+"");
            } catch (Exception e) {

            }
        }
        }
    }

