package org.example.kotlincollectionmanager.collection.items

/**
 * A list of possible interior decoration options.
 * It is used to indicate the finish level in Flat objects.
 */
enum class Furnish {
    /**
     * The finish is in the designer's style.
     */
    DESIGNER,

    /**
     * High-quality finish.
     */
    FINE,

    /**
     * Minimal finishing.
     */
    LITTLE,
    /**
     * Lack of finishing.
     */
    NONE;

    /**
     * A companion object for providing additional methods.
     */
    companion object {
        /**
         * Checks the string and returns the corresponding variant of the Furnish enum.
         * If the string does not match any of the options, returns NONE.
         * The string is converted to uppercase before checking.
         *
         * @param type is the string to check.
         * @return is the corresponding variant of the Furnish enumeration.
         */
        fun validate(type: String): Furnish {
            return try {
                Furnish.valueOf(type.uppercase())
            } catch (e: IllegalArgumentException) {
                NONE
            }
        }
    }
}
