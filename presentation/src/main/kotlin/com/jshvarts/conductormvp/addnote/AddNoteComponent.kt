package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.di.AppComponent
import com.jshvarts.conductormvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(AddNoteModule::class),
        dependencies = arrayOf(AppComponent::class))
interface AddNoteComponent {
    fun inject(view: AddNoteView)
}