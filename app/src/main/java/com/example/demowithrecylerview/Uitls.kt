package com.example.demowithrecylerview

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlin.random.Random

object AllPerson {
    var getPerson = ArrayList<Person>()
    fun getAllPerson(): ArrayList<Person> {
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))
        getPerson.add(Person("Chinmay", Random.nextInt(100)))

        return getPerson
    }
}

fun log(message: String) {
    Log.d("mytag", message)
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}