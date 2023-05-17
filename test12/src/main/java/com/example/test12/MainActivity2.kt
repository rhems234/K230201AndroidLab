package com.example.test12

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.test12.databinding.ActivityMain2Binding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity2 : AppCompatActivity() {
    // 참고 코드 : MainActivity388
    // 탭 만 따로 구성하는게 아니라, 뷰페이저2 라는 뷰와 연동을 함.
    // 연동하는 과정에서 해당 탭 설정추가.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // 탭 뷰 부분을 추가 -> xml 에서 작업을 먼저 했음.
        val tabLayout = binding.tabs

        // 뷰 페이저 2 추가. -> xml 에서 , viewpager 부분을 추가해야함.
        val viewPager = binding.viewpager

        // 뷰 페이저2에 어댑터 추가하는 부분 방법 2가지,
        // 1) 어댑터 (리사이클러뷰방식).
        // 2) 프래그먼트로 추가하는 방식. -> 이 방식으로 작업을 많이 함.
        // 2번 방식으로 프래그먼트 클래스 등록 하기.
        // 현재, 액티비티 파일 안에 클래스를 만들어서 작업을 하지만,
        // 사실, 프래그먼트 끼리 폴더를 만들어서 따로 관리.
        // 지금은 간단해서, 여기 내부에서 작업을 함.

        // 뷰페이저2 부분에 설정 한 부분 적용하기. 어댑터 연결.
        // 어댑터, 뷰 객체에 데이터를 연결(연동) 하는 부분.
        viewPager.adapter= MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position + 1)}"
        }.attach()
    }

    // 프래그먼트 어댑터 클래스 재사용.
    // 원래는 각각 프래그먼트 또한 만들어야 하지만,
    // 지금은 재사용. OneFragment(), TwoFragment(), ThreeFragment()
    // 복사 해도 되지만, 파일을 만들어서 ,복사하기.
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments= listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }
        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]
    }
}