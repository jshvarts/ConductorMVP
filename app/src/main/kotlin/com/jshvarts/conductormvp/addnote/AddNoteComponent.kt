package com.jshvarts.conductormvp.addnote

import com.jshvarts.conductormvp.app.AppComponent
import com.jshvarts.conductormvp.app.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(AddNoteModule::class),
        dependencies = arrayOf(AppComponent::class))
interface AddNoteComponent {
    fun inject(view: AddNoteView)
}