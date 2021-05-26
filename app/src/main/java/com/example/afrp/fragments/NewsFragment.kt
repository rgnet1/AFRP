package com.example.afrp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.afrp.FragmentCommunicator
import com.example.afrp.adapter.ItemAdapter
import com.example.afrp.data.Datasource
import com.example.afrp.databinding.FragmentNewsBinding
import kotlinx.coroutines.*

/**
 * A simple [Fragment] subclass.
 */
class NewsFragment : Fragment(){
    private var _binding: FragmentNewsBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private lateinit var communicator: FragmentCommunicator
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewsBinding.inflate(inflater, container, false)

        communicator = activity as FragmentCommunicator
        return binding.root

//        return inflater.inflate(R.layout.fragment_news, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val uiScope = CoroutineScope(Dispatchers.Main + Job())
        uiScope.launch {
            withContext(Dispatchers.IO){
                // Pull data
                val myDataSet = Datasource().loadNews("https://raw.githubusercontent.com/rgnet1/AFRP/master/json-files/news.json")

                // update UI
                withContext(Dispatchers.Main){
                    val recyclerView = binding.recyclerView
                    recyclerView.visibility = View.VISIBLE
                    recyclerView.adapter = context?.let { ItemAdapter(it, myDataSet, communicator) }
                }
            }
        }
        // start a thread with scope of "main" so it has context to update UI
//        GlobalScope.launch(Dispatchers.Main) {
//            // but start an IO thread so it can grab and return data
//            // Main threads don't like fetching data
//            val myDataSet = withContext(Dispatchers.IO) {
//                Datasource().loadNews("https://raw.githubusercontent.com/rgnet1/AFRP/master/json-files/news.json")
//            }
//            // initalize recyler view - where all articles are displayed
//            val recyclerView = binding.recyclerView
//            recyclerView.visibility = View.VISIBLE
////            val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
//            // update UI in Main thread
//            recyclerView.adapter = context?.let { ItemAdapter(it, myDataSet) }
//        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}