package com.miftah.absensipresiden

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class President(
    val nama: String,
    val negara : String,
    val masa : String,
    val pandangan : String,
    val bio : String,
    val foto : String,
    var check : Int
) : Parcelable