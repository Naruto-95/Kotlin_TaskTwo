package com.example.kotlin_tasktwo.lesson_9

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.ContentResolver
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kotlin_tasktwo.R
import com.example.kotlin_tasktwo.databinding.ContentProviderFragmentBinding


class ContentProviderFragment : Fragment() {

    private var _binding: ContentProviderFragmentBinding? = null
    private val binding: ContentProviderFragmentBinding
        get() {
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
        _binding = ContentProviderFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }


    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkPermission()
        checkPermissionw()

    }


    private fun checkPermissionw() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CALL_PHONE
            ) == PackageManager.PERMISSION_GRANTED&&
            ContextCompat.checkSelfPermission(
                requireContext(),Manifest.permission. READ_PHONE_NUMBERS
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            getPhoneCall()
        }else{
            mRequestPermission1()

        }
    }
    @SuppressLint("Recycle")
    private fun getContacts() {
        val contentResolver: ContentResolver = requireContext().contentResolver
        val cursor = contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " ASC "
        )
        cursor?.let {
            for (i in 0 until it.count) {
                if (cursor.moveToPosition(i)) {
                    val id = cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)
                    val name: String = cursor.getString(id)
                    binding.containerForContacts.addView(TextView(requireContext()).apply {
                        textSize = 35f
                        text = name

                    })
                }
            }
        }

    }


    private fun getPhoneCall() {
       val contentResolver: ContentResolver = requireContext().contentResolver
        val cursorNumber = contentResolver.query(ContactsContract.Contacts.CONTENT_URI,
            null, null, null, ContactsContract.Contacts.HAS_PHONE_NUMBER + "  ASC ")
        cursorNumber?.let {
            for (i in 0 until it.count) {
                if (cursorNumber.moveToPosition(i)) {
                    val id = cursorNumber.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)
                    val name: String? = cursorNumber.getString(id)
                    binding.containerForContacts.addView(TextView(requireContext()).apply {
                        textSize = 35f
                        text = name


                    })

                }

            }

        }

    }



    private val REQUEST_CALL = 779
    private val REQUEST_NUMBER = 778
    private fun mRequestPermission1(){
        requestPermissions(arrayOf(Manifest.permission.READ_PHONE_NUMBERS), REQUEST_NUMBER)
        requestPermissions(arrayOf(Manifest.permission.CALL_PHONE), REQUEST_CALL)
    }


    private val REQUEST_CODE = 777
    private fun mRequestPermission() {
        requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), REQUEST_CODE)


    }


    private fun checkPermission() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_CONTACTS
            )
            == PackageManager.PERMISSION_GRANTED
        ) {
            getContacts()
        } else if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
            explain()
        } else {
            mRequestPermission()//разрешение запроса
        }

    }


    private fun explain() {
        AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.Title))
            .setMessage(getString(R.string.Message))
            .setPositiveButton(getString(R.string.Grantaccess)) { _, _ ->
                mRequestPermission()
            }
            .setNegativeButton(getString(R.string.Nohanks)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }


    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.READ_CONTACTS && grantResults[i] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    getContacts()


                } else {
                    explain()
                }
            }

        } else if (requestCode == REQUEST_NUMBER) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.READ_PHONE_NUMBERS && grantResults[i] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    getPhoneCall()
                } else {
                    explain()
                }
            }
        } else if (requestCode == REQUEST_CALL) {
            for (i in permissions.indices) {
                if (permissions[i] == Manifest.permission.CALL_PHONE && grantResults[i] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    getPhoneCall()
                } else {
                    explain()
                }
            }

        }else{
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }





        companion object {
            fun newInstance() = ContentProviderFragment()
        }


}














