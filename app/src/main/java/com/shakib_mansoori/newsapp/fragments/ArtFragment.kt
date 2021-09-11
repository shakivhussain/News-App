package com.shakib_mansoori.newsapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.shakib_mansoori.newsapp.Listener.Listener
import com.shakib_mansoori.newsapp.Model.Articles
import com.shakib_mansoori.newsapp.activities.ViewModel
import com.shakib_mansoori.newsapp.activities.WebActivity
import com.shakib_mansoori.newsapp.adapter.Adapter
import com.shakib_mansoori.newsapp.databinding.FragmentArtBinding

class ArtFragment : Fragment(), Listener {

    private lateinit var binding: FragmentArtBinding
    private lateinit var viewModel: ViewModel
    private lateinit var articlesList: List<Articles>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentArtBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(ViewModel::class.java)


        val adapter = Adapter(this)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.itemAnimator = null

        viewModel.getArtNews().observe(viewLifecycleOwner) {
            if (it != null) {
                articlesList = it
                adapter.submitList(articlesList)
            }
        }

        return binding.root
    }

    override fun onItemClickListener(position: Int) {
        val intent = Intent(requireActivity().baseContext, WebActivity::class.java)
        intent.putExtra("url", articlesList[position].url)
        startActivity(intent)
    }

}