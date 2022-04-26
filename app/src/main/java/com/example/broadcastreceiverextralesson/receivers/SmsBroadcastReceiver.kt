package com.example.broadcastreceiverextralesson.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.telephony.SmsMessage
import android.util.Log
import android.widget.Toast


// New version of Sms Receiver
class SmsBroadcastReceiver : BroadcastReceiver() {

    companion object {
        private const val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"
        private const val PDU: String = "pdus"  // PROTOCOL DATA UNIT --> pdu
        private const val TAG: String = "SmsReceiverNew"
    }

    override fun onReceive(context: Context?, intent: Intent?) {

        if (intent != null) {

            if (intent.action === SMS_RECEIVED) {
                val bundle: Bundle? = intent.extras

                if (bundle != null) {
                    val protocolDataUnits: Array<*>? = bundle[PDU] as Array<*>?

                    if (protocolDataUnits != null) {

                        Toast.makeText(context, "${protocolDataUnits.toList()}", Toast.LENGTH_SHORT)
                            .show()

                        for (i in protocolDataUnits.indices) {
                            val currentSms = getIncomingMessage(protocolDataUnits[i], bundle)
                            val fromAddress = currentSms.displayOriginatingAddress
                            val message = currentSms.displayMessageBody

                            Log.d(TAG, "fromAddress: $fromAddress")
                            Log.d(TAG, "message: $message")

                        }
                        //   this.abortBroadcast()  // End of loop

                    } else {
                        Toast.makeText(context, "PDU list is null", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(context, "Bundle is null", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(context, "Intent's action is not sms received", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(context, "Intent is null", Toast.LENGTH_SHORT).show()
        }
    }

    private fun getIncomingMessage(any: Any?, bundle: Bundle): SmsMessage {
        val currentSMS: SmsMessage = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val format = bundle.getString("format")
            SmsMessage.createFromPdu(any as ByteArray, format)
        } else {
            SmsMessage.createFromPdu(any as ByteArray)
        }
        return currentSMS
    }
}

