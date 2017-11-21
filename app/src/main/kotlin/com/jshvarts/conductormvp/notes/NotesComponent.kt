package com.jshvarts.conductormvp.notes

import com.jshvarts.conductormvp.di.AppComponent
import com.jshvarts.conductormvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(NotesModule::class),
        dependencies = arrayOf(AppComponent::class))
interface NotesComponent {
    fun inject(view: NotesView)
}