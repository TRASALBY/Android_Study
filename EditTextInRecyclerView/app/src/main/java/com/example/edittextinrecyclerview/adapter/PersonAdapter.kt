package com.example.edittextinrecyclerview.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.edittextinrecyclerview.R
import com.example.edittextinrecyclerview.databinding.ItemPersonBinding
import com.example.edittextinrecyclerview.model.Person


class PersonAdapter(private val itemClick : (Int) -> Unit) : ListAdapter<Person, PersonAdapter.PersonViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(itemClick, parent)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PersonViewHolder(itemClick: (Int) -> Unit, parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_person,parent,false)
    ) {
        private val binding = ItemPersonBinding.bind(itemView)

        init {
            binding.btnDeleteProfile.setOnClickListener {
                itemClick(layoutPosition)
            }
        }
        fun bind(person: Person){
            binding.person = person
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Person>(){
            override fun areItemsTheSame(oldItem: Person, newItem: Person) =
                oldItem.name == newItem.name


            override fun areContentsTheSame(oldItem: Person, newItem: Person) =
                oldItem.hashCode() == newItem.hashCode()

        }
    }


}