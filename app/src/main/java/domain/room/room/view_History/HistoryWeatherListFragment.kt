package domain.room.room.view_History

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_tasktwo.MyApp
import com.example.kotlin_tasktwo.databinding.HistoryWeatherListFragmentBinding
import com.example.kotlin_tasktwo.viewmodel.AppState
import com.google.android.material.snackbar.Snackbar
import domain.convert.convertHistoryEntityToWeather
import domain.room.room.historyViewmodel.HistoryViewModel
import android.view.View as View


class HistoryWeatherListFragment : Fragment() {

    lateinit var binding: HistoryWeatherListFragmentBinding

    private val adapter = HistoryWeatherListAdapter()
    private var isRussin = true

    private val viewModel: HistoryViewModel by lazy {
        ViewModelProvider(this).get(HistoryViewModel::class.java)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HistoryWeatherListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    private var isDataSetWorld: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.RecyclerView.adapter = adapter

        val observer = { data: AppState -> renderData(data) }

        viewModel.getLiveData().observe(viewLifecycleOwner, observer)
viewModel.getAll()


    }








    private fun renderData(data: AppState) = when (data) {
        is AppState.Success -> {
            adapter.setData(data.weatherList)
        }
        AppState.Loading -> {
        }
        is AppState.Error -> {
            Snackbar.make(binding.root, "Не получилось ${data.error}", Snackbar.LENGTH_LONG)
                .show()

        }

    }


    companion object {
        fun newInstance() = HistoryWeatherListFragment()
    }



}







