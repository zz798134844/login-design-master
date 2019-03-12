package android.example.myapplication;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        //setContentView(R.layout.layout_login);
        //setContentView(R.layout.activity_main);
    }

    private EditText etUserName, etEMail, etPassword, letEMail, letPassword;
    public Button bLogin, bRegister;
    private String strUserName;
    private String strEMail;
    private String strPassword;


    public void Register(View view) {
        etUserName = findViewById(R.id.name);
        etEMail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        bRegister = findViewById(R.id.btnRegister);
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        strUserName = etUserName.getText().toString();
        strEMail = etEMail.getText().toString();
        strPassword = etPassword.getText().toString();
        if(strUserName.equals(null)||strEMail.equals(null)||strPassword.equals(null)){
            Toast.makeText(this, "不能为空", Toast.LENGTH_SHORT).show();
        }
        else{
            editor.putString("UserName", strUserName);
            editor.putString("EMail", strEMail);
            editor.putString("Password", strPassword);
            editor.commit();
            Toast.makeText(this, "点击下面的按键登录", Toast.LENGTH_SHORT).show();
        }
    }


    public void Login(View view) {
        letEMail = findViewById(R.id.ID_email);
        letPassword = findViewById(R.id.ID_password);
        bLogin = findViewById(R.id.btnLogin);
        SharedPreferences sharedPreferences = getSharedPreferences("user", MODE_PRIVATE);
        String ema = sharedPreferences.getString("EMail","null");
        String pas = sharedPreferences.getString("Password","null");

        if(letEMail.getText().toString().equals(ema)&&letPassword.getText().toString().equals(pas)){
            new Pages().execute(R.layout.activity_main);
        }
        else
            Toast.makeText(this, "邮箱或密码错误", Toast.LENGTH_SHORT).show();
    }


    public void turnToLogin(View view) {
        new Pages().execute(R.layout.layout_login);
    }
    public void turnToRegister(View view) {
        new Pages().execute(R.layout.layout_register);
    }

    private class Pages extends AsyncTask<Integer, Void, Integer> {

        @Override
        protected Integer doInBackground(Integer... ints)
        {
            return ints[0];
        }

        protected void onPostExecute(Integer integer)
        {
            setContentView(integer);
        }
    }
}
