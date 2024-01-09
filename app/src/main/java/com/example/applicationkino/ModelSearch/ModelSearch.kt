package com.example.applicationkino.ModelSearch

data class ModelSearch(
    val docs: List<Doc>,
    val limit: Int,
    val page: Int,
    val pages: Int,
    val total: Int
)