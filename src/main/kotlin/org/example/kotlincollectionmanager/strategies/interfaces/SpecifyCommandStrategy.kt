package org.example.kotlincollectionmanager.strategies.interfaces

import org.example.kotlincollectionmanager.command.validators.dto.FlatData

/**
 * Interface for strategies that implement commands to perform operations on collection items.
 * Provides methods for performing operations both manually and automatically.
 */
interface SpecifyCommandStrategy {
    /**
     * Performs the operation on the specified identifier in manual mode.
     *
     * @param id is the ID of the element to perform the operation.
     */
    fun lifeTimeExecution(id: Long?)

    /**
     * Performs an automatic operation on the specified ID and data.
     *
     * @param id is the ID of the element to perform the operation.
     * @param data is the data of the element to perform the operation.
     */
    fun automaticallyExecution(id: Long?, data: FlatData)
}