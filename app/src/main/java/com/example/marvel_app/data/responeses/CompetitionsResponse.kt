package com.example.marvel_app.data.responeses

import com.google.gson.annotations.SerializedName


data class CompetitionsResponse (

    @SerializedName("count"        ) var count        : Int?                    = null,
    @SerializedName("competitions" ) var competitions : ArrayList<Competitions> = arrayListOf()
)
data class Area (

    @SerializedName("id"   ) var id   : Int?    = null,
    @SerializedName("name" ) var name : String? = null,
    @SerializedName("code" ) var code : String? = null,
    @SerializedName("flag" ) var flag : String? = null

)

data class CurrentSeason (

    @SerializedName("id"              ) var id              : Int?    = null,
    @SerializedName("startDate"       ) var startDate       : String? = null,
    @SerializedName("endDate"         ) var endDate         : String? = null,
    @SerializedName("currentMatchday" ) var currentMatchday : Int?    = null,
    @SerializedName("winner"          ) var winner          : String? = null

)


data class Competitions (

    @SerializedName("id"                       ) var id                       : Int?           = null,
    @SerializedName("area"                     ) var area                     : Area?          = Area(),
    @SerializedName("name"                     ) var name                     : String?        = null,
    @SerializedName("code"                     ) var code                     : String?        = null,
    @SerializedName("type"                     ) var type                     : String?        = null,
    @SerializedName("emblem"                   ) var emblem                   : String?        = null,
    @SerializedName("plan"                     ) var plan                     : String?        = null,
//    @SerializedName("currentSeason"            ) var currentSeason            : CurrentSeason? = CurrentSeason(),
    @SerializedName("numberOfAvailableSeasons" ) var numberOfAvailableSeasons : Int?           = null,
    @SerializedName("lastUpdated"              ) var lastUpdated              : String?        = null

)