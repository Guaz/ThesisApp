package com.marcin.wac.thesisapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class ThesisModel(val thesisId: Long, val title: String, val description: String, val language: String,
                       val studentsSkills: String, val technologies: String, val promoterEmail: String,
                       val reviewerEmail: String, val reserved: Boolean, val occupied: Boolean, val studentEmail: String?,
                       val created: String, val lastModified: String?): Serializable, Parcelable