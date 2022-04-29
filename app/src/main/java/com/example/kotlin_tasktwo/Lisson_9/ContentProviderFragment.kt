package com.example.kotlin_tasktwo.Lisson_9
import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.databinding.TheardFragmentBinding


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

private fun checkPermission(){

    if (ContextCompat.checkSelfPermission(requireContext(),
            Manifest.permission.READ_CONTACTS)
        ==PackageManager.PERMISSION_GRANTED){
        getContacts()
    }else if (shouldShowRequestPermissionRationale( Manifest.permission.READ_CONTACTS)){
        explain()
    }else{
        mRequestPermission()
    }

}


    private fun explain() {

    }

    private fun mRequestPermission() {
        TODO("Not yet implemented")
    }



    private fun  getContacts() {
        TODO("Not yet implemented")
    }


    companion object {
        fun newInstance() = ContentProviderFragment()
    }


}









