package com.deindeal.app.data.model

data class Filter(
    val id: String,
    val label: String?,
    val icon: String?


) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Filter

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}
