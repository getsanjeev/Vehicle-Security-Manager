package com.alpha.sanjeev.vehiclesecuritymanager;

/**
 * Created by sanjeev on 10/7/16.
 */
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;


public class splash_screen extends Activity

{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timerThread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    SharedPreferences sp = getSharedPreferences("mango",MODE_PRIVATE);
                    if(sp.contains("check")){
                        if (sp.getBoolean("check",true))
                        {
                            Intent intent = new Intent(splash_screen.this,vehicle_list.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            Intent intent = new Intent(splash_screen.this, sign_up_screen.class);
                            //Log.e("sanjeev","IOU8YNTRES");
                            startActivity(intent);
                            finish();

                        }
                    }
                    else
                    {
                        Intent intent = new Intent(splash_screen.this,sign_up_screen.class);
                        //Log.e("sanjeev","IOU8YNTRES");
                        startActivity(intent);
                        finish();
                    }
                }

            }
        };

        timerThread.start();

    }
}

