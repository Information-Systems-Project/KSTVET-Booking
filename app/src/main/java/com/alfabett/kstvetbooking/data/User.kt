package com.alfabett.kstvetbooking.data

data class User(
    val name:String,
    val email:String,
    val adm:String,
    val phone:String,
    val gender:String,
    val password:String
)

data class UserProfile(
    val name:String?,
    val email:String?,
    val adm:String?,
    val phone:String?,
    val checkin:String?,
    val checkout:String?,
    val room: String?
)
