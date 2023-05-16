package com.example.test11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test11.databinding.ActivityMain355Binding

class MainActivity355 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main354)

        val binding= ActivityMain355Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val datas = mutableListOf<String>()
        for(i in 1..3){
            datas.add("Item $i")
        }
        // 뷰 페이저2, 설정 방식 중에서, 프래그먼트 방식의 어댑터 설정 및 적용 부분.
        val adapter= MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter
    }

    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            // MyFragmentPagerAdapter 생성자 호출시 마다, 항상 실행되는 코드 부분.
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
            Log.d("kkang" ,"fragments size : ${fragments.size}")
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}