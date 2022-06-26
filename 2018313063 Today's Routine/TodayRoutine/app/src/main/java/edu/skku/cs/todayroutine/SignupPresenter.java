package edu.skku.cs.todayroutine;

public class SignupPresenter implements SignupContract.ContractForPresenter,SignupContract.ContractForModel.OnValueChangedListener {
    private SignupContract.ContractForView view;
    private SignupContract.ContractForModel model;

    public SignupPresenter(SignupContract.ContractForView view,SignupContract.ContractForModel model){
        this.view=view;
        this.model=model;
    }
    @Override
    public void onChanged() {
        if(model.getSuccess()){
            view.StartMainActivity();
        }
        else{
            view.ToastSignupError();
        }
    }

    @Override
    public void onSignupButtonTouched() {
        model.Signup(view.get_id(),view.get_passwd(),view.get_nickname(),this);
    }
}
