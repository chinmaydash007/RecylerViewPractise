package com.example.demowithrecylerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main2.*
import kotlin.random.Random

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        chatRecyclerView.adapter = ChatsAdapter(MyChats.getChats())


    }
}

object MyChats {
    fun getChats(): List<Chat> {
        var list = ArrayList<Chat>()
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        list.add(Chat("Hello ${Random.nextInt(100)}", false))
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        list.add(Chat("Hello ${Random.nextInt(100)}", false))
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        list.add(Chat("Hello ${Random.nextInt(100)}", false))
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        list.add(Chat("Hello ${Random.nextInt(100)}", false))
        list.add(Chat("Hello ${Random.nextInt(100)}", true))
        return list
    }

}