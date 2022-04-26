package com.example.broadcastreceiverextralesson.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            Toast.makeText(context, "BOOT COMPLETED FINISHED", Toast.LENGTH_SHORT).show()
        }
    }
}

