package com.example.demowithrecylerview

import java.util.*

data class Movie private constructor(var id: String, var name: String, var rating: String) {

    constructor(name:String,rating:String):this(UUID.randomUUID().toString(),name,rating)

    override fun toString(): String {
        return "Movie(id='$id', name='$name', rating='$rating')"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (name != other.name) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + rating.hashCode()
        return result
    }


}