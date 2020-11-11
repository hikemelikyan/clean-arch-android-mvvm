package com.hmelikyan.newsletter.ui.mainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.ActivityMainBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivityMVVM<ActivityMainBinding, MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)
        setSupportActionBar(mBinding.toolbar)

        mBinding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        DaggerMainActivityComponent.builder()
    }

    override val viewModelType: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {

    }

    override fun onRetryRequest() {

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}