package domain.room.room.view_History

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import com.example.kotlin_tasktwo.repository.Weather
import com.example.kotlin_tasktwo.databinding.HistoryRecyclerWeatherListFragmentBinding

class HistoryWeatherListAdapter (
    private var data: List<Weather> = listOf()
) :
    RecyclerView.Adapter<HistoryWeatherListAdapter.CityHolder>(){

    fun setData(dataNew: List<Weather>){
        this.data = dataNew
        notifyDataSetChanged()
    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val binding = HistoryRecyclerWeatherListFragmentBinding.inflate(LayoutInflater.from(parent.context),parent,false)
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
            HistoryRecyclerWeatherListFragmentBinding.bind(itemView).apply {
                tvCityName.text = weather.city.name
                temp.text = weather.temperature.toString()
                fils.text = weather.feelsLike.toString()
                    icon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")




            }
        }

    }

    fun ImageView.loadSvg(url: String) {
        val imageLoader = ImageLoader.Builder(this.context)
            .componentRegistry { add(SvgDecoder(this@loadSvg.context)) }
            .build()
        val imagerequest = coil.request.ImageRequest.Builder(this.context)
            .crossfade(true)
            .crossfade(500)
            .data(url)
            .target(this)
            .build()
        imageLoader.enqueue(imagerequest)
    }

}