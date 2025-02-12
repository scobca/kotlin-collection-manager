package org.example.kotlincollectionmanager.collection

import org.example.kotlincollectionmanager.collection.items.Flat
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.util.*

@Component
class Collection() {
    private val initDate: ZonedDateTime = ZonedDateTime.now()
    var flats = TreeMap<Long, Flat>()
}