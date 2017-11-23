package com.jshvarts.conductormvp.editnote

import com.jshvarts.conductormvp.di.AppComponent
import com.jshvarts.conductormvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(EditNoteModule::class),
        dependencies = arrayOf(AppComponent::class))
interface EditNoteComponent {
    fun inject(view: EditNoteView)
}