package com.example.applicationkino.Model.Random

data class Random(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)