package com.example.broadcastreceiverextralesson.receivers

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log
import android.widget.Toast

class PhoneStateReceiver : BroadcastReceiver() {
    companion object {
        private const val TAG: String = "PhoneStateReceiver"
    }

    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context?, intent: Intent) {
        try {
            Log.d(TAG, "onReceive: Receiver start")
            val state = intent.getStringExtra(TelephonyManager.EXTRA_STATE)

            val incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER) // old version
            val incomingNumber2 = intent.extras?.getString("incoming_number") // new version

            if (state == TelephonyManager.EXTRA_STATE_RINGING) {
                Toast.makeText(context, "Incoming Call State", Toast.LENGTH_SHORT).show()
                Toast.makeText(
                    context,
                    "Ringing State Number is $incomingNumber",
                    Toast.LENGTH_SHORT
                ).show()
                //   {
                // these are bugs with incoming number
                // 2022-03-31 19:33:46.824 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:33:46.856 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:33:56.263 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:33:56.293 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:33:58.188 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:33:58.220 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:34:09.388 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:34:09.414 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:34:24.211 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                // 2022-03-31 19:34:24.236 18864-18864/com.example.broadcastreceiverextralesson I/System.out: Receiver start
                //     }
            }
            if (state == TelephonyManager.EXTRA_STATE_OFFHOOK) {
                Toast.makeText(context, "Call Received State", Toast.LENGTH_SHORT).show()
            }
            if (state == TelephonyManager.EXTRA_STATE_IDLE) {
                Toast.makeText(context, "Call Idle State", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}

