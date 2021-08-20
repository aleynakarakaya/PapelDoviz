package com.example.papeldoviz.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.papeldoviz.R
import com.example.papeldoviz.adapter.MyAdapter
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {

    // private var binding: FragmentDetailBinding? = null
    //private var coinId: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //userId2.text = getText()

        /*arguments?.let{
           this.coinId = it.getString(BUNDLE_COIN_ID)
            println(coinId)
        }*/

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    /*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val localBinding = FragmentDetailBinding.bind(view)
        binding = localBinding
        localBinding.userId2.text = getString()
    }

    companion object{
        private const val BUNDLE_COIN_ID = "BUNDLE_COIN_ID"
        fun newInstance(coinId: String?) = DetailFragment().apply {
            arguments = Bundle().apply{
                coinId?.let {putString(BUNDLE_COIN_ID, it)}
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }*/


}