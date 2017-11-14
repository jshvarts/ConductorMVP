package com.jshvarts.conductormvp.colors

import com.jshvarts.conductormvp.app.PerScreen
import dagger.Binds
import dagger.Module

@Module
abstract class ColorsViewModule {

    @PerScreen
    @Binds
    abstract fun provideColorsView(view: ColorsView): ColorsContract.View
}