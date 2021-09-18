package com.kl3jvi.fooddiary.view.adapters

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.chip.Chip
import com.kl3jvi.fooddiary.R
import com.kl3jvi.fooddiary.databinding.ItemEntryBinding
import com.kl3jvi.fooddiary.model.entities.entries.Entries
import com.kl3jvi.fooddiary.model.entities.local.EntryTransfer
import com.kl3jvi.fooddiary.utils.Constants
import com.kl3jvi.fooddiary.view.activities.AddEntryActivity
import com.kl3jvi.fooddiary.view.fragments.MyDiaryFragment


class CustomEntryAdapter(private val fragment: Fragment) :
    RecyclerView.Adapter<CustomEntryAdapter.ViewHolder>() {

    private var entries: Entries = Entries()


    inner class ViewHolder(view: ItemEntryBinding) : RecyclerView.ViewHolder(view.root) {
        val date = view.entryDate
        val chipGroup = view.chipGroup
        val vitamins = view.vitaminNumber
        val fruits = view.fruitNumber
        val moreBUtton = view.moreButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemEntryBinding =
            ItemEntryBinding.inflate(LayoutInflater.from(fragment.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val entry = entries[position]
        holder.date.text = entry.date
        var sumOfFruits = 0
        val sumOfVitamins = 0
        holder.chipGroup.removeAllViews()
        entry.fruitList.forEach { fruit ->
            sumOfFruits += fruit.amount
            val chip = Chip(fragment.requireContext())

            chip.text = "${fruit.amount} x ${fruit.fruitType}"
            chip.chipBackgroundColor = getChipColor(fruit.fruitType)
            chip.setTextColor(Color.WHITE)
            Glide.with(fragment)
                .load(Constants.BASE_URL + "images/${fruit.fruitType}.png")
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(object : CustomTarget<Drawable>(30, 30) {
                    override fun onResourceReady(
                        resource: Drawable,
                        transition: Transition<in Drawable>?
                    ) {
                        chip.chipIcon = resource
                    }

                    override fun onLoadCleared(placeholder: Drawable?) {
                        chip.chipIcon = placeholder
                    }
                })
            holder.chipGroup.addView(chip)
        }
        holder.fruits.text = if (sumOfFruits > 1) "$sumOfFruits Fruits" else "$sumOfFruits Fruit"


        holder.moreBUtton.setOnClickListener {
            val popupMenu = PopupMenu(fragment.context, holder.moreBUtton)
            popupMenu.menuInflater.inflate(R.menu.popup_menu, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener {
                if (it.itemId == R.id.action_edit_entry) {
                    val intent =
                        Intent(fragment.requireActivity(), AddEntryActivity::class.java)
                    intent.putExtra(
                        Constants.EXTRA_DETAILS,
                        EntryTransfer(entry.fruitList, entry.entryId)
                    )
                    fragment.requireActivity().startActivity(intent)
                } else if (it.itemId == R.id.action_delete_entry) {
                    if (fragment is MyDiaryFragment) {
                        try {
                            fragment.deleteEntry(entryId = entry.entryId)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
                true
            }
            popupMenu.show()
        }


    }

    override fun getItemCount() = entries.size

    fun entriesList(list: Entries) {
        entries = list
        notifyDataSetChanged()
    }

    private fun getChipColor(fruitType: String): ColorStateList {

        when (fruitType) {
            "apple" -> {
                return ColorStateList.valueOf(Color.argb(255, 102, 180, 71))
            }
            "banana" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 225, 53))
            }
            "cherry" -> {
                return ColorStateList.valueOf(Color.argb(255, 210, 4, 45))
            }
            "lemon" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 207, 0))
            }
            "orange" -> {
                return ColorStateList.valueOf(Color.argb(255, 255, 165, 0))
            }
            "pear" -> {
                return ColorStateList.valueOf(Color.argb(255, 209, 226, 49))
            }
            "pineapple" -> {
                return ColorStateList.valueOf(Color.argb(255, 86, 60, 13))
            }
        }
        return ColorStateList.valueOf(Color.argb(255, 0, 144, 193))
    }
}

