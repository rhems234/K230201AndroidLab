package com.example.test10

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.test10.databinding.ActivityMain305Binding
import kotlin.concurrent.thread

class MainActivity305 : AppCompatActivity() {
    // 전체 소스 코드(답지 코드)에서, Test10 폴더 안에,
    // MainActivity305, 306 각각 XML 화면, 샘플 이미지 3장 복사함.
    // 매니페스트 파일에서 해당 액티비트 export 설정 true 변경.
    // 주의사항, 해당앱을 최초 설치 및 실행 하면, 기본 권한 설정은 다 거부입니다.
    // 그래서, 앱을 길게 눌러서 -> 앱 정보 -> 권한 에서 허용을 클릭 하면,
    // 우리가 만든 채널의 정보 창이 보입니다.
    // 테스트 가능함.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain305Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            val builder: NotificationCompat.Builder

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channelId = "one-channel"
                val channelName = "My Channel One"
                val channel = NotificationChannel(
                    channelId,
                    channelName,
                    NotificationManager.IMPORTANCE_LOW
                )
                //채널에 다양한 정보 설정
                channel.description = "My Channel One Description"
                manager.createNotificationChannel(channel)

                //채널을 이용하여 builder 생성
                builder = NotificationCompat.Builder(this, channelId)
            } else {
                builder = NotificationCompat.Builder(this)
            }

            builder.setSmallIcon(android.R.drawable.ic_notification_overlay)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("Content Title")
            builder.setContentText("Content Massage")

            builder.setProgress(100, 0, false)
            manager.notify(11, builder.build())

            //스레드, 간단한 예
            // File IO, 입력과 출력을 동시에 진행할 때,
            // 채팅 예), 제가 채팅 메세지를 입력과 동시에 메시지 수신을 받을 수 있음.
            // 스레드 없이, 동작을 하게 되면, 동기적 진행, 무조건, 앞에 어떤 프로세스 실행이
            // 끝난 다음에, 메세지를 받을 수 있음.
            // 앱에서 연산을 수행하는 부분이 시간이 좀 걸림.
            // 동기적으로 처리를 한다면, 오래 걸리는 작업이 순서가 앞에 나왔다.
            // 그러면, 뒤에 서비스는 아무것도 동작 할 수 없습니다.
            // 비동기적 처리를, 멀티 스레드로 동작을, 병렬 처리 가능.
            // 스레드 업그레이드 -> 경량식 스레드 -> 코루틴 작업을 함.
            // 실제로 많은 외부 API들은 이미 coroutine 기법을 이용해서, 비동기적으로 작업을 많이 함.
            // 일단, 현재 우리는 잘 이용하는 것에 초점을 맞추고,
            // 이제, 어느정도 실력이 쌓이면, 그 때, API, 라이브러리를 직접 만들어서, 이용하면 됨.

            thread {
                for (i in 1..100){
                    builder.setProgress(100, i, true)
                    manager.notify(11, builder.build())
                    SystemClock.sleep(100)
                }
            }
        }
    }
}