package org.example.kotlincollectionmanager.strategies.interfaces

import org.example.kotlincollectionmanager.command.validators.dto.FlatData

interface SpecifyCommandStrategy {
    fun lifeTimeInsert(id: Long?)
    fun automaticallyInsert(id: Long?, data: FlatData)
}