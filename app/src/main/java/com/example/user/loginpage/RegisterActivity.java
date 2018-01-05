package com.example.user.loginpage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity{

    EditText et_Email;
    EditText et_UserName;
    EditText et_Password;
    EditText et_Age;
    Button btn_Register;

    static final String PREF = "Login_PREF";      //偏好設定檔 檔名
    static final String PREF_EMAIL = "Login_Email";
    static final String PREF_USERNAME = "Login_UserName";
    static final String PREF_PASSWORD = "Login_Password";
    static final String PREF_AGE = "Login_Age";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        findViews();
    }

    private void findViews() {
        et_Email = (EditText) findViewById(R.id.etEmail);
        et_UserName = (EditText) findViewById(R.id.etUserName);
        et_Password = (EditText) findViewById(R.id.etPassword);
        et_Age = (EditText) findViewById(R.id.etAge);
        btn_Register = (Button) findViewById(R.id.btnSignIn);
    }

    public void Register(View view) {       //用 activity_register Button 事件onClick 呼叫

        String Email = et_Email.getText().toString();
        String UserName = et_UserName.getText().toString();
        String Password = et_Password.getText().toString();
        String Age = et_Age.getText().toString();

        SharedPreferences settings = getSharedPreferences(PREF+UserName, 0);
        String pref_email = settings.getString(PREF_EMAIL, "");
        String pref_username = settings.getString(PREF_USERNAME, "");

        if((Email.equals("") || UserName.equals("")) || (Password.equals("") || Age.equals(""))){
            Toast.makeText(RegisterActivity.this,"欄位不可空白", Toast.LENGTH_SHORT).show();
        }
        else if(pref_email.equals(Email) || pref_username.equals(UserName)){
            if(pref_email.equals(Email) && pref_username.equals(UserName)){     //若Email 和 UserName 已被註冊 並且游標到 Email 欄位
                Toast.makeText(RegisterActivity.this,"此 Email 和 UserName 已被註冊", Toast.LENGTH_SHORT).show();
                et_Email.requestFocus();
            }
            else if(pref_email.equals(Email)){              //若Email  已被註冊 並且游標到 Email 欄位
                Toast.makeText(RegisterActivity.this,"此 Email 已被註冊", Toast.LENGTH_SHORT).show();
                et_Email.requestFocus();
            }
            else if(pref_username.equals(UserName)){         //若 UserName 已被註冊 並且游標到 UserName 欄位
                Toast.makeText(RegisterActivity.this,"此 UserName 已被註冊", Toast.LENGTH_SHORT).show();
                et_UserName.requestFocus();
            }
        }
        else{       //儲存資料 並跳到使用者頁面
            //SharedPreferences settings = getSharedPreferences(PREF, 0);
            SharedPreferences.Editor editor = settings.edit();  //建立物件
            editor.putString(PREF_EMAIL,et_Email.getText().toString());
            editor.putString(PREF_USERNAME,et_UserName.getText().toString());
            editor.putString(PREF_PASSWORD,et_Password.getText().toString());
            editor.putString(PREF_AGE,et_Age.getText().toString());
            editor.apply();

            Toast.makeText(RegisterActivity.this,"註冊成功", Toast.LENGTH_SHORT).show();
            goUserHomePage();
        }
    }

    private void goUserHomePage(){          //切換到使用者頁面 方法
        Intent intent = new Intent();   //建立物件
        intent.setClass(RegisterActivity.this,UserHomePage.class);  //設定 來源,目的地
        Bundle bundle = new Bundle();
        bundle.putString("KEY_USERNAME", et_UserName.getText().toString());
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
