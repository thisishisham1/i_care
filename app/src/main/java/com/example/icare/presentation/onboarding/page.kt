package com.example.icare.presentation.onboarding

import androidx.annotation.DrawableRes
import com.example.icare.R

data class Page(
    @DrawableRes val imgRes: Int,
    val title: String,
    val description: String
)


val pages = listOf(
    Page(
        imgRes = R.drawable.page_1,
        title = "Find a lot of specialist\ndoctors in one place",
        description = "page one"
    ),
    Page(
        imgRes = R.drawable.page_2,
        title = "View and buy Medicine\nonline",
        description = "page two"
    ),
    Page(
        imgRes = R.drawable.page_3,
        title = "Quick Diagnoses, Instant\nRelief!",
        description = "page three "
    ),
    Page(
        imgRes = R.drawable.page_4,
        title = "Book, Visit, Chat Results.\nSwift Health in Three\nSteps!",
        description = "page three"
    )
)
