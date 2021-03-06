package com.yfw.zlt.zltwx;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeScreenHelper;
import com.stephentuso.welcome.ui.WelcomeActivity;
import com.yfw.zlt.zltwx.ui.activity.mine.LoginActivity;

import java.io.File;

/**
 * 开屏页
 */

public class WelcomActivity extends Activity {
    private static final int sleepTime = 2000;
    boolean isLogin=false;
    WelcomeScreenHelper welcomeScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_welcom);
        welcomeScreen = new WelcomeScreenHelper(this, MyWelcomActivity.class);
        welcomeScreen.show(savedInstanceState);
        welcomeScreen.forceShow();

        //        new Timer().schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent intent=new Intent(MainActivity.this,MainFragmentManager.class);
//                startActivity(intent);
//            }
//        },1000);

//        new Handler().postDelayed(new Thread(){
//            public void run(){
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                Intent intent=new Intent(WelcomActivity.this,MainFragmentManager.class);
//                startActivity(intent);
//                WelcomActivity.this.finish();
//            }
//        },1000);
        initFile() ;

//        AlphaAnimation animation = new AlphaAnimation(0.3f, 1.0f);
//        animation.setDuration(1500);
//        view.startAnimation(animation);
    }
    @Override
    protected void onStart() {
        super.onStart();
//        new Handler().postDelayed(new Thread(){
//            public void run(){
//                if(isLogin){
//                    long start = System.currentTimeMillis();
//                    long costTime = System.currentTimeMillis() - start;
//                    //等待sleeptime时长
//                    if(sleepTime-costTime>0){
//                        try {
//                            Thread.sleep(sleepTime-costTime);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
//                        Intent intent=new Intent(WelcomActivity.this,MainFragmentManager.class);
//                        startActivity(intent);
//                        WelcomActivity.this.finish();
//
//                    }
//                }else {
//                    try {
//                        Thread.sleep(sleepTime);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Intent intent=new Intent(WelcomActivity.this,LoginActivity.class);
//                    startActivity(intent);
//                    WelcomActivity.this.finish();
//                }
//            }
//        },1000);
    }
    /**
     * 获取当前应用程序的版本号
     */
    private String getVersion(){
        PackageManager pm = getPackageManager();
        try {
            PackageInfo packinfo = pm.getPackageInfo(getPackageName(), 0);
            String version = packinfo.versionName;
            return version;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "版本号错误";
        }
    }

    @SuppressLint("SdCardPath")
    private void initFile() {
        File dir = new File("/sdcard/fanxin");
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WelcomeScreenHelper.DEFAULT_WELCOME_SCREEN_REQUEST) {
            String welcomeKey = data.getStringExtra(WelcomeActivity.WELCOME_SCREEN_KEY);

            if (resultCode == RESULT_OK) {
                Intent intent=new Intent(WelcomActivity.this,LoginActivity.class);
                startActivity(intent);
                WelcomActivity.this.finish();
                //Toast.makeText(getApplicationContext(), welcomeKey + " completed", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), welcomeKey + " canceled", Toast.LENGTH_SHORT).show();
            }

        }

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }
}

