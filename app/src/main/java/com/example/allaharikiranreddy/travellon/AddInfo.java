package com.example.allaharikiranreddy.travellon;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class AddInfo extends Activity {

    EditText Name,Username,Password,Mobile;
    String name,username,password,mobile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_info_layout);
        Name = (EditText)findViewById(R.id.et_name);
        Username = (EditText)findViewById(R.id.et_username);
        Password = (EditText)findViewById(R.id.et_password);
        Mobile = (EditText)findViewById(R.id.et_mobile);

    }

    public void Register(View view)
    {
         name = Name.getText().toString();
         username = Username.getText().toString();
         password= Password.getText().toString();
         mobile = Mobile.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask();
        backgroundTask.execute(name,username,password,mobile);
        finish();
    }


    class BackgroundTask extends AsyncTask<String,Void,String>
    {
        String add_info_url;

        @Override
        protected void onPreExecute() {
            add_info_url = "http://allaharikiranreddy.netau.net/add_info.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String name,username,password,mobile;
            name = args[0];
            username = args[1];
            password = args[2];
            mobile = args[3];
            try {
                URL url = new URL(add_info_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data_string = URLEncoder.encode("name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("username","UTF-8")+"="+URLEncoder.encode(username,"UTF-8")+"&"+
                        URLEncoder.encode("password","UTF-8")+"="+URLEncoder.encode(password,"UTF-8")+"&"+
                        URLEncoder.encode("mobile","UTF-8")+"="+URLEncoder.encode(mobile,"UTF-8");

                bufferedWriter.write(data_string);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();
                httpURLConnection.disconnect();
                return "One row of data inserted...";

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
        }





    }




}
