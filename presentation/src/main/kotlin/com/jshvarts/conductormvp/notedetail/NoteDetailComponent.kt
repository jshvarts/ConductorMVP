package com.jshvarts.conductormvp.notedetail

import com.jshvarts.conductormvp.di.AppComponent
import com.jshvarts.conductormvp.di.PerScreen
import dagger.Component

@PerScreen
@Component(modules = arrayOf(NoteDetailModule::class),
        dependencies = arrayOf(AppComponent::class))
interface NoteDetailComponent {
    fun inject(view: NoteDetailView)
}