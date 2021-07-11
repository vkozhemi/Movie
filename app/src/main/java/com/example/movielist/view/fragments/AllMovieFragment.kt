package com.example.movielist.view.fragments

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import com.example.movielist.databinding.FragmentAllMovieBinding
import com.example.movielist.viewmodel.AllMovieViewModel
import com.example.movielist.application.MovieApplication
import com.example.movielist.viewmodel.MovieViewModelFactory

class AllMovieFragment : Fragment() {

    private lateinit var mBinding: FragmentAllMovieBinding

    private val mMovieViewModel: AllMovieViewModel by viewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).repository)
    }

    private lateinit var homeViewModel: AllMovieViewModel
    private var _binding: FragmentAllMovieBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(AllMovieViewModel::class.java)

        _binding = FragmentAllMovieBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}