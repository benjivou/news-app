package com.example.newsapp.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.newsapp.data.model.News
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.adapter.NewsAdapter
import com.example.newsapp.ui.adapter.RecyclerItemClickListener
import com.example.newsapp.usecases.viewmodel.NewsViewModel


class Home : Fragment() {
    val viewModel: NewsViewModel by viewModels()
    private val adapter = NewsAdapter()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.recyclerview.adapter = adapter

        viewModel.news.observe(this) { news ->
            if (news?.isNotEmpty() == true) {
                binding.progressDialog.visibility = View.INVISIBLE
                adapter.setNews(news as List<News>)
            } else {
                binding.progressDialog.visibility = View.VISIBLE
            }
        }
        viewModel.errors.observe(this) {
            val toast = Toast.makeText(this.context, it, Toast.LENGTH_LONG)
            toast.show()
        }
        binding.recyclerview.addOnItemTouchListener(
            RecyclerItemClickListener(
                context,
                binding.recyclerview,
                object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View?, position: Int) {
                        val action =
                            HomeDirections.actionHome2ToDetailFragment(
                                viewModel.news.value!![position]!!
                            )

                        binding.root.findNavController().navigate(action)
                    }

                    override fun onLongItemClick(view: View?, position: Int) {
                        // DO nothing
                    }
                })
        )
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}