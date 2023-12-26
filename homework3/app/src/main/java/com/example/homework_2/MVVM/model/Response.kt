package com.example.homework_2.MVVM.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("data") var data: ArrayList<Item> = arrayListOf()
) : java.io.Serializable
{
    data class Item(
        @SerializedName("id") var id: String = "",
        @SerializedName("images") var images: Images = Images(),
        @SerializedName("title") var title: String = "Title"
    )
    {
        data class Images(
            @SerializedName("original") var parameters: Parameters = Parameters()
        )
        {
            data class Parameters(
                @SerializedName("url") var url: String = "",
            )
        }

        val description: String = "Description"
    }
}
