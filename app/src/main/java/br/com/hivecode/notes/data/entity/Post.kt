package br.com.hivecode.notes.data.entity

data class Post(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)