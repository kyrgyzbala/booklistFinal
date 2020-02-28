package com.example.booklist01

class BooksT(
    val items: List<Item> = ArrayList(),
    val totalItems: Int
)

data class Item(
    val volumeInfo: VolumeInfo
)

data class VolumeInfo(
    val authors: List<String>,
    val averageRating: Double,
    val contentVersion: String,
    val imageLinks: ImageLinks,
    val pageCount: Int,
    val previewLink: String,
    val publishedDate: String,
    val title: String
)

data class ImageLinks(
    val smallThumbnail: String,
    val thumbnail: String
)

