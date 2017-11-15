package com.jshvarts.conductormvp.colors

import com.jshvarts.conductormvp.app.PerScreen
import dagger.Module
import dagger.Provides

@Module
class ColorsModule {

    @PerScreen
    @Provides
    fun provideView() = ColorsView()

    @PerScreen
    @Provides
    fun providePresenter() = ColorsPresenter()
}