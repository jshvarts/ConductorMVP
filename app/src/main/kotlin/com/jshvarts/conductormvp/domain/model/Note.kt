package com.jshvarts.conductormvp.domain.model

import android.os.Parcel
import android.os.Parcelable
import paperparcel.PaperParcel

/**
 * Data holder used by View layer.
 */
@PaperParcel
data class Note(
        val id: Long = 0,
        val noteText: String = ""
) : Parcelable {

    companion object {
        @JvmField val CREATOR = PaperParcelNote.CREATOR
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        PaperParcelNote.writeToParcel(this, dest, flags)
    }

    fun isValid(): Boolean = noteText.trim().length > 3
}