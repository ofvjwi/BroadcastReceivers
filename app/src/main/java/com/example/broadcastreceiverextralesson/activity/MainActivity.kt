package com.example.broadcastreceiverextralesson.activity

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.broadcastreceiverextralesson.R
import com.example.broadcastreceiverextralesson.receivers.NoticeUsbConnectionReceiver


class MainActivity : AppCompatActivity() {
    private lateinit var noticeUsbConnectionReceiver: NoticeUsbConnectionReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  requestSmsPermission()
        initViews()

        //   LocalBroadcastManager.getInstance(this)
    }

    override fun onStart() {
        super.onStart()
        val filter = IntentFilter(Intent.ACTION_BATTERY_CHANGED)
        registerReceiver(noticeUsbConnectionReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        unregisterReceiver(noticeUsbConnectionReceiver)
    }

    private fun initViews() {
        noticeUsbConnectionReceiver = NoticeUsbConnectionReceiver()
    }

    private fun requestSmsPermission() {
        // See if the user has not granted permission to read his or her text messages
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
            !=
            PackageManager.PERMISSION_GRANTED
        ) {
            // Request the user to grant permission to read SMS messages
            ActivityCompat.requestPermissions(
                this, arrayOf(
                    Manifest.permission.RECEIVE_SMS,
                    Manifest.permission.READ_PHONE_STATE,
                    //  Manifest.permission.CALL_PHONE,
                    //  Manifest.permission.ANSWER_PHONE_CALLS,
                    //  Manifest.permission.MODIFY_PHONE_STATE,
                    Manifest.permission.READ_PHONE_NUMBERS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.READ_CALL_LOG,
//                    Manifest.permission.RECEIVE_BOOT_COMPLETED,
//                    Manifest.permission.WAKE_LOCK
                ),
                2
            )
        }
    }
}

