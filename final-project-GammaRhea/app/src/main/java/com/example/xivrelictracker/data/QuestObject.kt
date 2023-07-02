package com.example.xivrelictracker.data

import com.squareup.moshi.FromJson
import java.io.Serializable
import com.example.xivrelictracker.data.ToDo

data class QuestObject(
    val name: String,
    val firstToDo: String,
    val toDos: List<String>
) : Serializable

val falList = listOf<Boolean>(false)

//Data classes to represent structure
data class XIVApiListJson(
    val Name: String,
    val TextData: XIVAPIListText
)

data class XIVAPIListText(
    val ToDo: List<XIVApiListToDos>
)

data class XIVApiListToDos(
    val Text: String
)

class XIVApiJsonAdapter {
    @FromJson
    fun questObjectFromJson(list: XIVApiListJson) = QuestObject(
        name = list.Name,
        firstToDo = list.TextData.ToDo[0].Text,
        toDos = list.TextData.ToDo.map { it.Text }
    )
}
