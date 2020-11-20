package com.hmelikyan.newsletter.ui.mainActivity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.hmelikyan.newsletter.R
import com.hmelikyan.newsletter.databinding.ActivityMainBinding
import com.hmelikyan.newsletter.mvvm.ui.BaseActivityMVVM
import com.hmelikyan.newsletter.mvvm.vm.ViewCommand
import com.hmelikyan.newsletter.ui.commands.Commands
import com.hmelikyan.newsletter.ui.mainActivity.adapters.TestAdapter

class MainActivity : BaseActivityMVVM<ActivityMainBinding,MainViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(mBinding.toolbar)
        mViewModel.getTest()
        mBinding.fab.setOnClickListener {
            mViewModel.getList()
        }
        mBinding.rvTest.adapter = TestAdapter()
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

    override val viewModelType: Class<MainViewModel>
        get() = MainViewModel::class.java
    override val inflate: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun proceedViewCommand(command: ViewCommand) {
        when(command){
            is Commands.TestViewCommand -> showToast(command.list?.size.toString())
            is Commands.ShowLoadingViewCommand -> showToast("Loading")
            is Commands.PagingViewCommand -> ( mBinding.rvTest.adapter as TestAdapter).submitData(lifecycle, command.list)
        }
    }

    override fun onRetryRequest() {

    }
}