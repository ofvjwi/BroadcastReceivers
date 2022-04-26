package com.example.broadcastreceiverextralesson.receivers

import android.app.AlertDialog
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.widget.Toast


class NoticeUsbConnectionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val plugged = intent!!.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1)
        if (plugged == BatteryManager.BATTERY_PLUGGED_USB) {
            Toast.makeText(context, "Connected to USB", Toast.LENGTH_LONG).show()
            showDialog(context)
        } else if (plugged == BatteryManager.BATTERY_PLUGGED_AC) {
            Toast.makeText(context, "Connected to Power", Toast.LENGTH_LONG).show()
        }
    }

    private fun showDialog(context: Context?) {
        val dialog = AlertDialog.Builder(context)
        dialog.setTitle("Warning!")
        dialog.setMessage("You are using USB to debugging!\nPlease unplug your phone from the computer!!!")
        dialog.setCancelable(false)
        dialog.setPositiveButton(
            "Ok"
        ) { _, _ -> throw Exception() }
        dialog.show()
    }
}



