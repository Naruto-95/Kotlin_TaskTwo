package com.example.kotlin_tasktwo.room.romm



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlin_tasktwo.Repository.Weather
import com.example.kotlin_tasktwo.databinding.RecyclerWeatherListFragmentBinding




class WeatherListAdapter(
    private val onItemListClickListener: OnItemListClickListiner,
    private var data: List<Weather> = listOf()) : RecyclerView.Adapter<WeatherListAdapter.CityHolder>(){

    fun setData(dataNew: List<Weather>){
        this.data = dataNew
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
val binding = RecyclerWeatherListFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CityHolder(binding.root)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {

        holder.bind(data.get(position))

    }

    override fun getItemCount(): Int {
   return data.size
    }

   inner class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(weather: Weather) {
            RecyclerWeatherListFragmentBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                root.setOnClickListener{
                    onItemListClickListener.onitemClik(weather)
                }
            }
        }

   }

    }





