package org.example.kotlincollectionmanager.collection

import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class Collection {
    private val initDate: ZonedDateTime = ZonedDateTime.now()
    private var flats = TreeMap<Long, Flat>()

    fun getInitDate(): ZonedDateTime = initDate
    fun getFlats(): TreeMap<Long, Flat> = flats
}