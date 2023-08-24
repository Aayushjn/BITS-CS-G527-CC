package xyz.ajraspi.cc.webservice.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Course(
    @SerialName("cid") val id: Int,
    @SerialName("cname") val name: String,
    val description: String,
)
