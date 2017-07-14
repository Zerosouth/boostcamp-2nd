package appjam.contest.secondminiproject.application;

import android.app.Activity;
import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.kakao.auth.KakaoSDK;

import appjam.contest.secondminiproject.kakao.KakaoSDKAdapter;

/**
 * Created by dh814_000 on 2017-03-07.
 */


public class ApplicationController extends Application {
    private static volatile ApplicationController instance = null;
    private static volatile Activity currentActivity = null;

    public static String user_name;
    public static String user_profile;


    //서버 유알엘
    private static String baseUrl = "http://52.78.182.69:3000";

//    private static NetworkService networkService;
//    public NetworkService getNetworkService() { return networkService; }

    public static ApplicationController getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

//        Toast.makeText(getApplicationContext(),"start",Toast.LENGTH_SHORT).show();

        KakaoSDK.init(new KakaoSDKAdapter());
        // TODO: 2016. 11. 21. 어플리케이션 초기 실행 시, retrofit을 사전에 build한다.
        //buildService();



    }

    public static Activity getCurrentActivity() {
        return currentActivity;
    }

    public static void setCurrentActivity(Activity currentActivity) {
        ApplicationController.currentActivity = currentActivity;
    }

    /**
     * singleton 애플리케이션 객체를 얻는다.
     * @return singleton 애플리케이션 객체
     */
    public static ApplicationController getGlobalApplicationContext() {
        if(instance == null){
            Log.d("application","fail");
            throw new IllegalStateException("this application does not inherit com.kakao.GlobalApplication");

        }
        Log.d("application","success");
        return instance;
    }

    /**
     * 애플리케이션 종료시 singleton 어플리케이션 객체 초기화한다.
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        instance = null;
    }
    // 레트로피부분을 사전에 빌드하도록함
//    public void buildService() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit.Builder builder = new Retrofit.Builder();
//        Retrofit retrofit = builder
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create(gson)) // json 사용할꺼기때문에 json컨버터를 추가적으로 넣어준것임
//                .build();
//        networkService = retrofit.create(NetworkService.class);
//
//    }

}
