package com.blume.theapiproject

data class usersClass(
        val id :Int,
        val name : String,
        val username : String,
        val email :String,
        val address : userAdressClass,
        val geo : userGeoClass,
        val phone : String,
        val website :String,
        val company :userCompanyClass
)
