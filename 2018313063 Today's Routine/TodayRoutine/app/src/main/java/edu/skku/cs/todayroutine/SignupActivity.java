package edu.skku.cs.todayroutine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity  implements SignupContract.ContractForView{
    private EditText id_editText;
    private EditText pw_editText;
    private EditText nickname_editText;
    private Button Signup_btn;
    private SignupPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ActionBar ab= getSupportActionBar();
        ab.setTitle("Sign up");
        id_editText=findViewById(R.id.signup_id_et);
        pw_editText=findViewById(R.id.signup_passwd_et);
        nickname_editText=findViewById(R.id.signup_nickname_et);
        Signup_btn=findViewById(R.id.button);
        presenter=new SignupPresenter(this,new SignupModel(false));
        Signup_btn.setOnClickListener(view ->{
            if(id_editText.getText().length()==0 || pw_editText.getText().length()==0 || nickname_editText.getText().length()==0)
                imperfect_input();
            else
                presenter.onSignupButtonTouched();
        });
    }


    @Override
    public void StartMainActivity() {
        finish();
    }

    @Override
    public void ToastSignupError() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Your id already exists!",Toast.LENGTH_SHORT).show();
            }
        },0 );
    }

    @Override
    public String get_id() {
        return id_editText.getText().toString();
    }

    @Override
    public String get_passwd() {
        return pw_editText.getText().toString();
    }

    @Override
    public String get_nickname() {
        return nickname_editText.getText().toString();
    }
    public void imperfect_input(){
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Please enter unfilled part",Toast.LENGTH_SHORT).show();
            }
        },0 );
    }
}