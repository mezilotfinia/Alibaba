package com.example.myapplication.Receiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckInternetReceiver extends BroadcastReceiver {

    private OnCheckConnection onCheckConnection;

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null &&networkInfo.isConnected()){
            /*if(networkInfo.getType()==ConnectivityManager.TYPE_WIFI)*/
            onCheckConnection.OnReceive();
        }else {
            onCheckConnection.OnErrorReceive();
        }

    }

    public void setOnCheckConnection(OnCheckConnection onCheckConnection) {
        this.onCheckConnection = onCheckConnection;
    }

    public interface OnCheckConnection{
        void OnErrorReceive();
        void OnReceive();
    }
}
