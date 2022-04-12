package com.example.task.view.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.task.R
import com.example.task.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadDetail()
    }

    private fun loadDetail() {
        val car = args.data
        val carName = car.name
        val carCategory = getString(R.string.car_category, car.category)
        val carPrice = getString(R.string.car_price, car.price.toString())
        val updatedAt = getString(R.string.updated_at, car.updatedAt)
        val createdAt = getString(R.string.created_at, car.createdAt)

        Glide.with(this)
            .load(car.image)
            .into(binding.carImageView)

        binding.nameTextView.text = carName
        binding.categoryTextView.text = carCategory
        binding.priceTextView.text = carPrice
        binding.createdTextView.text = createdAt
        binding.updatedTextView.text = updatedAt
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}