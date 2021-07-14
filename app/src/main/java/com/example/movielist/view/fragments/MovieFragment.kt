package com.example.movielist.view.fragments


import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movielist.application.MovieApplication
import com.example.movielist.databinding.FragmentMovieBinding
import com.example.movielist.model.entities.Movie
import com.example.movielist.model.entities.TheatreMovies
import com.example.movielist.model.network.Common
import com.example.movielist.model.network.RetrofitServices
import com.example.movielist.utils.Constants.API_KEY_VALUE
import com.example.movielist.utils.Constants.LOG_TAG
import com.example.movielist.view.adapter.MovieAdapter
import com.example.movielist.viewmodel.MovieViewModel
import com.example.movielist.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.item_layout.*
import kotlinx.android.synthetic.main.item_layout.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieFragment : Fragment() {
    lateinit var mService: RetrofitServices
    private lateinit var mBinding: FragmentMovieBinding

    private val mMovieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory((requireActivity().application as MovieApplication).repository)
    }

    private lateinit var mMovieAdapter: MovieAdapter
    private lateinit var mCustomListDialog: Dialog
    var movieList : MutableList<Movie>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        Log.d(LOG_TAG, "MovieFragment onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(LOG_TAG, "MovieFragment onCreateView")

        mBinding = FragmentMovieBinding.inflate(inflater, container, false)
        Log.d(LOG_TAG, "MovieFragment onCreateView mBinding = "+mBinding)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(LOG_TAG, "MovieFragment onViewCreated")


        mBinding.rvMovieList.layoutManager = LinearLayoutManager(requireActivity())
        mMovieAdapter = MovieAdapter(this@MovieFragment)

        mBinding.rvMovieList.adapter = mMovieAdapter

        mMovieViewModel.allMovieList.observe(viewLifecycleOwner) {
            movie ->
            movie.let {
                for (item in it) {
                    Log.i(LOG_TAG, "item.id = "+item.id+", item.title = "+item.title)
                }

                if (it.isNotEmpty()) {
                    mBinding.rvMovieList.visibility = View.VISIBLE
                    mBinding.tvNoMovieList.visibility = View.GONE
                    mMovieAdapter.movieList(it)
                } else {
                    getAllMovieList()
                    mBinding.rvMovieList.visibility = View.GONE
                    mBinding.tvNoMovieList.visibility = View.VISIBLE
                    movieList?.let { it1 -> mMovieViewModel.insert(it1) }
                }
        }
        }

    }

    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "MovieFragment onResume")
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(LOG_TAG, "MovieFragment onDestroyView")
    }

    private fun getAllMovieList() {
        Log.d(LOG_TAG, "getAllMovieList")
        mService = Common.retrofitServices!!
        mService.getMovieList(API_KEY_VALUE).enqueue(object : Callback<TheatreMovies> {
            override fun onResponse(call: Call<TheatreMovies>, response: Response<TheatreMovies>) {
                Log.i(LOG_TAG, "Response: $response")
                movieList = response.body()?.results
            }

            override fun onFailure(call: Call<TheatreMovies>, t: Throwable) {
                Log.e(LOG_TAG, "Throwable: $t")
            }
        })
    }

    fun updateMovie() {
        Log.d(LOG_TAG, "MovieFragment updateMovie")
    }
}