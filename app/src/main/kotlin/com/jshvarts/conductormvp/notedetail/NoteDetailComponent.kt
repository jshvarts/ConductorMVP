package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.app.AppComponent
import com.jshvarts.conductormvp.app.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(NoteDetailModule::class),
        dependencies = arrayOf(AppComponent::class))
interface NoteDetailComponent {
    fun inject(view: NoteDetailView)
}