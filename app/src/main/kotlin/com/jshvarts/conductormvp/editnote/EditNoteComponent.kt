package com.jshvarts.conductormvp.editnote

import com.jshvarts.conductormvp.app.AppComponent
import com.jshvarts.conductormvp.app.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(EditNoteModule::class),
        dependencies = arrayOf(AppComponent::class))
interface EditNoteComponent {
    fun inject(view: EditNoteView)
}