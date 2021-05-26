package com.example.afrp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.afrp.databinding.FragmentWebBinding

/**
 * A simple [Fragment] subclass.
 * Use the [WebFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WebFragment : Fragment() {
//    private lateinit var communicator: FragmentCommunicator
    var url:String?=""
    private var _binding: FragmentWebBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding= FragmentWebBinding.inflate(inflater, container, false)

        url = arguments?.getString("url")

        Log.i("Web Fragment", "URL: $url" )
        url.let{
            if (it != null) {

                binding.webView.loadUrl(it)
            }else{
                Toast.makeText(context, "Null link", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }


//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding
//    }
}