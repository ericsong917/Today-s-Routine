package edu.skku.cs.todayroutine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RoutineSelectActivity extends AppCompatActivity implements RoutineSelectContract.ContractForView {
    public static int count=0;
    private TextView hello;
    private ListView listview;
    private EditText edittext;
    private Button add_btn;
    private Button start_train_btn;
    private ArrayList<Athlete_Unit> items;
    private RoutineSelectAdapter routineSelectAdapter;
    private RoutineSelectPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_select);
        ActionBar ab= getSupportActionBar();
        ab.setTitle("Select routine");
        hello=findViewById(R.id.hello_tv);
        listview=findViewById(R.id.listView);
        hello.setText(MainActivity.global_nickname);
        add_btn=findViewById(R.id.add_unit_btn);
        edittext=findViewById(R.id.add_unit);
        start_train_btn=findViewById(R.id.start_train_btn);
        items=new ArrayList<Athlete_Unit>();
        addItems();
        presenter=new RoutineSelectPresenter(this,new RoutineSelectModel(0,items) );
        routineSelectAdapter=new RoutineSelectAdapter(items,getApplicationContext());
        listview.setAdapter(routineSelectAdapter);
        add_btn.setOnClickListener(view->{
                presenter.onAddBtnTouched();
        });
        start_train_btn.setOnClickListener(view->{
                if(count>11){
                    Toast.makeText(getApplicationContext(),"too many units selected",Toast.LENGTH_SHORT).show();
                }
                else{
                    count=0;
                    presenter.onStartTrainingTouched();

                }
        });
    }

    @Override
    public void clear_listview() {
        for(int i=0;i<items.size();i++){
            if(items.get(i).isChecked()){
                items.get(i).setChecked(false);
            }
        }
    }

    @Override
    public void update_listview() {
        routineSelectAdapter.notifyDataSetChanged();
    }

    @Override
    public String get_unit() {
        return edittext.getText().toString();
    }

    @Override
    public ArrayList<Athlete_Unit> get_ListView() {
        return items;
    }

    @Override
    public void ToastAlreadyExist() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"Already existing unit(??????)",Toast.LENGTH_SHORT).show();
            }
        },0 );
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                edittext.setText("");
            }
        });
    }

    @Override
    public void ToastNothing() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"You should type unit to add",Toast.LENGTH_SHORT).show();
            }
        },0 );
    }

    @Override
    public void Toast_NothingSelected() {
        Handler handler= new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"You should Select Some unit",Toast.LENGTH_SHORT).show();
            }
        },0 );
    }

    @Override
    public void StartTrainingActivity(ArrayList<String> arrayList) {
        Intent intent=new Intent(getApplicationContext(),TrainingActivity.class);
        intent.putExtra("checked_units",arrayList);
        startActivity(intent);
    }

    @Override
    public void clearEditText() {
        edittext.setText("");
    }


    void addItems(){
        items.add(new Athlete_Unit(false, "?????? ???????????????"));
        items.add(new Athlete_Unit(false, "???????????? ???????????????"));
        items.add(new Athlete_Unit(false, "??????"));
        items.add(new Athlete_Unit(false, "?????? ??????????????????"));
        items.add(new Athlete_Unit(false, "????????? ?????????"));
        items.add(new Athlete_Unit(false, "?????????????????? ??????"));
        items.add(new Athlete_Unit(false, "???????????? ???????????????"));
        items.add(new Athlete_Unit(false, "??????"));
        items.add(new Athlete_Unit(false, "??????"));
        items.add(new Athlete_Unit(false, "????????????"));
        items.add(new Athlete_Unit(false, "???????????? ???????????????"));
        items.add(new Athlete_Unit(false, "???????????? ???????????????"));
        items.add(new Athlete_Unit(false, "?????? ??????"));
        items.add(new Athlete_Unit(false, "?????? ??????"));
        items.add(new Athlete_Unit(false, "?????? ?????? ??????"));
        items.add(new Athlete_Unit(false, "????????? ?????? ??????"));
        items.add(new Athlete_Unit(false, "?????? ???????????????"));
        items.add(new Athlete_Unit(false, "???????????? ?????????"));
        items.add(new Athlete_Unit(false, "???????????? ????????????"));
        items.add(new Athlete_Unit(false, "??????????????? ??????"));
        items.add(new Athlete_Unit(false, "????????? ??????????????????"));
        items.add(new Athlete_Unit(false, "???????????? ??????????????????"));
        items.add(new Athlete_Unit(false, "????????? ?????????"));
        items.add(new Athlete_Unit(false, "?????????"));
        items.add(new Athlete_Unit(false, "?????? ??????"));
        items.add(new Athlete_Unit(false, "????????? ??????"));
        items.add(new Athlete_Unit(false, "?????? ?????????"));
        items.add(new Athlete_Unit(false, "?????? ????????????"));
        items.add(new Athlete_Unit(false, "?????? ???"));
        items.add(new Athlete_Unit(false, "??????"));
        items.add(new Athlete_Unit(false, "?????????"));
        items.add(new Athlete_Unit(false, "??????"));
        items.add(new Athlete_Unit(false, "?????????"));
        items.add(new Athlete_Unit(false, "?????????????????????"));
        items.add(new Athlete_Unit(false, "?????????"));
    }

}