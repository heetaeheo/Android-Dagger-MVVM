package com.example.dagger_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger_exam.databinding.ActivityMainBinding
import com.example.dagger_exam.model.RecyclerList

class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding

    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var mainActivityViewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
    }



    private fun initRecyclerView(){
        binding.recyc.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = RecyclerViewAdapter()
        binding.recyc.adapter = recyclerViewAdapter
    }

    private fun initViewModel(){
        mainActivityViewModel= ViewModelProvider(this).get(MainActivityViewModel::class.java)
        mainActivityViewModel.getLiveDataObserver().observe(this, object : Observer<RecyclerList>{
            override fun onChanged(t: RecyclerList?) {
                if(t != null){
                    recyclerViewAdapter.setupdatedData(t.items)
                    recyclerViewAdapter.notifyDataSetChanged()
                }else{
                    Toast.makeText(this@MainActivity,"error",Toast.LENGTH_SHORT).show()

                }
            }
        })
        mainActivityViewModel.makeApicall()
    }
}