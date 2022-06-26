package edu.skku.cs.todayroutine;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TrainingActivity extends AppCompatActivity {
    private TrainingAdapter trainingAdapter;
    private ArrayList<String> Checked_List;
    private  ArrayList<Training_Unit> training_list;
    private ListView listview;
    public static ArrayList<Boolean> fin;
    private Button btn;
    private Button btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        ActionBar ab= getSupportActionBar();
        ab.setTitle("Training");
        Checked_List = (ArrayList<String>)getIntent().getSerializableExtra("checked_units");
        training_list=new ArrayList<Training_Unit>();
        listview=findViewById(R.id.listView_training);
        btn2=findViewById(R.id.button2);
        btn=findViewById(R.id.button_end_training);
        set_trainingList();
        trainingAdapter=new TrainingAdapter(training_list,getApplicationContext());
        listview.setAdapter(trainingAdapter);
        btn.setOnClickListener(view->{
            copytoclipboard();
        });
        btn2.setOnClickListener(view -> {
            this.finishAffinity();
        });
    }

    public void set_trainingList(){
        for(int i=0;i<Checked_List.size();i++){
            Training_Unit train_unit = new Training_Unit(Checked_List.get(i),"","","",false);
            training_list.add(train_unit);
        }
    }
    public void copytoclipboard(){
        String temp="";
        for(int i=0;i<training_list.size();i++){
            temp+=(training_list.get(i).getUnit_name()+" "+training_list.get(i).getSets()+"Sets "+"\n");
        }
        ClipboardManager clipboard=(ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip= ClipData.newPlainText("label",temp);
        clipboard.setPrimaryClip(clip);
        Toast.makeText(getApplicationContext(),"Your Routine is copied into clipboard",Toast.LENGTH_SHORT).show();
    }


}