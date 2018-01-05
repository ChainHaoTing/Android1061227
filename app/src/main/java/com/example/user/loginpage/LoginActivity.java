package com.example.user.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    EditText et_UserName;
    EditText et_Password;
    Button btn_SignIn;
    TextView tv_Register;

    static final String PREF = "Login_PREF";      //偏好設定檔 檔名
    static final String PREF_USERNAME = "Login_UserName";
    static final String PREF_PASSWORD = "Login_Password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findViews();
    }

    private void findViews() {
        et_UserName = (EditText) findViewById(R.id.etUserName);
        et_Password = (EditText) findViewById(R.id.etPassword);
        btn_SignIn = (Button) findViewById(R.id.btnSignIn);
        btn_SignIn.setOnClickListener(this);
        tv_Register = (TextView) findViewById(R.id.tvRegister);
        tv_Register.setOnClickListener(new View.OnClickListener()       //TwxtView 事件
        {
            public void onClick(View v)
            {
                Intent intent = new Intent();   //建立物件
                intent.setClass(LoginActivity.this,RegisterActivity.class);  //設定 來源,目的地
                startActivity(intent);
            }
        });
    }


    @Override
    public void onClick(View view) {

        try {
            String UserName = et_UserName.getText().toString();
            String Password = et_Password.getText().toString();
            //取得帳號資料---------------------------------------------------------------------
            SharedPreferences settings = getSharedPreferences(PREF+UserName, 0);
            String pref_username = settings.getString(PREF_USERNAME, "");
            String pref_password = settings.getString(PREF_PASSWORD, "");
            //--------------------------------------------------------------------------
            if(UserName.equals("") || Password.equals("")){
                Toast.makeText(LoginActivity.this,"欄位不可空白", Toast.LENGTH_SHORT).show();
            }
            else if(UserName.equals(pref_username) && Password.equals(pref_password)){
                Toast.makeText(LoginActivity.this,"帳號驗證成功", Toast.LENGTH_SHORT).show();
                goUserHomePage();   //呼叫切換到使用者頁面 方法
            }
            else{
                Toast.makeText(LoginActivity.this,"帳號驗證失敗", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        SharedPreferences settings = getSharedPreferences(PREF, 0);
//        SharedPreferences.Editor editor = settings.edit();
//        editor.clear();   //清除時 用
//        editor.apply();

    }

    private void goUserHomePage(){      //切換到使用者頁面 方法
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,UserHomePage.class);  //設定 來源,目的地
        Bundle bundle = new Bundle();
        bundle.putString("KEY_USERNAME", et_UserName.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
