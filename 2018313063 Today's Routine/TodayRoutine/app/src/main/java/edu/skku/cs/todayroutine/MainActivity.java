package edu.skku.cs.todayroutine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.ContractForView {
    public static String global_nickname;
    private MainPresenter presenter;
    private EditText Id;
    private EditText Pw;
    private AppCompatButton Login;
    private AppCompatButton Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar ab= getSupportActionBar();
        ab.setTitle("Login / SignUp");
        setContentView(R.layout.activity_main);
        Id=findViewById(R.id.id_et);
        Pw=findViewById(R.id.pw_et);
        Login=findViewById(R.id.login_btn);
        Signup=findViewById(R.id.signup_btn);
        presenter=new MainPresenter(this,new MainModel(false,"","",""));
        Login.setOnClickListener(view -> {
            if(Id.getText().length()==0 || Pw.getText().length()==0){
                Toast.makeText(getApplicationContext(),"Please fill empty part(s).",Toast.LENGTH_SHORT).show();

            }
            else{
                presenter.onLoginTouched();
                Id.setText("");
                Pw.setText("");
            }

        });
        Signup.setOnClickListener(view -> {
            presenter.onSignupTouched();
        });
    }

    @Override
    public void StartRoutineSelectActivity(String nickname) {
        Intent intent= new Intent(getApplicationContext(),RoutineSelectActivity.class);
        intent.putExtra("nickname",nickname);
        global_nickname=nickname;
        startActivity(intent);
    }


    @Override
    public void StartSignUpActivity() {
        Intent intent= new Intent(getApplicationContext(),SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void ToastLoginError() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Non valid id, or password is wrong",Toast.LENGTH_SHORT).show();
            }
        },0 );
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Id.setText("");
                Pw.setText("");
            }
        });

    }

    @Override
    public String get_string_id() {
        return Id.getText().toString();
    }

    @Override
    public String get_string_pw() {
        return Pw.getText().toString();
    }

}