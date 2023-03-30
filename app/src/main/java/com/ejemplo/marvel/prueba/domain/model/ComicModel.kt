package com.ejemplo.marvel.prueba.domain.model

import android.media.Image
import android.os.Parcel
import android.os.Parcelable
import com.ejemplo.marvel.prueba.data.data_source.dto.Thumbnail

data class ComicModel(

    val id: Int,
    val title: String?,
    val description: String?,
    val thumbnail: Thumbnail?
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readParcelable(Thumbnail::class.java.classLoader)
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(description)
        parcel.writeParcelable(thumbnail, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ComicModel> {
        override fun createFromParcel(parcel: Parcel): ComicModel {
            return ComicModel(parcel)
        }

        override fun newArray(size: Int): Array<ComicModel?> {
            return arrayOfNulls(size)
        }
    }
}

