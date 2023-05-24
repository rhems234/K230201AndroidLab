package com.example.k0201_pjs_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.k0201_pjs_test.databinding.ActivityMainBinding
import com.example.k0201_pjs_test.model.PageListModel
import com.example.k0201_pjs_test.recycler.MyAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val serviceKey = "hhW7AMU4vwoVG1xBV%2FfnesXMaaMO77%2BHTacBdNBRMSzFaA8TV%2FCk2jSL2ffujNDwZGq5ddNhN%2BAc80WaWXc71A%3D%3D"
        val serviceKey2 = "hhW7AMU4vwoVG1xBV/fnesXMaaMO77+HTacBdNBRMSzFaA8TV/Ck2jSL2ffujNDwZGq5ddNhN+Ac80WaWXc71A=="
        val resultType ="json"

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val networkService = (applicationContext as MyApplication).networkService

        val userListCall = networkService.getList(serviceKey2,10,1,resultType)

        Log.d("lsy", "url:" + userListCall.request().url().toString())

        userListCall.enqueue(object : Callback<PageListModel> {

            override fun onResponse(call: Call<PageListModel>, response: Response<PageListModel>) {

                Log.d("lsy", "실행 여부 확인. userListCall.enqueue")
                val userList = response.body()

                Log.d("lsy","userList data 값 : ${userList?.getAttractionKr?.item}")
                Log.d("lsy","userList data 갯수 : ${userList?.getAttractionKr?.item?.size}")

                binding.recyclerView.adapter= MyAdapter(this@MainActivity,userList?.getAttractionKr?.item)


                binding.recyclerView.addItemDecoration(
                    DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL)
                )
            }
            override fun onFailure(call: retrofit2.Call<PageListModel>, t: Throwable) {
                Log.d("lsy","fail")
                call.cancel()
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuItem1 : MenuItem? = menu?.add(0,0,0,"메뉴1")
        val menuItem2 : MenuItem? = menu?.add(0,1,0,"메뉴2")
        menuInflater.inflate(R.menu.menu_navigation, menu)
        return super.onCreateOptionsMenu(menu)
    }
}