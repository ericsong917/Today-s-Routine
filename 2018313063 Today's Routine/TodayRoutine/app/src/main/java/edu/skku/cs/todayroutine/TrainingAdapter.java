package edu.skku.cs.todayroutine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TrainingAdapter extends BaseAdapter {
    public static ArrayList<Training_Unit> items;
    private Context mContext;
    TrainingAdapter(ArrayList<Training_Unit> items, Context mContext){
        this.mContext=mContext;
        this.items=items;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        RecyclerView.ViewHolder holder = null;
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.training,viewGroup,false);
        }

        TextView textview1=view.findViewById(R.id.unit_name);
        EditText Sets=view.findViewById(R.id.num_set);
        EditText minute=view.findViewById(R.id.minute);
        EditText second=view.findViewById(R.id.second);
        Button btn=view.findViewById(R.id.Go);
        ImageView img=view.findViewById(R.id.finished);
        textview1.setText(items.get(i).unit_name);

        if(items.get(i).finished){
            img.setImageResource(R.drawable.o);
        }
        else{
            img.setImageResource(R.drawable.x);
        }
        Sets.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i3, int i1, int i2) {
                    items.get(i).setSets(charSequence.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minute.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i3, int i1, int i2) {
                minute.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View view, boolean b) {
                            items.get(i).setMinute(charSequence.toString());
                    }
                });

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        second.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i3, int i1, int i2) {
                    items.get(i).setSecond(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyDataSetChanged();
                if(items.get(i).unit_name.length()==0 || items.get(i).minute.length()==0 || items.get(i).second.length()==0 ){
                    Handler handler= new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(mContext,"Fill the parts",Toast.LENGTH_SHORT).show();
                        }
                    },0 );
                    return;
                }
                Intent intent = new Intent(view.getContext(),GoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("unitname",items.get(i).unit_name);
                intent.putExtra("minute",items.get(i).minute);
                intent.putExtra("second",items.get(i).second);
                intent.putExtra("sets",items.get(i).sets);
                intent.putExtra("index",i);
                view.getContext().startActivity(intent);
                items.get(i).setFinished(true);
            }
        });

        return view;
    }
}
