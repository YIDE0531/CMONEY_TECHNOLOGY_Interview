package com.nuu.cmoney_technology_interview

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nuu.cmoney_technology_interview.databinding.ActivityLoginBinding
import com.nuu.cmoney_technology_interview.viewmodel.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private var context: Context? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.viewModel = loginViewModel
        binding.lifecycleOwner = this
        context = this

        loginViewModel.clickEvent.observe(this, Observer {
            val intent = Intent(context, ThumbnailActivity::class.java)
            startActivity(intent)
        })
    }
}