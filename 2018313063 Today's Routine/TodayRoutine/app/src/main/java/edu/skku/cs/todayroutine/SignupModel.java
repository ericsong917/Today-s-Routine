package edu.skku.cs.todayroutine;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignupModel implements SignupContract.ContractForModel {
    private static boolean success;
    public SignupModel(boolean initial_success){
        this.success=initial_success;
    }
    @Override
    public boolean getSuccess() {
        return this.success;
    }

    @Override
    public void Signup(String id, String passwd, String nickname, OnValueChangedListener listener) {
        OkHttpClient client=new OkHttpClient();
        LoginDataModel data=new LoginDataModel();
        data.setId(id);
        data.setPasswd(passwd);
        data.setNickname(nickname);
        Gson gson=new Gson();
        String json=gson.toJson(data,LoginDataModel.class);
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://txfdvbzif3.execute-api.ap-northeast-2.amazonaws.com/dev/signup").newBuilder();
        String url = urlBuilder.build().toString();
        Request req=new Request.Builder().url(url).post(RequestBody.create(MediaType.parse("application/json"),json)).build();
        client.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                final String myResponse=response.body().string();
                Gson gson=new GsonBuilder().create();
                final LoginDataModel data1=gson.fromJson(myResponse,LoginDataModel.class);
                boolean suc=data1.isSuccess();
                if(suc){
                    SignupModel.success=true;
                    listener.onChanged();
                }
                else{
                    SignupModel.success=false;
                    listener.onChanged();
                }
            }
        });
    }
}
