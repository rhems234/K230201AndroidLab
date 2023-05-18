package com.example.test13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import com.example.test13.databinding.ActivityMain443Binding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis

class MainActivity443 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain443Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button.setOnClickListener {
           /* var sum = 0L
            var time = measureTimeMillis {
                for(i in 1..1_000_000){
                    sum += i
                }
            }
            Log.d("lsy","time : $time")
            binding.resultView.text = "sum : $sum"*/

            // 방법1 핸들러로 비동기 처리.
            val handler=object: Handler(){
                override fun handleMessage(msg: Message) {
                    super.handleMessage(msg)
                    binding.resultView.text = "sum : ${msg.arg1}"
                }
            }

            thread {
                var sum = 0L
                var time = measureTimeMillis {
                    for (i in 1..10_000_000_000) {
                        sum += i
                    }
                    val message = Message()
                    message.arg1=sum.toInt()
                    handler.sendMessage(message)
                }
                Log.d("kkang", "time : $time")
            }

            // 방법2 코루틴으로 비동기 처리. 결과는 거의 비슷
            // 차이점 권고 및 앞으로 계속 지원등으로, 코루틴 이용.]
            // 이미 많은 API 등에서 채택에서 서비스에 적용중.
            // 그래서 API 등을 이용시, 비동기 처리로 인해서 빠른 성능을 볼 수 있음.
//            val channel = Channel<Int>()
//
//            val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
//            backgroundScope.launch {
//                var sum = 0L
//                var time = measureTimeMillis {
//                    for (i in 1..2_000_000_000) {
//                        sum += i
//                    }
//                }
//                Log.d("kkang", "time : $time")
//                channel.send(sum.toInt())
//            }
//
//            val mainScope= GlobalScope.launch(Dispatchers.Main) {
//                channel.consumeEach {
//                    binding.resultView.text = "sum : $it"
//                }
//            }
//

        }
    }
}