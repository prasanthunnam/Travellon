package com.example.allaharikiranreddy.travellon;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class verify extends Activity {
        EditText ET_VERIFY;
        String v_verify;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_layout);
        ET_VERIFY = (EditText)findViewById(R.id.et_verify);
    }

    public void verify(View view) {

        v_verify = ET_VERIFY.getText().toString();
    }
}
