package com.example.allaharikiranreddy.travellon;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class userlogin extends Activity {

        EditText ET_NAME,ET_PASS;
        String login_name,login_pass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login_layout);

        ET_NAME = (EditText)findViewById(R.id.et_uname);
        ET_PASS = (EditText)findViewById(R.id.et_upass);

    }

    public void Login(View view)
    {
        login_name = ET_NAME.getText().toString();
        login_pass = ET_PASS.getText().toString();
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(login_name,login_pass);
        finish();

    }


    public class BackgroundTask extends AsyncTask<String,Void,String>
    {
        Context ctx;
        BackgroundTask(Context ctx)
        {
            this.ctx = ctx;
        }
        String userlogin_url;

        @Override
        protected void onPreExecute() {
            userlogin_url = "http://allaharikiranreddy.netau.net/login.php";
        }

        @Override
        protected String doInBackground(String... args) {
            String login_name,login_pass;
            login_name = args[0];
            login_pass = args[1];

            try {
                URL url = new URL(userlogin_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);

                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

                String data = URLEncoder.encode("login_name","UTF-8")+"="+URLEncoder.encode(login_name,"UTF-8")+"&"+
                        URLEncoder.encode("login_pass","UTF-8")+"="+URLEncoder.encode(login_pass,"UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null)
                {
                    response+=line;
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

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
