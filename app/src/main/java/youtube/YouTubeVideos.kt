package youtube

import android.os.Parcel
import android.os.Parcelable

data class YouTubeVideos(val image:Int, val name:String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<YouTubeVideos> {
        override fun createFromParcel(parcel: Parcel): YouTubeVideos {
            return YouTubeVideos(parcel)
        }

        override fun newArray(size: Int): Array<YouTubeVideos?> {
            return arrayOfNulls(size)
        }
    }
}
