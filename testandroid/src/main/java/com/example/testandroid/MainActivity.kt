package com.example.testandroid

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testandroid.databinding.ActivityMainBinding
import com.example.testandroid.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.GoLogin.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        // 툴바 적용하기
        setSupportActionBar(binding.toolbar)

        //ActionBarDrawerToggle 버튼 적용
        // 꾸미는 작업은 네비게이션 뷰의 작업
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_opened, R.string.drawer_closed)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("Item $i")
        }

        // 리사이클러뷰 적용하기
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerview.layoutManager = layoutManager
        val adapter = MyAdapter(datas)
        binding.recyclerview.adapter = adapter
        binding.recyclerview.addItemDecoration(MyDecoration(this))
    }

    class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

    class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        override fun getItemCount(): Int{
            return datas.size
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
                = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val binding=(holder as MyViewHolder).binding
            binding.itemData.text= datas[position]
        }
    }

    class MyDecoration(val context: Context): RecyclerView.ItemDecoration() {

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            val index = parent.getChildAdapterPosition(view) + 1

            if (index % 3 == 0) //left, top, right, bottom
                outRect.set(10, 10, 10, 60)
            else
                outRect.set(10, 10, 10, 0)

            view.setBackgroundColor(Color.parseColor("#28A0FF"))
            ViewCompat.setElevation(view, 20.0f)

        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //이벤트가 toggle 버튼에서 제공된거라면..
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}