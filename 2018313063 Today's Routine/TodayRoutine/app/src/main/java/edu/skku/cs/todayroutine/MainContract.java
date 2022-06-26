package edu.skku.cs.todayroutine;

public interface MainContract {
    interface ContractForView{
        void StartRoutineSelectActivity(String nickname);
        void StartSignUpActivity();
        void ToastLoginError();
        String get_string_id();
        String get_string_pw();
}
    interface ContractForModel{
        boolean getSuccess();
        String getId();
        String getNickname();
        String getPasswd();
        void LoginCheck(String id, String pw,OnValueChangedListener listener);
        interface OnValueChangedListener{
            void onChanged();
            void onChanged2();
        }
        void setNickName2(OnValueChangedListener listener);

    }
    interface ContractForPresenter{
        void onLoginTouched();
        void onSignupTouched();
    }
}
