package com.bedirhandroid.pokemontechcase.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bedirhandroid.pokemontechcase.R
import com.bedirhandroid.pokemontechcase.databinding.ActivityMainBinding
import com.bedirhandroid.pokemontechcase.ui.second.activity.SecondActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewBinding()
        initViewModel()
        setListeners()
    }

    //check overlay permission
    private fun checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (Settings.canDrawOverlays(this)) {
                true -> goNextScreen()
                else -> {
                    Toast.makeText(
                        this,
                        getString(R.string.get_permissions_toast),
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }

    private fun initViewBinding() {
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    private fun setListeners() {
        viewBinding.apply {
            btnGetPermission.setOnClickListener {
                getPermissions()
            }
        }
    }

    private fun getPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (Settings.canDrawOverlays(this)) {
                true -> { Toast.makeText(this, "true", Toast.LENGTH_SHORT).show() }
                else -> goToSettingsPage()
            }
        }
    }

    private fun goToSettingsPage() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            when (Settings.canDrawOverlays(this)) {
                true -> goNextScreen()
                else -> {
                    Intent(
                        //intent directly app settings
                        Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:$packageName")
                    ).also { startActivity(it) }
                }
            }
        }
    }

    private fun goNextScreen() {
        Intent(this, SecondActivity::class.java).also {
            startActivity(it)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        if (!isDestroyed && !isFinishing) {
            checkPermissions()
        }
    }
}