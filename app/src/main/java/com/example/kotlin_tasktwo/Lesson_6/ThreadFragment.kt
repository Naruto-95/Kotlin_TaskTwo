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
        val myThread = MyThread()
            myThread.start()
        with(binding){
            val fors = edTeFast.text.toString().toLong()
            var num = 1
            BtnFast.setOnClickListener {
                Thread{
                    sleep(fors * 1000L)
                    requireActivity().runOnUiThread{
                        TextViewTwo.text = " ${fors} секунды "
                        GeneralText("${ Thread.currentThread().name} ${num++}")
                    }
                }.start()

            }
                //2 00
          BtnLong.setOnClickListener{
              val long = edTeLong.text.toString().toLong()
              myThread.mHandler?.post {
                    sleep(long * 1000L)
                    Handler(Looper.getMainLooper()).post{
                        TextViewOne.text = " ${long} секунды "
                        GeneralText("${ Thread.currentThread().name} ${num++}")
                    }

                }
                }



        }


        }




private fun GeneralText(name:String){
    binding.mainContainer.addView(TextView(requireContext()).apply {
        text = name
        textSize = 20f
    })
}


    class MyThread:Thread(){
            var mHandler:Handler? = null
           override fun run() {
               Looper.prepare()
               mHandler = Handler(Looper.myLooper()!!)
               Looper.loop()

           }

    }


    companion object {
        fun newInstance() = ThreadFragment()
    }


}









