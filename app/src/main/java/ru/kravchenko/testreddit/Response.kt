package ru.kravchenko.testreddit


data class Result(val data: Data)

data class Data(val children: List<Children>)

data class Children(val data: Datas)

data class Datas(
    val title: String,
    val author: String,
    val thumbnail: String,
    val num_comments: String,
    val created: Long,
    val url_overridden_by_dest: String
)