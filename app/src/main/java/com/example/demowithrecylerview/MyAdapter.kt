package com.example.demowithrecylerview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter(
    var personList: ArrayList<Person>,
    var context: Context,
    val listener: MyItemClick
) :
    RecyclerView.Adapter<MyAdapter.PersonViewHolder>(), Filterable {
    val allpersonList: ArrayList<Person> = ArrayList()

    init {
        allpersonList.addAll(personList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        var view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.single_card_view, parent, false);
        return PersonViewHolder(view)
    }

    override fun getItemCount(): Int {
        return personList.size
    }
    fun addToAllPersonList(person:Person){
        allpersonList.add(person)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.nameText.text = personList.get(position).name
        holder.ageText.text = personList.get(position).age.toString()
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {
        var nameText: TextView = itemView.findViewById(R.id.name)
        var ageText: TextView = itemView.findViewById(R.id.age)

        init {
            nameText.setOnClickListener(this)
            itemView.setOnLongClickListener(this)

        }

        override fun onClick(v: View?) {
            listener.onClicked(nameText.text.toString())
        }

        override fun onLongClick(v: View?): Boolean {
            Log.d("mytag", "longClicked")
            //personList.removeAt(adapterPosition)
            //notifyItemRemoved(adapterPosition)
            return true
        }

    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition..toPosition - 1) {
                personList.set(i, personList.set(i + 1, personList.get(i)));

            }
        } else {
            for (i in fromPosition..toPosition + 1) {
                personList.set(i, personList.set(i - 1, personList.get(i)));
            }

        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun getFilter(): Filter {
        return filter
    }

    private var filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Person> = ArrayList()
            if (constraint.toString().isEmpty()) {
                filteredList.addAll(allpersonList)
                Log.d("mytag all", allpersonList.size.toString())
                Log.d("mytag some", personList.size.toString())
            } else {
                for (i in 0 until allpersonList.size) {
                    if (allpersonList.get(i).name.toLowerCase()
                            .contains(constraint.toString().toLowerCase())
                    ) {
                        filteredList.add(
                            allpersonList.get(i))
                    }
                }
                Log.d("mytag all", allpersonList.size.toString())
                Log.d("mytag some", personList.size.toString())
            }
            var filterResult: FilterResults = FilterResults()
            filterResult.values = filteredList
            return filterResult
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            personList.clear()
            results?.values?.let {
                personList.addAll(it as Collection<Person>)

            }
            notifyDataSetChanged()
        }

    }


}

interface MyItemClick {
    fun onClicked(name: String);
}