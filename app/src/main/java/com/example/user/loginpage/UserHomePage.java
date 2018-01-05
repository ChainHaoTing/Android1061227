package com.example.user.loginpage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class UserHomePage extends AppCompatActivity {

    TextView tv_Welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_home_page);

        tv_Welcome = (TextView) findViewById(R.id.tvWelcome);

        Bundle bundle = this.getIntent().getExtras();
        tv_Welcome.setText("Welcome, " + bundle.getString("KEY_USERNAME"));
    }
}
