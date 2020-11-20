package com.eric.firebaseexample.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.eric.firebaseexample.data.Article
import com.eric.firebaseexample.databinding.FragmentHomeBinding
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


class HomeFragment : Fragment(){

    lateinit var adapter: HomeAdapter
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val db = FirebaseFirestore.getInstance()
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        val application = requireNotNull(activity).application
        val viewModelFactory = HomeViewModelFactory(db, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(HomeViewModel::class.java)


        val adapter =  HomeAdapter(HomeAdapter.OnClickListener {})

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.homeRecyclerView.adapter = adapter
        adapter.submitList(viewModel.articles.value)



        val getart = db.collection("articles").orderBy("createdTime").get().addOnSuccessListener {
            it.let {
                it.forEach {
                    Log.i("data form from fb : ", it.toString())
                }
            }
        }

        binding.publishBtn.setOnClickListener {
            this.findNavController()
                .navigate(HomeFragmentDirections.actionHomeFragmentToPublisherFragment())
        }

        binding.homeRecyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(-1)) {
                    viewModel.getArticle()
                }
            }
        })
        //testing

        //testing

        fun addpost() {
            val article = hashMapOf<String, Any>(
                "author" to hashMapOf(
                    "email" to ".com",
                    "id" to "eric1141",
                    "name" to "eric"
                ),
                "title" to "考場安靜",
                "content" to "窒息了",
                "createdTime" to Calendar.getInstance().timeInMillis,
                "id" to "ox",
                "category" to "SchoolLife"
            )

            db.collection("articles").add(article)
        }


        binding.homeTitle.setOnClickListener {
            viewModel.addpost()
            Log.i("data form from fb : ", "????")
        }
            ////
//            addpost()


            ////
//            db.collection("articles").get().addOnSuccessListener {
//                it.let {
////                    it.forEach {
//                        val x = it.toObjects(Article::class.java)
//                        adapter.submitList(x)
//                        Log.i("data form from fb : ", x.toString())
////                    }
//                }
//            }
//            Log.i("data form from fb : ", getart)

////        auto update with new post detected
//        db.collection("articles").addSnapshotListener{ value, error ->
//            viewModel.getArticle()
//        }


        viewModel.getData.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            if (it == 1) {
                adapter.submitList(viewModel.articles.value)
                adapter.notifyDataSetChanged()
                viewModel.getData.value = 0
            }
        })
        return binding.root
    }


    override fun onResume() {
        super.onResume()
    }
}