package edu.skku.cs.todayroutine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GoActivity extends AppCompatActivity implements GoContract.ContractForView {
    private TextView unit_name;
    private TextView whole_set;
    private TextView remain_set;
    private TextView Minute;
    private TextView Second;
    private Button RestButton;
    private Button EndButton;
    private GoPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_go);
        ActionBar ab= getSupportActionBar();
        ab.setTitle("Timer");
        unit_name=findViewById(R.id.go_unitname);
        whole_set=findViewById(R.id.go_WholeSet);
        remain_set=findViewById(R.id.go_remainset);
        Minute =findViewById(R.id.go_M);
        Second=findViewById(R.id.go_S);
        RestButton=findViewById(R.id.go_rest);
        EndButton=findViewById(R.id.go_Fin);
        Intent intent = getIntent();
        Bundle bundle=intent.getExtras();
        whole_set.setText(intent.getStringExtra("sets"));
        remain_set.setText(intent.getStringExtra("sets"));
        unit_name.setText(intent.getStringExtra("unitname"));
        Minute.setText(intent.getStringExtra("minute"));
        Second.setText(intent.getStringExtra("second"));
        int MIN= Integer.parseInt(Minute.getText().toString());
        int SEC= Integer.parseInt(Second.getText().toString());
        int REM= Integer.parseInt(remain_set.getText().toString());
        presenter=new GoPresenter(this,new GoModel(MIN,SEC,REM) );
        RestButton.setOnClickListener(view ->{
            presenter.onStartButtonTouched();
        });
        EndButton.setOnClickListener(view->{
            presenter.onEndButtonTouched();
        });


    }

    @Override
    public void updateMinute(int minute) {
        Minute.setText(Integer.toString(minute));
    }

    @Override
    public void updateSecond(int second) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Second.setText(Integer.toString(second));
            }
        });

    }

    @Override
    public void updateRemainSet(int remainset) {
        remain_set.setText(Integer.toString(remainset));
    }

    @Override
    public void ToastNoRemainSet() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"No more Sets left",Toast.LENGTH_SHORT).show();
            }
        },0 );
        }


    @Override
    public void goBackTraining() {
        finish();
    }
}