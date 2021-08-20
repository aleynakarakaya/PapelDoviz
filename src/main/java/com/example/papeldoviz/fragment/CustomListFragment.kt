package com.example.papeldoviz.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.papeldoviz.R
import com.example.papeldoviz.`interface`.ApiInterface
import com.example.papeldoviz.adapter.MyAdapter
import com.example.papeldoviz.servis.MyData
import com.example.papeldoviz.servis.MyDataItem
import kotlinx.android.synthetic.main.fragment_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//f878bb1bd8cf652e0f4b2f50a69872b7b352dd18
//base url "https://api.nomics.com/v1/
class CustomListFragment : Fragment() {

    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        getMyData()

    }

   private fun getMyData() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)


        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<List<MyDataItem>?> {
            override fun onResponse(call: Call<List<MyDataItem>?>,
                                    response: Response<List<MyDataItem>?>) {




                val responseBody = response.body()!!
                //userList = responseBody
                //tempArrayList.addAll(liste)
                recyclerview_users.setHasFixedSize(true)
                linearLayoutManager = LinearLayoutManager(requireContext())
                recyclerview_users.layoutManager = linearLayoutManager
                myAdapter = MyAdapter(activity!!.baseContext, responseBody!!)
                myAdapter.notifyDataSetChanged()
                recyclerview_users.adapter = myAdapter


                //recyclerview_users.adapter!!.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<MyDataItem>?>, t: Throwable) {
                d("ListFragment", "onFailure: "+t.message)
            }
        })




   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    companion object {
        const val BASE_URL = "https://api.nomics.com/v1/"
    }


}