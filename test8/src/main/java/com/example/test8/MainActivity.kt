package com.example.test8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.CompoundButton
import com.example.test8.databinding.ActivityMainBinding

// 방법2. 클래스로 구현( 인터페이스 구현 )
class MyEventHandler : CompoundButton.OnCheckedChangeListener{
    override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("lsy", "클래스로 구현 방법 2 체크박스 클릭.")
    }
}

//class MainActivity : AppCompatActivity(), CompoundButton.OnCheckedChangeListener {

class MainActivity : AppCompatActivity() {

    // 뷰 이벤트 처리 방법1, 인터페이스 구현시 ,재정의
    /*override fun onCheckedChanged(buttonView: CompoundButton?, isChecked: Boolean) {
        Log.d("lsy", "체크박스 클릭.")
    }*/

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KeyEvent.KEYCODE_BACK -> Log.d("lsy", "Back Key Click")
            KeyEvent.KEYCODE_VOLUME_UP -> Log.d("lsy", "Volume Up")
            KeyEvent.KEYCODE_VOLUME_DOWN -> Log.d("lsy", "Volume Down")
        }
//        Log.d("lsy", "onKeyDown start")
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("lsy", "onKeyUp start")
        return super.onKeyUp(keyCode, event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("lsy","좌표 x값 : ${event.x}, rawX 좌표 : ${event.rawX}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("lsy","Touch up event 발생함.")
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
//      setContentView(R.layout.activity_main)
        setContentView(binding.root)

        // 방법 1.
        //리스너 인터페이스를 메인 액티비에서 구현 후, 이벤트 처리 방법.
        //binding.check1.setOnCheckedChangeListener(this)

        // 방법2.
        // 클래스로 정의된 리스너를 사용함.
        //binding.check1.setOnCheckedChangeListener(MyEventHandler())

        // 방법3 : SAM 기법(Single Abstract Method), JAVA : 함수형 인터페이스, 람다식 구현.
        // 인터페이스 : 구성을 추상 메서드로 구성된 것.
        // 추상 메서드 : 메서드의 선언부는 있지만, 구현부가 없는 것 말함.
        // 보통 인터페이스를 구현해서 사용할 때, 보통 재정의해서 사용함.
        binding.check1.setOnCheckedChangeListener {
            a, b -> Log.d("lsy", "방법3 SAM 기법 구현 : 체크박스 클릭")
        }

        binding.btn1.setOnLongClickListener {
            Log.d("lsy", "롱 클릭")
            true
        }


    }
}