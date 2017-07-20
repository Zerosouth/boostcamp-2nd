package appjam.contest.thirdminiproject.main.application;

import android.app.Activity;
import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import appjam.contest.thirdminiproject.main.network.NetworkService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dh814_000 on 2017-03-07.
 */


public class ApplicationController extends Application {
    private static volatile ApplicationController instance = null;

    public static ArrayList<Activity> activityList=new ArrayList<Activity>();

    //서버 유알엘
    private static String baseUrl = "http://52.79.188.192:3000";

    private static NetworkService networkService;

    public NetworkService getNetworkService() { return networkService; }
    public static ApplicationController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;


        //어플리케이션 초기 실행 시, retrofit을 사전에 build한다.
        buildService();


    }


    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
    // 레트로피부분을 사전에 빌드하도록함
    public void buildService() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson)) // json 사용할꺼기때문에 json컨버터를 추가적으로 넣어준것임
                .build();
        networkService = retrofit.create(NetworkService.class);

    }

}
