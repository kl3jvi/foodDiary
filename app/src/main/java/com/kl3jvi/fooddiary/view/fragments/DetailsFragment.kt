package com.kl3jvi.fooddiary.view.fragments

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.chip.Chip
import com.kl3jvi.fooddiary.R
import com.kl3jvi.fooddiary.databinding.FragmentDetailsBinding
import com.kl3jvi.fooddiary.utils.Constants
import com.kl3jvi.fooddiary.utils.Constants.getChipColor
import java.io.IOException

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailsFragmentArgs by navArgs()

        args.let { arg ->
            try {
                binding.textView.text =
                    resources.getString(R.string.fruits_added_for_date, arg.entryDetails.date)
                arg.entryDetails.fruitList.forEach {
                    val chip = Chip(requireContext())
                    chip.text = "${it.amount} x ${it.fruitType}"
                    chip.chipBackgroundColor = getChipColor(it.fruitType)
                    chip.setTextColor(Color.WHITE)
                    Glide.with(requireActivity())
                        .load(Constants.BASE_URL + "images/${it.fruitType}.png")
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
                    binding.chipDetailsGroup.addView(chip)

                }


            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

}