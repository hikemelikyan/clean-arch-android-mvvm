package com.hmelikyan.newsletter.ui.mainActivity

import com.hmelikyan.newsletter.di.AppComponent
import dagger.Component

@Component()
interface MainActivityComponent {

    fun inject(target:MainActivity)

}
