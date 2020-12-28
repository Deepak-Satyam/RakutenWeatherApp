package com.rakuten.weatherapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rakuten.weatherapp.R
import com.rakuten.weatherapp.data.model.Forecast
import kotlinx.android.synthetic.main.item_day.view.*

class ForecastListAdapter(
    private val movieList: ArrayList<Forecast>) : RecyclerView.Adapter<ForecastListAdapter.DataViewHolder>() {

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var index = 1
        fun bind(dayForeCast: Forecast) {
            itemView.day.text = dayForeCast.weekDay
            itemView.date.text = dayForeCast.date
            itemView.lowestTemperature.text = "${dayForeCast.minTemp}℃"
            itemView.highestTemperature.text = "${dayForeCast.maxTemp}℃"
            if(index%2==1)
                itemView.weatherIcon.setImageResource(R.drawable.weather_sun)
            else
                itemView.weatherIcon.setImageResource(R.drawable.weather_clear_day)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_day, parent,
                false
            )
        )

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(movieList[position])

    fun addData(list: List<Forecast>) {
        movieList.addAll(list)
    }

}