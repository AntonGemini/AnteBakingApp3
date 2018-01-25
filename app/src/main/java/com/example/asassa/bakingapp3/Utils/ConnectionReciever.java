package com.example.asassa.bakingapp3.Utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionReciever extends BroadcastReceiver {


    public ConnectionReciever()
    {
    }

    public ConnectionReciever(OnConnectionChangedReciever reciever)
    {
        super();
        onConnectionChangedReciever = reciever;
    }

    public interface OnConnectionChangedReciever
    {
        void onConnectionChanged(boolean isConnected);
    }

    private OnConnectionChangedReciever onConnectionChangedReciever;

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        if (!isInitialStickyBroadcast()) {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
            if (onConnectionChangedReciever != null) {
                onConnectionChangedReciever.onConnectionChanged(isConnected);
            }
        }
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public static boolean checkConnection(Context context)
    {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnectedOrConnecting();
        return isConnected;
    }

}
