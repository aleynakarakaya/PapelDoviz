package com.example.papeldoviz.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.papeldoviz.R
import com.example.papeldoviz.`interface`.ApiInterface
import com.example.papeldoviz.databinding.FragmentDetailBinding
import com.example.papeldoviz.servis.MyDataItem
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null
    private var coinId: String? = null
    private var coinLogo: String? = null
    private var coinName: String? = null
    private var coinFiatCurrency: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let{
            this.coinId = it.getString(BUNDLE_COIN_ID)
            this.coinLogo = it.getString(BUNDLE_COIN_LOGO)
            this.coinName = it.getString(BUNDLE_COIN_NAME)
            this.coinFiatCurrency = it.getString(BUNDLE_COIN_FIAT_CURRENCY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        Glide.with(requireContext()).load(this.coinLogo).placeholder(R.drawable.ic_launcher_background).into(binding!!.coinLogo2)
        binding!!.coinName2.text = coinName
        binding!!.coinFiatCurrency2.text = coinFiatCurrency

    }

    companion object{
        private const val BUNDLE_COIN_ID = "BUNDLE_COIN_ID"
        private const val BUNDLE_COIN_LOGO = "BUNDLE_COIN_LOGO"
        private const val BUNDLE_COIN_NAME = "BUNDLE_COIN_NAME"
        private const val BUNDLE_COIN_FIAT_CURRENCY = "BUNDLE_COIN_FIAT_CURRENCY"

        fun newInstance(coinId: String?, coinLogo: String?, coinName: String?, coinFiatCurrency: String? ) = DetailFragment().apply {
            arguments = Bundle().apply{
                coinId?.let {putString(BUNDLE_COIN_ID, it)}
                coinLogo?.let { putString(BUNDLE_COIN_LOGO, it) }
                coinName?.let { putString(BUNDLE_COIN_NAME, it) }
                coinFiatCurrency?.let { putString(BUNDLE_COIN_FIAT_CURRENCY,it) }

            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }


}