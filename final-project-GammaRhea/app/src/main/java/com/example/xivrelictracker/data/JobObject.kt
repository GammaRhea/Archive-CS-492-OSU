package com.example.xivrelictracker.data

import java.io.Serializable

data class JobObject(
    val name: String,
    val jobID: String,
    val relicOffset: Int
) : Serializable
