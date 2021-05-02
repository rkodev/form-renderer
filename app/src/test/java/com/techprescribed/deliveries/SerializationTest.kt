package com.roksky.sample

import com.google.gson.GsonBuilder
import com.roksky.sample.app.DateDeserializer
import com.roksky.sample.ui.home.Trip
import org.junit.Assert
import org.junit.Test
import java.util.*

class SerializationTest {

    @Test
    fun canParseDates() {

        val dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"

        val gsonSerializer = GsonBuilder()
            .setDateFormat(dateFormat)
            .registerTypeAdapter(Date::class.java,
                com.roksky.sample.app.DateDeserializer()
            )
            .create()

        val data = """
            {
                "id": "0bb4e8b9-1df0-489d-9b6c-f27d7da5c0a4",
                "trip_date_created": "2021-02-03T00:00:00.000000Z",
                "trip_driver": "test@gitau.com",
                "trip_vehicle": "KBC 438",
                "trip_status": "COMPLETED",
                "trip_loading_time": "2021-02-03T17:59:29.000000Z",
                "trip_photo": null,
                "trip_from_port": "Kikuyu",
                "trip_from_port_weight": 28000,
                "trip_destination_port": "Rongai",
                "trip_destination_filledby": null,
                "trip_destination_initial_photo": "f29e27a2-c724-46e3-9f3d-d3b5d91a0c5d.jpg",
                "trip_destination_final_photo": "6b5cbcf9-927c-45dd-ae25-1e86fd51aed0.jpg",
                "trip_destination_filled_by": "trailer@test.com",
                "trip_destination_arrival_time": "2021-02-06T19:02:29.000000Z",
                "trip_destination_inital_weight": 15000,
                "trip_destination_final_weight": 13000,
                "trip_destination_net_weight": 2000,
                "trip_date_deleted": null,
                "created_at": "2021-02-03T14:59:30.000000Z",
                "updated_at": "2021-02-08T13:54:02.000000Z"
              }
        """.trimIndent()

        val trip = gsonSerializer.fromJson(data, Trip::class.java)
        Assert.assertNotNull(
            trip.dateCreated
        )

    }
}