package com.example.retrofit_error_handling.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.retrofit_error_handling.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var bookAdapter: BookAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setBookRecyclerView()
    }


    private fun setBookRecyclerView() {
        bookAdapter = BookAdapter()
        binding.rvBookList.adapter = bookAdapter

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                mainViewModel.bookList.collectLatest {
                    bookAdapter.submitList(it)
                }
            }
        }
    }

}