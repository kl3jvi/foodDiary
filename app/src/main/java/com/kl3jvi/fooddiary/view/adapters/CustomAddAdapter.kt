package com.kl3jvi.fooddiary.view.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kl3jvi.fooddiary.databinding.ItemAddEntryBinding
import com.kl3jvi.fooddiary.model.entities.entries.FruitEntry
import com.kl3jvi.fooddiary.model.entities.fruit.FruitItem
import com.kl3jvi.fooddiary.utils.Constants
import com.kl3jvi.fooddiary.view.activities.AddEntryActivity

class CustomAddAdapter(private val activity: Activity) :
    RecyclerView.Adapter<CustomAddAdapter.ViewHolder>() {
    private var list: List<FruitItem> = listOf()

    inner class ViewHolder(view: ItemAddEntryBinding) : RecyclerView.ViewHolder(view.root) {
        val image = view.fruitImg
        val add = view.add
        val remove = view.remove
        val counter = view.counter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemAddEntryBinding =
            ItemAddEntryBinding.inflate(LayoutInflater.from(activity), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        var ctr = 0;
        holder.add.setOnClickListener {
            ctr++
            holder.counter.text = ctr.toString()
        }
        holder.remove.setOnClickListener {
            if (ctr > 0)
                ctr--
            holder.counter.text = ctr.toString()

        }

        Glide.with(activity)
            .load(Constants.BASE_URL + item.image)
            .into(holder.image)


    }

    override fun getItemCount() = list.size


    fun fruitList(listOfFr: List<FruitItem>) {
        list = listOfFr
        notifyDataSetChanged()
    }
}