package com.example.rideskotlin

import DataModel
import MyAPICall
import VehicleDetailsFragment
import VehiclesAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.rideskotlin.databinding.ActivityMainBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {


    lateinit var val1: String
    var val2 = 0
    lateinit var vehiclesAdapter: VehiclesAdapter
    lateinit var rv_vehicle: RecyclerView
    lateinit var vehicle_details_fragment: FrameLayout
    lateinit var progressBar: ProgressBar
    lateinit var toolbar_main_activity: Toolbar
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    var model: List<DataModel> = ArrayList<DataModel>()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rv_vehicle =findViewById(R.id.rv_vehicle)
        progressBar = findViewById(R.id.progressBar)
        swipeRefreshLayout = findViewById(R.id.sr_vehicles)
        toolbar_main_activity = findViewById(R.id.toolbar_main_activity)
        vehicle_details_fragment = findViewById(R.id.vehicle_details_fragment)
        setSupportActionBar(toolbar_main_activity)
        supportActionBar!!.title = "Vehicle List"

        rv_vehicle.addItemDecoration(DividerItemDecoration(this,LinearLayoutManager.VERTICAL))

        binding.btnVehicle!!.setOnClickListener {
            val1 = binding.et1.text.toString()
            binding.et1!!.addTextChangedListener(textWatcher)
            if (val1!!.isEmpty() == false) {
                val2 = val1.toInt()
                showProgress(progressBar)
                ResponseDetails()
                hideSoftKeyboard()
            }
        }

        swipeRefreshLayout!!.setOnRefreshListener {
            showProgress(progressBar!!)
            ResponseDetails()
            swipeRefreshLayout.isRefreshing = false
            vehiclesAdapter!!.notifyDataSetChanged()
        }

    }

    private fun ResponseDetails() {
        val retrofit = Retrofit.Builder().baseUrl("https://random-data-api.com/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitApiClient: MyAPICall = retrofit.create(MyAPICall::class.java)
        val call: Call<List<DataModel>> = retrofitApiClient.responseModel(val2)
        call.enqueue(object : Callback<List<DataModel>?> {
            override fun onResponse(
                call: Call<List<DataModel>?>,
                response: Response<List<DataModel>?>
            ) {

                try {
                    if (val2 in 1..100) {
                        Log.e("Success", "" + Gson().toJson(response.body()))
                        model = response.body()!!
                        hideProgress(progressBar)

                        rv_vehicle.layoutManager = LinearLayoutManager(this@MainActivity,LinearLayoutManager.VERTICAL,false)
                        rv_vehicle.setHasFixedSize(true)


                        vehiclesAdapter =
                            VehiclesAdapter(this@MainActivity, model)
                        rv_vehicle!!.adapter = vehiclesAdapter
                        Collections.sort(model, object : Comparator<DataModel?> {

                            override fun compare(p0: DataModel?, p1: DataModel?): Int {
                                return p0?.vin!!.compareTo(p1?.vin!!)
                            }
                        })

                        vehiclesAdapter!!.notifyDataSetChanged()

                        vehiclesAdapter.setOnCLickListener(object: VehiclesAdapter.ItemClickListener{
                            override fun onItemClick(dataModel: DataModel?, position: Int) {

                                vehicle_details_fragment!!.visibility = View.VISIBLE
                                toolbar_main_activity!!.visibility = View.GONE
                                val vin: String? = model[position].vin
                                val make_and_model: String? = model[position].makeAndModel
                                val color: String? = model[position].color
                                val car_type: String? = model[position].carType
                                val kilometrage: Int? = model[position].kilometrage
                                val fragment: Fragment = VehicleDetailsFragment()
                                val bundle = Bundle()
                                bundle.putString("vin", vin)
                                bundle.putString("make_and_model", make_and_model)
                                bundle.putString("color", color)
                                bundle.putString("car_type", car_type)
                                bundle.putInt("kilometrage", kilometrage!!)
                                fragment.arguments = bundle
                                supportFragmentManager
                                    .beginTransaction()
                                    .replace(R.id.vehicle_details_fragment, fragment)
                                    .commit()
                            }
                        })
                    }
                    else
                    {
                        Toast.makeText(
                            applicationContext,
                            "Please Enter the number ranging from 1-100",
                            Toast.LENGTH_LONG
                        ).show()
                        hideProgress(progressBar)
                    }
                }

                catch (e: Exception)
                {
                    Log.e("Error", "" + e.message)
                }

            }

            override fun onFailure(call: Call<List<DataModel>?>, t: Throwable) {
                Toast.makeText(
                    applicationContext,
                    "Please Enter the number ranging from 1-100",
                    Toast.LENGTH_LONG
                ).show()
                hideProgress(progressBar)
                Log.e("Failure","" + t.message)

            }
        })

    }

    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    private fun showProgress(progressBar: ProgressBar) {
        if (progressBar.visibility == View.GONE) {
            progressBar.visibility = View.VISIBLE
        }
    }

    fun hideProgress(progressBar: ProgressBar) {
        if (progressBar.visibility == View.VISIBLE) {
            progressBar.visibility = View.GONE
        }
    }

    private var textWatcher: TextWatcher = object : TextWatcher {
        override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}
        override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
            val1 = binding.et1!!.text.toString()
            binding.btnVehicle!!.isEnabled = !val1.isEmpty()
        }

        override fun afterTextChanged(editable: Editable) {}
    }

}
