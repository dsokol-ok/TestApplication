package com.example.testapplication.ui.file

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentFileHomeBinding

class FileFragment : Fragment(), View.OnClickListener {

    private lateinit var fileViewModel: FileViewModel

    private lateinit var binding: FragmentFileHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        fileViewModel = ViewModelProvider(this).get(FileViewModel::class.java)

        binding = FragmentFileHomeBinding.inflate(inflater, container, false)

        binding.apply {
            clickListener = this@FileFragment
            lifecycleOwner = viewLifecycleOwner
            viewModel = fileViewModel
        }

        fileViewModel.isCreatedFile.observe(viewLifecycleOwner){

            val text = if (it){
                "File created"
            } else {
                "File not created"
            }

            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        fileViewModel.isWroteText.observe(viewLifecycleOwner){

            val text = if (it){
                "Text is written"
            } else {
                "File not created or you are writing empty text"
            }

            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        if (v == null) return

        when (v.id){
            R.id.create_file_btn -> fileViewModel.createFile(requireActivity())
            R.id.write_text_to_file_btn -> fileViewModel.writeTextToFile(binding.textFileEditText.text.toString())
            R.id.read_from_file_btn -> fileViewModel.readTextFromFile(context)
        }
    }
}
