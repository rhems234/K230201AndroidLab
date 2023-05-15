package com.example.test10

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat

class ReplyReceiver : BroadcastReceiver() {
    // 4대 컴포넌트 구성요소 중의 하나이고,
    // 담당 업무는 앱이 백그라운드에서 실행하는 부분을 처리하는 역할.

    override fun onReceive(context: Context, intent: Intent) {
        val replyText = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_reply")
        Log.d("lsy", "replyText 답장 받은 내용 확인 : $replyText")

        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE)
                as NotificationManager
        val builder : NotificationCompat.Builder

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "one-channel"
            val channelName = "My Channel One"
            val channel = NotificationChannel(
                channelId,
                channelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(context,channelId)
        } else {
            builder = NotificationCompat.Builder(context)
        }
        builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
        builder.setWhen(System.currentTimeMillis())
        builder.setContentTitle("더미 임시 제목")
        builder.setContentText(replyText)

        manager.notify(11, builder.build())
    }
}