package com.example.newsapp.ui.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.newsapp.databinding.FragmentDetailBinding
import com.squareup.picasso.Picasso

/**
 * Show the details of a News
 */
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        args.news.run {
            Picasso.get().load(urlToImage).into(binding.imageview)
            binding.title.text = title
            binding.content.text = description
            binding.url.setOnClickListener {
                onClick(url)
            }
        }

        return binding.root
    }

    private fun onClick(url: String?) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

}