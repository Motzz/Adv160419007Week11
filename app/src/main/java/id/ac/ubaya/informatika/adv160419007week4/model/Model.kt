package id.ac.ubaya.informatika.adv160419007week4.model

import com.google.gson.annotations.SerializedName

data class Student(
    val id:String?,
    @SerializedName("student_name")//untuk parsing nama dari json buat parsingin
    val name:String?,
    @SerializedName("birth_of_date")
    val bod:String?,
    @SerializedName("phone")
    val Phone:String?,
    @SerializedName("photo_url")
    val photoUrl:String?
)