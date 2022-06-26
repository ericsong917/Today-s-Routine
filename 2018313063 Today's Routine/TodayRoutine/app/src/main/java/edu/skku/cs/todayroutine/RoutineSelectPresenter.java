package edu.skku.cs.todayroutine;

import java.util.ArrayList;

public class RoutineSelectPresenter implements RoutineSelectContract.ContractForPresenter,RoutineSelectContract.ContractForModel.OnValueChangedListener{
    private RoutineSelectContract.ContractForView view;
    private RoutineSelectContract.ContractForModel model;
    public RoutineSelectPresenter(RoutineSelectContract.ContractForView view,RoutineSelectContract.ContractForModel model){
        this.view=view;
        this.model=model;
    }
    @Override
    public void onChanged() {
        view.update_listview();
    }


    @Override
    public void onAddBtnTouched() {
        if(model.calculate_status(view.get_unit())==0){
            view.ToastNothing();
        }
        else if(model.calculate_status(view.get_unit())==2){
            view.ToastAlreadyExist();
        }
        else{
            model.add_unit(view.get_unit(),this);
            view.clearEditText();
        }
    }

    @Override
    public void onStartTrainingTouched() {
        if(model.check_nothing(view.get_ListView())){
            view.StartTrainingActivity((model.getChecked(view.get_ListView(),this)));
            view.clear_listview();
            view.update_listview();

        }
        else{
            view.Toast_NothingSelected();
        }
    }

}
