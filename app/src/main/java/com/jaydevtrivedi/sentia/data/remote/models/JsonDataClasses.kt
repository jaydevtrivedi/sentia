package com.jaydevtrivedi.sentia.data.remote.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Json4Kotlin_Base (
    val ad_id : Int,
    val data : Data,
    val title : String
)

@JsonClass(generateAdapter = true)
data class Data (
    val listings : List<Listings>
)

@JsonClass(generateAdapter = true)
data class Listings (

    val Id : String,
    val AgencyLogoUrl : String,
    val Area : String,
    val AuctionDate : String,
    val AvailableFrom : String?,
    val Bathrooms : Int,
    val Bedrooms : Int,
    val Carspaces : Int,
    val DateFirstListed : String,
    val DateUpdated : String,
    val Description : String,
    val DisplayPrice : String,
    val Currency : String,
    val Location : Location,
    val owner : Owner,
    val ImageUrls : List<String>,
    val is_premium : Int,
    val IsPriority : Int,
    val Latitude : Double,
    val ListingPrice : String?,
    val ListingStatistics : String?,
    val ListingType : String,
    val ListingTypeString : String,
    val Longitude : Double
)

@JsonClass(generateAdapter = true)
data class Location (

    val Address : String,
    val Address2 : String,
    val State : String,
    val Suburb : String
)

@JsonClass(generateAdapter = true)
data class Owner (

    val name : String,
    val lastName : String,
    val dob : String,
    val image : Image?
)

@JsonClass(generateAdapter = true)
data class Image (
    val big : Big,
    val small : Small,
    val medium : Medium
)

@JsonClass(generateAdapter = true)
data class Big (
    val url : String
)

@JsonClass(generateAdapter = true)
data class Small (
    val url : String
)

@JsonClass(generateAdapter = true)
data class Medium (
    val url : String
)