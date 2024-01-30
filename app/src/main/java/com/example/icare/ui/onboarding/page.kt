package com.example.icare.ui.onboarding

import androidx.annotation.DrawableRes
import com.example.icare.R

data class Page(
    @DrawableRes val imgRes: Int,
    val title: String,
    val description: String
)


val pages = arrayOf(
    Page(
        imgRes = R.drawable.robot,
        title = "Digital Smart Diagnoses,\nInstant Relief! ",
        description = "page one"
    ),
    Page(
        imgRes = R.drawable.doctor,
        title = "Find a lot of specialist\ndoctors in one place",
        description = "page two"
    ),
    Page(
        imgRes = R.drawable.pharmacy,
        title = "Find Pharmacies\nNearby",
        description = "page three "
    ),
    Page(
        imgRes = R.drawable.lab,
        title = "Book, Visit, Chat Results.\nSwift Health in Three\nSteps!",
        description = "page three"
    )
)
