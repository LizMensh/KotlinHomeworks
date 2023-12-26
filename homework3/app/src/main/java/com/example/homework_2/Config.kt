package com.example.homework_2

const val CountAddImages : Int = 10

enum class ResponseParameters(val value : String)
{
    API_KEY("LDcyZ649GUV7Biq7Sucp7aZoNzi1PlhD"),
    Q("Gojo"),
    RATING("g"),
    LANG("en"),
}

enum class StatusLoad
{
    LOADING,
    SUCCESS,
    ERROR,
    DEFAULT
}