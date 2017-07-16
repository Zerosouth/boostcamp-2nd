package appjam.contest.secondminiproject.kakao;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.kakao.auth.Session;

import java.security.MessageDigest;
import appjam.contest.secondminiproject.R;

public class SplashActivity extends Activity{


    Handler handler;
    //GPS체크

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

       /**
         *  카카오세션 열려있으면(로그인) -> KaKaoSignupActivity -> MainActivity
         *  닫혀있으면 LoginActivity
         */


        //
        Session.getCurrentSession().close();



        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {


                if(Session.getCurrentSession().isOpened()) {
                    intent = new Intent(getApplicationContext(), KakaoSignupActivity.class);
                    //Toast.makeText(getApplicationContext(),"kakao",Toast.LENGTH_SHORT).show();
                }else {
                    intent = new Intent(getApplicationContext(), LoginActivity.class);
                    //Toast.makeText(getApplicationContext(),"login",Toast.LENGTH_SHORT).show();
                }
                startActivity(intent);
                finish();
            }
        };

        handler.sendEmptyMessageDelayed(0, 2000);

        //getAppKeyHash();

    }


    private void getAppKeyHash() {
       // Toast.makeText(getApplicationContext(),"splash",Toast.LENGTH_SHORT).show();
        try {
            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                Log.d("Hash key", something);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.e("name not found", e.toString());
        }
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        handler.sendEmptyMessageDelayed(0, 1000);
    }






}
