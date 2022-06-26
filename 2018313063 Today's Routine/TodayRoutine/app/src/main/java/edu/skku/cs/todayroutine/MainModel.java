package edu.skku.cs.todayroutine;

import android.util.Log;

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

public class MainModel implements MainContract.ContractForModel{
    private static boolean success;
    private static String NICKNAME;
    private static String ID;
    private static String PASSWD;


    public MainModel(boolean initial_success,String initial_nickname,String initial_id ,String initial_pwd){
        this.success=initial_success;
        this.NICKNAME=initial_nickname;
        this.ID=initial_id;
        this.PASSWD=initial_pwd;
    }

    @Override
    public boolean getSuccess() {
        return this.success;
    }

    @Override
    public String getId() {
        return this.ID;
    }

    @Override
    public String getNickname() {
        return this.NICKNAME;
    }

    @Override
    public String getPasswd() {
        return this.PASSWD;
    }


    @Override
    public void setNickName2(OnValueChangedListener listener) {
        OkHttpClient client=new OkHttpClient();
        HttpUrl.Builder urlBuilder=HttpUrl.parse("https://txfdvbzif3.execute-api.ap-northeast-2.amazonaws.com/dev/getdata").newBuilder();
        urlBuilder.addQueryParameter("id",this.getId());
        String url=urlBuilder.build().toString();
        Request req=new Request.Builder().url(url).build();
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
                MainModel.NICKNAME=data1.getNickname();
                MainModel.success=true;
                listener.onChanged2();
            }
        });
    }

    @Override
    public void LoginCheck(String id, String pw,OnValueChangedListener listener) {
        OkHttpClient client=new OkHttpClient();
        LoginDataModel data=new LoginDataModel();
        data.setId(id);
        data.setPasswd(pw);
        this.ID=id;
        this.PASSWD=pw;
        Gson gson=new Gson();
        String json=gson.toJson(data,LoginDataModel.class);
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://txfdvbzif3.execute-api.ap-northeast-2.amazonaws.com/dev/login").newBuilder();
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
                    MainModel.success=true;
                    listener.onChanged();
                }
                else{
                    MainModel.success=false;
                    listener.onChanged();
                }
            }
        });
    }


}
