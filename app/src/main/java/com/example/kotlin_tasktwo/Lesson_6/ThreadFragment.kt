package com.example.kotlin_tasktwo.Lesson_6
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.databinding.TheardFragmentBinding
import com.example.kotlin_tasktwo.view.main.MainActivity
import java.lang.Thread.sleep


class ThreadFragment : Fragment() {
// занулил  binding пока что здесь
    //Потом и до проекта доберусь
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
        with(binding){
            BtnDZ.setOnClickListener {
                Thread{
                    val sek = editText.text.toString().toLong()
                    sleep(sek * 1000L)
                    requireActivity().runOnUiThread{TextView.text = " ${sek} секунды "}

                }.start()

            }
        }



    }





    companion object {
        fun newInstance() = ThreadFragment()
    }




}







