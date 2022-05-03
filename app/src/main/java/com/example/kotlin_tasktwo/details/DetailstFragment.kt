package com.example.kotlin_tasktwo.details


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import com.example.kotlin_tasktwo.Lesson_7.lesson7.viewmodelOkhttp_Retrofit.DetailstState
import com.example.kotlin_tasktwo.Lesson_7.lesson7.viewmodelOkhttp_Retrofit.DetailstViewModel
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.repository.Weather
import com.example.kotlin_tasktwo.databinding.DetailsFragmentBinding
import com.google.android.material.snackbar.Snackbar


//2:39

class DetailstFragment : Fragment() {

    private var _binding: DetailsFragmentBinding? = null
    private val binding: DetailsFragmentBinding
        get() {
            return _binding!!
        }
/* receiver
    //Создал на месте ресивер
    val receiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let { intent ->
                intent.getParcelableExtra<WeatherDTO>(KEY_WEATHER)?.let {
                    onResponse(it)
                }

            }

        }

    }
*/

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        // LocalBroadcastManager.getInstance(requireContext()).unregisterReceiver(receiver)
    }

    private val viewModel: DetailstViewModel by lazy {
        ViewModelProvider(this).get(DetailstViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    //lateinit var currentCityName: String
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.getliveData().observe(
            viewLifecycleOwner
        ) { t -> renderData(t) }
        //val observer = { appStateError: AppStateError -> Errorr(appStateError) }
        //  OnErrorListener(observer)
        arguments?.getParcelable<Weather>(BUNDLE_WEATHER)?.let {
            //currentCityName = it.city.name
            viewModel.getWeather(it.city)
/*  LoaderWeather
 LoaderWeather(this@DetailstFragment,this@DetailstFragment )
.LoadWeather(it.city.lat, it.city.lon)*/
            /*registerReceiver
                  LocalBroadcastManager.getInstance(requireContext())
                      .registerReceiver(receiver, IntentFilter(KEY_WEATHER_WAVE))*/

/* Service
// requireActivity().startService(Intent(requireContext(),DetalisService::class.java).apply {
//  putExtra(KAY_BUN_LAT,it.city.lat)
//  putExtra(KAY_BUN_LON, it.city.lon )
//   })
*/


        }
    }
/* Okhttp getWeathe
    fun getWeather(lat: Double, lon: Double) {
        binding.loadingLayout.visibility = View.VISIBLE
        val client = OkHttpClient()
        val buider = Request.Builder()
        buider.addHeader(KEY_YANDEX_API, BuildConfig.WEATHER_API_KEY)
        buider.url("$KEY_YANDEX_DOMEN${KEY_YANDEX}lat=$lat&lon=$lon")
        val rec = buider.build()
        val callback: Callback = object : Callback {
            override fun onFailure(call: Call, e: IOException) {
//getWeather(it.city.lat, it.city.lon)
            }


            override fun onResponse(call: Call, response: Response) {

                if (response.isSuccessful) {
                    val weatherDTO: WeatherDTO =
                        Gson().fromJson(response.body()!!.string(), WeatherDTO::class.java)
                    requireActivity().runOnUiThread {//Главный поток
                        renderData(weatherDTO)
                    }
                }
            }
        }
        val call = client.newCall(rec)
/*  Синхронность
val response = call.execute()
Thread{
if (response.isSuccessful){
val weatherDTO: WeatherDTO = Gson().fromJson(response.body()!!.string(), WeatherDTO::class.java)
requireActivity().runOnUiThread{
  renderData(weatherDTO)
}
}
}*/
        call.enqueue(callback)//аинсинхронность
        binding.loadingLayout.visibility = View.GONE
    }

 */
//1:25


    @SuppressLint("ShowToast")
    private fun renderData(detailstState: DetailstState) = when (detailstState) {
        is DetailstState.Error -> {
            with(binding) {
                loadingLayout.visibility = View.GONE
                Snackbar.make(mainView, "wwww", Snackbar.LENGTH_LONG).show()

                // onFail(DetailstState.Error( IllegalAccessException()))
                // mainView.showSnackBar("yt",5000)
                //DetailstState.Error(Throwable(ERROR_SERVER)).toString()

            }


        }
        DetailstState.Loading -> {
            binding.mainView.visibility = View.VISIBLE
            binding.loadingLayout.visibility = View.GONE

        }
        is DetailstState.Success -> {
            val weather = detailstState.weather
            with(binding) {
                cityName.text = weather.city.name
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    weather.city.lat.toString(),
                    weather.city.lon.toString(),
                    mainView.showSnackBar(
                        " работает ",
                        2000
                    )
                )

                temperatureValue.text = weather.temperature.toString()
                feelsLikeValue.text = weather.feelsLike.toString()

/* Glide.with(requireActivity())
 .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
 .into(Icon)*/


                IconCity.load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                CloudIcon.loadSvg("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.icon}.svg")

                /*Picasso.get()?.load("https://yastatic.net/weather/i/icons/blueye/color/svg/${weather.city}.svg")

                    ?.into(CloudIcon)*/

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

    // extension-функция
    private fun View.showSnackBar(
        text: String,

        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).show()
    }

    companion object {
        const val BUNDLE_WEATHER = "weather"
        fun newInstance(bundle: Bundle): DetailstFragment {
            val fragment = DetailstFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

// override fun onResponse(weather: WeatherDTO) {
//      renderData(weather)
//   }


    fun onFail(detailstState: DetailstState) = when (detailstState) {

        is DetailstState.Error -> {
            binding.loadingLayout.visibility = View.GONE
            Snackbar.make(binding.root, " Сервер не отвечает", Snackbar.LENGTH_LONG).show()

        }

        /* is AppStateError.ErrorCl -> {
             Snackbar.make(binding.root, " Что-то случилось ", Snackbar.LENGTH_LONG).show()
             binding.loadingLayout.visibility = View.GONE

         }*/
        else -> {

        }
    }


//override fun onError(appStateError: AppStateError) {
//   Errorr(appStateError)
//  }
}























