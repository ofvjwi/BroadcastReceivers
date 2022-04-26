package com.example.broadcastreceiverextralesson.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast


// Check Network State
class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (isConnectingToInternet(context!!)) {
            Toast.makeText(context, "Successfully internet connection!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Failed internet connection!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isConnectingToInternet(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}

