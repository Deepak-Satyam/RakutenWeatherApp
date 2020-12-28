package com.rakuten.weatherapp.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakuten.weatherapp.R
import com.rakuten.weatherapp.data.model.Forecast
import com.rakuten.weatherapp.data.model.Weather
import com.rakuten.weatherapp.ui.adapter.ForecastListAdapter
import com.rakuten.weatherapp.ui.model.DetailViewModel
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : Fragment() {
    var location: TextView? = null
    var region: TextView? = null
    //var coord: TextView? = null
    var icon: ImageView? = null
    var temper: TextView? = null
    var describe: TextView? = null
    var foreCastdayList: RecyclerView? = null
    var foreCastList = ArrayList<Forecast>()
    //Dummy Forecast Data
    val foreCast1 = Forecast("Sunday","27.12.20",11,6,1)
    val foreCast2 = Forecast("Monday","28.12.20",20,14,1)
    val foreCast3 = Forecast("Tuesday","29.12.20",14,6,1)
    val foreCast4 = Forecast("Wednesday","30.12.20",21,12,1)
    val foreCast5 = Forecast("Thursday","31.12.20",22,10,1)
    val foreCast6 = Forecast("Friday","01.01.21",15,8,1)
    val foreCast7 = Forecast("Saturday","02.01.21",16,7,1)


    private val detailViewModel: DetailViewModel by viewModel()

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }


    private var weatherData: Weather? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val inputStr = arguments?.getString("detail_prop")
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        location = root.findViewById(R.id.title_location)
        region = root.findViewById(R.id.title_region)
       // coord = root.findViewById(R.id.title_coord)
        icon = root.findViewById(R.id.icon_image)
        temper = root.findViewById(R.id.title_temper)
        describe = root.findViewById(R.id.title_wtdescribe)
        detailViewModel.liveLocation().observe(this, locationObserver)
        detailViewModel.liveRegion().observe(this, regionObserver)
        //detailViewModel.liveCoord().observe(this, coordObserver)
        detailViewModel.liveImgUrl().observe(this, iconObserver)
        detailViewModel.liveTempText().observe(this, temperObserver)
        detailViewModel.liveWeatherDescribe().observe(this, describeObserver)
        weatherData?.let {
            detailViewModel.setWeatherData(it)
        } ?: run {
            detailViewModel.setWeatherData(arguments?.getSerializable("weather_detail") as Weather?)
        }
        foreCastList.add(foreCast1)
        foreCastList.add(foreCast2)
        foreCastList.add(foreCast3)
        foreCastList.add(foreCast4)
        foreCastList.add(foreCast5)
        foreCastList.add(foreCast6)
        foreCastList.add(foreCast7)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up list configurations
        dayList.run {
            val forCastAdapter = ForecastListAdapter(foreCastList)
            adapter = forCastAdapter
            layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
        }
    }

    private val locationObserver = Observer<String> { str ->
        location?.text = str
    }

    private val regionObserver = Observer<String> { str ->
        region?.text = str
    }

    /*private val coordObserver = Observer<String> { str ->
        coord?.text = str
    }*/

    private val temperObserver = Observer<String> { str ->
        temper?.text = str
    }

    private val describeObserver = Observer<String> { str ->
        describe?.text = str
    }

    private val iconObserver = Observer<String> { str ->
        str?.let {
            if (it.length > 0) {
                icon?.let { view ->
                    Glide.with(context).load(it).into(view)
                }
            }
        }
    }
}