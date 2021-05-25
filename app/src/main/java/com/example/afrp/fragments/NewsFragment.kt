package com.example.afrp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.afrp.R
import com.example.afrp.adapter.ItemAdapter
import com.example.afrp.data.Datasource
import com.example.afrp.databinding.FragmentNewsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment() {
    private var _binding: FragmentNewsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)


        return binding.root

//        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // initalize recyler view - where all articles are displayed
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)

        // start a thread with scope of "main" so it has context to update UI
        GlobalScope.launch(Dispatchers.Main) {
            // but start an IO thread so it can grab and return data
            // Main threads don't like fetching data
            val myDataSet = withContext(Dispatchers.IO) {
                Datasource().loadNews()
            }

            // update UI in Main thread
            recyclerView.adapter = context?.let { ItemAdapter(it, myDataSet) }
        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}