package com.example.dagger_exam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_exam.databinding.ActivityMainBinding
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: MainActivityViewModel.Factory

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val mainActivityViewModel by viewModels<MainActivityViewModel> { viewModelFactory }

    private val component get() = (application as MyApplication).retroComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView(){
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyc.layoutManager = LinearLayoutManager(this)
        binding.recyc.adapter = recyclerViewAdapter
    }

    private fun initViewModel(){
        mainActivityViewModel.liveDataList.observe(this) { items ->
            if (items != null) {
                recyclerViewAdapter.listData = items.items
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this@MainActivity, "error", Toast.LENGTH_SHORT).show()
            }
        }
        mainActivityViewModel.makeApiCall()
    }
}