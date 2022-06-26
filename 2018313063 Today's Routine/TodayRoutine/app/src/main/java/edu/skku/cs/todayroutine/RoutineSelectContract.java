package edu.skku.cs.todayroutine;

import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public interface RoutineSelectContract {
    interface ContractForView{
        void clear_listview();
        void update_listview();
        String get_unit();
        ArrayList<Athlete_Unit> get_ListView();
        void ToastAlreadyExist();
        void ToastNothing();
        void Toast_NothingSelected();
        void StartTrainingActivity(ArrayList<String> arrayList);
        void clearEditText();
    }
    interface ContractForModel{
        void add_unit(String unit,OnValueChangedListener listener);
        interface OnValueChangedListener{
            void onChanged();
        }
        boolean check_nothing(ArrayList<Athlete_Unit> arr_list);
        ArrayList<String> getChecked(ArrayList<Athlete_Unit> arr_list,OnValueChangedListener listener);
        int calculate_status(String unit);

    }
    interface ContractForPresenter{
        void onAddBtnTouched();
        void onStartTrainingTouched();
    }
}
