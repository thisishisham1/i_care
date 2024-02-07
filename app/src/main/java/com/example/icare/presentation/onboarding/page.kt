package com.example.icare.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.icare.R

data class Page(
    @DrawableRes val imgRes: Int,
    val title: Int,
    val description: String
)


val pages = arrayOf(
    Page(
        imgRes = R.drawable.robot,
        title = R.string.on_boarding1,
        description = "page one"
    ),
    Page(
        imgRes = R.drawable.doctor,
        title = R.string.on_boarding2,
        description = "page two"
    ),
    Page(
        imgRes = R.drawable.pharmacy,
        title = R.string.on_boarding3,
        description = "page three "
    ),
    Page(
        imgRes = R.drawable.lab,
        title = R.string.on_boarding4,
        description = "page three"
    )
)
