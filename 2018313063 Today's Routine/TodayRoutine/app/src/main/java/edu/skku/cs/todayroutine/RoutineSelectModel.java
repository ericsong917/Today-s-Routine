package edu.skku.cs.todayroutine;

import java.util.ArrayList;
import java.util.Iterator;

public class RoutineSelectModel implements RoutineSelectContract.ContractForModel {
    private int status;
    private ArrayList<Athlete_Unit> list;
    public RoutineSelectModel(int status, ArrayList<Athlete_Unit>list){
        this.status=status;
        this.list=list;
    }
    @Override
    public void add_unit(String unit, OnValueChangedListener listener) {
        Athlete_Unit temp=new Athlete_Unit(false,unit);
        this.list.add(temp);
        listener.onChanged();
    }

    @Override
    public boolean check_nothing(ArrayList<Athlete_Unit> arr_list) {
        for(int i=0;i<arr_list.size();i++){
            if(arr_list.get(i).checked){
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<String> getChecked(ArrayList<Athlete_Unit> arr_list, OnValueChangedListener listener) {
        ArrayList<String> temp = new ArrayList<String>();
        for(int i=0;i<arr_list.size();i++){
            if(arr_list.get(i).isChecked()){
                temp.add(arr_list.get(i).getUnit_name());
            }
        }
        return temp;
    }


    @Override
    public int calculate_status(String unit) {
        int status;
        if(unit.length()==0)
            status=0; //아무것도 없음
        else{
            status=1; // 리스트 안에 없음
        }
        for(int i=0;i<this.list.size();i++){
            if(this.list.get(i).getUnit_name().equals(unit)){
                status=2;
            }
        }
        return status;
    }
}
