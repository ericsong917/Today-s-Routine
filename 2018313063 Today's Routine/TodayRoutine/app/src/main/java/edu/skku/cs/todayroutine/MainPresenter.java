package edu.skku.cs.todayroutine;

import android.util.Log;

public class MainPresenter implements MainContract.ContractForPresenter,MainContract.ContractForModel.OnValueChangedListener {
    private MainContract.ContractForView view;
    private MainContract.ContractForModel model;
    public MainPresenter(MainContract.ContractForView view,MainContract.ContractForModel model){
        this.view=view;
        this.model=model;
    }
    @Override
    public void onLoginTouched() {
        model.LoginCheck(view.get_string_id(), view.get_string_pw(),this);

    }
    @Override
    public void onSignupTouched() {
        view.StartSignUpActivity();
    }

    @Override
    public void onChanged() {
        if(model.getSuccess()){
            model.setNickName2(this);

        }
        else{
            view.ToastLoginError();
        }
    }

    @Override
    public void onChanged2() {
        Log.d("TEST",model.getNickname());
        view.StartRoutineSelectActivity(model.getNickname());
    }
}
