package com.example.task.view.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.task.data.model.ResponseApiItem
import com.example.task.data.network.ApiConfig
import com.example.task.databinding.FragmentHomeBinding
import com.example.task.view.adapter.CarsAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    private lateinit var carsAdapter: CarsAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.itemRecyclerView.layoutManager = layoutManager

        showLoading(true)
        getAllCars()
    }

    private fun getAllCars() {
        val client = ApiConfig.getApiService().getAllCars()
        client.enqueue(object : Callback<List<ResponseApiItem>> {
            override fun onResponse(
                call: Call<List<ResponseApiItem>>,
                response: Response<List<ResponseApiItem>>
            ) {
                val responseBody = response.body()
                val code = response.code()
                if (code == 200) {
                    if (responseBody != null) {
                        showLoading(false)
                        showList(responseBody)
                    } else {
                        showLoading(true)
                    }
                } else {
                    Log.e("Failure", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ResponseApiItem>>, t: Throwable) {
                Log.e("Failure", "onFailure: ${t.message}")
            }

        })
    }

    private fun showList(data: List<ResponseApiItem>) {
        carsAdapter = CarsAdapter(data)
        carsAdapter.submitData(data)
        binding.itemRecyclerView.adapter = carsAdapter
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressCircular.visibility = View.VISIBLE
        } else {
            binding.progressCircular.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}