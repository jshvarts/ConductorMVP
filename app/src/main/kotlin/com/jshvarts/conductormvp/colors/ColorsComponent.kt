package com.jshvarts.conductormvp.colors

import com.jshvarts.conductormvp.app.AppComponent
import com.jshvarts.conductormvp.app.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(ColorsModule::class),
        dependencies = arrayOf(AppComponent::class))
interface ColorsComponent {
    fun inject(view: ColorsView)
}