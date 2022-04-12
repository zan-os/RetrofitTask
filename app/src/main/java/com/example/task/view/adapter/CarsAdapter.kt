package com.example.task.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.task.data.model.ResponseApiItem
import com.example.task.databinding.ItemContainerBinding
import com.example.task.view.ui.HomeFragmentDirections

class CarsAdapter(private val listView: List<ResponseApiItem>) :
    RecyclerView.Adapter<CarsAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<ResponseApiItem>() {
        override fun areItemsTheSame(
            oldItem: ResponseApiItem,
            newItem: ResponseApiItem
        ): Boolean = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: ResponseApiItem,
            newItem: ResponseApiItem
        ): Boolean = oldItem.hashCode() == newItem.hashCode()
    }

    private val differ = AsyncListDiffer(this, diffCallback)

    fun submitData(value: List<ResponseApiItem>?) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = ItemContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(bind)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            binding.nameTextView.text = listView[position].name
            binding.priceTextView.text = listView[position].price.toString()
            binding.itemCardView.setOnClickListener {
                val direction =
                    HomeFragmentDirections.actionHomeFragmentToDetailFragment(listView[position])
                it.findNavController().navigate(direction)
                Toast.makeText(it.context, "to detail fragment", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun getItemCount(): Int = listView.size


    class ViewHolder(val binding: ItemContainerBinding) : RecyclerView.ViewHolder(binding.root)

}