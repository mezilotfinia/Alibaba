package com.example.myapplication.Fragment;

import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.myapplication.MainActivity;
import com.example.myapplication.Receiver.CheckInternetReceiver;
import com.example.myapplication.R;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SplashFragment extends Fragment {

    Timer timer;
    ImageView imgLeft,imgRight;
    LinearLayout linearErrorConnection;
    CheckInternetReceiver checkInternetReceiver;
    View view;
    private Handler handler;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.splash_screen,container,false);
        setupViews();
        leftAnimation();
        rightAnimation();
        checkInternetReceiver.setOnCheckConnection(new CheckInternetReceiver.OnCheckConnection() {
            @Override
            public void OnErrorReceive() {
                linearErrorConnection.setVisibility(View.VISIBLE);
            }
            @Override
            public void OnReceive() {
                timer=new Timer();
                timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        //timer worker thread ijad mikone va bayad modiriat bshe
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                MainActivity.fragmentManager.beginTransaction().remove(SplashFragment.this).commit();
                                timer.purge();
                                timer.cancel();
                            }
                        });
                    }
                },4500,1);
            }
        });
        return view;
    }

    private void setupViews(){
        linearErrorConnection=view.findViewById(R.id.linear_splash_errorConnection);
        imgLeft=view.findViewById(R.id.img_splash_left);
        imgRight=view.findViewById(R.id.img_splash_right);
        handler=new Handler();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkInternetReceiver=new CheckInternetReceiver();
        getContext().registerReceiver(checkInternetReceiver,new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        timer.purge();
        timer.cancel();
        getContext().unregisterReceiver(checkInternetReceiver);
    }

    private void leftAnimation(){
        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(3000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        imgLeft.setAnimation(rotateAnimation);
    }
    private void rightAnimation(){
        RotateAnimation rotateAnimation=new RotateAnimation(0,360, Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rotateAnimation.setDuration(2000);
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        rotateAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
        imgRight.setAnimation(rotateAnimation);
    }

    @Override
    public void onDestroyView() {
        timer.purge();
        timer.cancel();
        super.onDestroyView();
    }
}
