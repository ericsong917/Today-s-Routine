package edu.skku.cs.todayroutine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class RoutineSelectAdapter extends BaseAdapter {
    private ArrayList<Athlete_Unit> items;
    private Context mContext;
    RoutineSelectAdapter(ArrayList<Athlete_Unit> items, Context mContext){
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

    public boolean is_checked(int position){
        return items.get(position).checked;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            LayoutInflater layoutInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view=layoutInflater.inflate(R.layout.athelete_pick,viewGroup,false);
        }
        CheckBox checkbox=view.findViewById(R.id.checkBox);
        TextView textview=view.findViewById(R.id.ath);
        checkbox.setChecked(items.get(i).checked);
        textview.setText(items.get(i).unit_name);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(items.get(i).isChecked()){
                    RoutineSelectActivity.count--;
                }
                else{
                    RoutineSelectActivity.count++;
                }
                boolean newState= !items.get(i).isChecked();
                items.get(i).checked=newState;
            }
        });
        checkbox.setChecked(is_checked(i));
        return view;
    }
}
