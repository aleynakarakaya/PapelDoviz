package com.example.papeldoviz.fragment

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.papeldoviz.R
import com.example.papeldoviz.`interface`.ApiInterface
import com.example.papeldoviz.databinding.FragmentDetailBinding
import com.example.papeldoviz.servis.MyListChartList
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.ZoneOffset


class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var binding: FragmentDetailBinding? = null
    private var coinId: String? = null
    private var coinLogoUrl: String? = null
    private var coinName: String? = null
    private var coinFiatCurrency: String? = null

    private var lineList = mutableListOf<Entry>()
    lateinit var lineDataSet: LineDataSet
    lateinit var lineData: LineData


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            this.coinId = it.getString(BUNDLE_COIN_ID)
            this.coinLogoUrl = it.getString(BUNDLE_COIN_LOGO)
            this.coinName = it.getString(BUNDLE_COIN_NAME)
            this.coinFiatCurrency = it.getString(BUNDLE_COIN_FIAT_CURRENCY)
        }
        getMyLineChart()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMyLineChart() {
        val retrofitBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(CustomListFragment.BASE_URL)
                .build()
                .create(ApiInterface::class.java)

        var dateTime = LocalDateTime.now()
        var newwDate = dateTime.minusDays(30)
        var newDate = newwDate.atOffset(ZoneOffset.UTC)

        val retrofitData = retrofitBuilder.getLineChart(coinId!!, newDate.toString())
        retrofitData.enqueue(object : Callback<List<MyListChartList>> {
            override fun onResponse(call: Call<List<MyListChartList>>,
                                    response: Response<List<MyListChartList>>) {

                binding?.relativeLayout?.visibility = View.VISIBLE

                lineList.clear()
                var index: Float = 0f


                //burda geliştirmelerin değişmesi gerekecek.
                response.body()?.get(0)?.prices?.forEach {
                    lineList.add(Entry(
                            index,
                            it.toFloat()
                    ))
                    index++
                }





                lineDataSet = LineDataSet(lineList, "Count")
                lineData = LineData(lineDataSet)
                lineChart.data = lineData
                lineDataSet.valueTextColor = Color.BLUE
                lineDataSet.valueTextSize = 13f
                lineDataSet.setDrawFilled(true)
                lineChart.invalidate()



            }

            override fun onFailure(call: Call<List<MyListChartList>>, t: Throwable) {
                lineChart.visibility = View.GONE
            }
        })
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        //container?.removeAllViews()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailBinding.bind(view)

        Glide.with(requireContext()).load(this.coinLogoUrl).placeholder(R.drawable.ic_empty_state).into(binding!!.coinLogo2)
        binding!!.coinName2.text = coinName
        binding!!.coinFiatCurrency2.text = coinFiatCurrency
    }
    companion object {
        private const val BUNDLE_COIN_ID = "BUNDLE_COIN_ID"
        private const val BUNDLE_COIN_LOGO = "BUNDLE_COIN_LOGO"
        private const val BUNDLE_COIN_NAME = "BUNDLE_COIN_NAME"
        private const val BUNDLE_COIN_FIAT_CURRENCY = "BUNDLE_COIN_FIAT_CURRENCY"


        fun newInstance(coinId: String?, coinLogo: String?, coinName: String?, coinFiatCurrency: String?) = DetailFragment().apply {
            arguments = Bundle().apply {
                coinId?.let { putString(BUNDLE_COIN_ID, it) }
                coinLogo?.let { putString(BUNDLE_COIN_LOGO, it) }
                coinName?.let { putString(BUNDLE_COIN_NAME, it) }
                coinFiatCurrency?.let { putString(BUNDLE_COIN_FIAT_CURRENCY, it) }

            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}