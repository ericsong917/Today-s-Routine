package edu.skku.cs.todayroutine;

public interface SignupContract {
    interface ContractForView{
        void StartMainActivity();
        void ToastSignupError();
        String get_id();
        String get_passwd();
        String get_nickname();
    }
    interface ContractForModel{
        boolean getSuccess();
        void Signup(String id, String passwd, String nickname,OnValueChangedListener listener);
        interface OnValueChangedListener{
            void onChanged();
        }
    }
    interface ContractForPresenter{
        void onSignupButtonTouched();
    }
}
