package edu.skku.cs.todayroutine;

public interface GoContract {
    interface ContractForView{
        void updateMinute(int minute);
        void updateSecond(int second);
        void updateRemainSet(int remainset);
        void ToastNoRemainSet();
        void goBackTraining();
    }
    interface ContractForModel{
        int getRemainSets();
        int getMinute();
        int getSecond();
        void setRemainSets(OnValueChangedListener listener);
        void StartTimer(OnValueChangedListener listener);
        interface OnValueChangedListener{
            void onChanged();
            void onChanged_time();
        }
    }
    interface ContractForPresenter{
        void onStartButtonTouched();
        void onEndButtonTouched();
    }
}
