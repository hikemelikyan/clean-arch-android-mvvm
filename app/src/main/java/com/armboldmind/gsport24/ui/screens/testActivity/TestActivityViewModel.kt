package com.armboldmind.gsport24.ui.screens.testActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.armboldmind.gsport24.data.root.UIState
import com.armboldmind.gsport24.domain.entities.NotificationDomain
import com.armboldmind.gsport24.mvvm.vm.BaseViewModel
import kotlinx.coroutines.delay
import java.util.*

class TestActivityViewModel : BaseViewModel() {

    init {
        setData()
    }

    private val _items: MutableLiveData<List<NotificationDomain>> by lazy { MutableLiveData() }
    val items: LiveData<List<NotificationDomain>>
        get() = _items

    private fun setData() {
        launchDefault {
            switchUIState(UIState.LOADING)

            delay(1500)
            val list = listOf(
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false),
                NotificationDomain(UUID.randomUUID().mostSignificantBits.toInt(),"Football","15 min until start", "asdasdas",true,4,78,false)
            )
            _items.postValue(list)
            switchUIState(UIState.SUCCESS)

            /*while (true){
                delay(1000)

                _items.postValue(list.shuffled())

            }*/
        }
    }

}