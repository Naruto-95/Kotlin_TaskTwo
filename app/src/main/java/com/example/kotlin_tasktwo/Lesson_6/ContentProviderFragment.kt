package com.example.kotlin_tasktwo.Lesson_6
import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.databinding.TheardFragmentBinding
import java.lang.Thread.sleep


class ContentProviderFragment : Fragment() {

   private var _binding: TheardFragmentBinding? = null
    private val binding: TheardFragmentBinding
    get(){
        return _binding!!
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = TheardFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super. onViewCreated(view,savedInstanceState)



        }










    companion object {
        fun newInstance() = ContentProviderFragment()
    }


}









