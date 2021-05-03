package com.roksky.sample

import java.util.*

data class Client(
    var id: String?,
    var name: String?,
    var area: String?,
    var allocatedBags: Int?,
    var bagType: String?,
    var extraBags: Int?,
    var status: Int?,
    var latitude: String?,
    var longitude: String?,
    var dateUpdate: Date?
)