package edu.skku.cs.todayroutine;

public class GoPresenter implements GoContract.ContractForPresenter, GoContract.ContractForModel.OnValueChangedListener {
    private GoContract.ContractForModel model;
    private GoContract.ContractForView view;
    public GoPresenter(GoContract.ContractForView view,GoContract.ContractForModel model){
        this.view=view;
        this.model=model;
    }
    @Override
    public void onChanged() {
        view.updateRemainSet(model.getRemainSets());
    }

    @Override
    public void onChanged_time() {
        view.updateMinute(model.getMinute());
        view.updateSecond(model.getSecond());
    }


    @Override
    public void onStartButtonTouched() {
        if(model.getRemainSets()>0)
            model.StartTimer(this);
        else
            view.ToastNoRemainSet();

    }

    @Override
    public void onEndButtonTouched() {
        view.goBackTraining();
    }
}
