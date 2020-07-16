package com.example.demowithrecylerview

import android.graphics.Color
import android.graphics.Rect
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.EdgeEffect
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import java.util.*

class MainActivity : AppCompatActivity(), MyItemClick {
    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    var personList: ArrayList<Person> = AllPerson.getAllPerson()
    lateinit var person: Person
    var position: Int = 0
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.recylerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        myAdapter = MyAdapter(personList, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = myAdapter
        var div: DividerItemDecoration =
            DividerItemDecoration(recyclerView.context, RecyclerView.VERTICAL)
        recyclerView.addItemDecoration(div)

        recyclerView.edgeEffectFactory = object : RecyclerView.EdgeEffectFactory() {
            override fun createEdgeEffect(view: RecyclerView, direction: Int): EdgeEffect {
                return EdgeEffect(view.context).apply { setColor(Color.RED) }
            }
        }

        ItemTouchHelper(
            CustomItemTouchHelper(
                ItemTouchHelper.UP or ItemTouchHelper.DOWN or ItemTouchHelper.START or ItemTouchHelper.END,
                LEFT
            )
        ).attachToRecyclerView(
            recyclerView
        )
        recyclerView.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                outRect.bottom = 100
                outRect.left = 160
                outRect.right = 100
                if (parent.getChildAdapterPosition(view) == parent.adapter?.itemCount!! - 1) {
                    outRect.bottom = 0

                }
            }
        })
        swipeRefreshLayout.setProgressBackgroundColorSchemeColor(Color.YELLOW)
//        swipeRefreshLayout.setColorSchemeColors(Color.BLUE, Color.GREEN)
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorAccent)
        swipeRefreshLayout.setColorSchemeResources(R.color.green, R.color.magenta)
//        swipeRefreshLayout.setProgressViewEndTarget(true,500)
        swipeRefreshLayout.setProgressViewOffset(true, 100, 500)
//        swipeRefreshLay out.setSize(SwipeRefreshLayout.LARGE)
        swipeRefreshLayout.setSlingshotDistance(200)
        swipeRefreshLayout.isHapticFeedbackEnabled = true
        swipeRefreshLayout.isHovered = true
        swipeRefreshLayout.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                var person = Person("Google", 12)
                personList.add(person)
                myAdapter.addToAllPersonList(person)
                myAdapter.notifyDataSetChanged()
                swipeRefreshLayout.isRefreshing = false

            }

        })


    }

    inner class CustomItemTouchHelper(dragInts: Int, swipeDirs: Int) :
        ItemTouchHelper.SimpleCallback(dragInts, swipeDirs) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            var fromPosition = viewHolder.adapterPosition
            var toPosition = target.adapterPosition
            Collections.swap(personList, fromPosition, toPosition)
            recyclerView.adapter?.notifyItemMoved(fromPosition, toPosition)
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            when (direction) {
                LEFT -> {
                    person = personList.get(viewHolder.adapterPosition)
                    position = viewHolder.adapterPosition

                    personList.remove(personList.get(viewHolder.adapterPosition))
                    myAdapter.notifyDataSetChanged()
                    Snackbar.make(recyclerView, "Deleted" as CharSequence, Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", object : View.OnClickListener {
                            override fun onClick(v: View?) {
                                personList.add(position, person)
                                myAdapter.notifyItemInserted(position)
                            }
                        }).show()
                }
            }

        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        var menuItem = menu?.findItem(R.id.search)
        var searchView = menuItem?.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            View.OnFocusChangeListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                myAdapter.filter.filter(newText)
                return false
            }

            override fun onFocusChange(v: View?, hasFocus: Boolean) {


                return
            }


        })
        searchView.setOnCloseListener(object : SearchView.OnCloseListener {
            override fun onClose(): Boolean {
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)


    }

    override fun onClicked(name: String) {
        showToast(name)
    }


}


