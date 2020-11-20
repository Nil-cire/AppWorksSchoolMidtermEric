package com.eric.firebaseexample.publisher

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.eric.firebaseexample.databinding.FragmentHomeBinding
import com.eric.firebaseexample.databinding.FragmentPublisherBinding
import com.google.firebase.firestore.FirebaseFirestore

class PublisherFragment: Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPublisherBinding.inflate(inflater, container, false)
        val application = requireNotNull(activity).application
        val db = FirebaseFirestore.getInstance()

        val viewModelFactory = PublisherViewModelFactory(db, application)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(PublisherViewModel::class.java)

        binding.publishBtn.setOnClickListener {

            var title = binding.inputTitle.text.toString()
            var category = binding.inputCatagory.text.toString()
            var content = binding.inputContent.text.toString()

            viewModel.postArticle(".com","1141","eric", title, category, content)
        }

        viewModel.donepost.observe(viewLifecycleOwner, Observer {
            if (it == 1) {
                this.findNavController().navigate(PublisherFragmentDirections.actionPublisherFragmentToHomeFragment())
            }
        })

        return binding.root
    }
}