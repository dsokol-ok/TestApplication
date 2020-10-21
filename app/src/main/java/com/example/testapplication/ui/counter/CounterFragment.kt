package com.example.testapplication.ui.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import com.example.testapplication.R
import com.example.testapplication.databinding.FragmentCounterBinding

class CounterFragment : Fragment(), View.OnClickListener {

    private lateinit var counterViewModel: CounterViewModel

    private lateinit var binding: FragmentCounterBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        counterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        binding = FragmentCounterBinding.inflate(inflater, container, false)

        binding.clickListener = this
        binding.lifecycleOwner = viewLifecycleOwner

        counterViewModel.text.observe(viewLifecycleOwner){
            binding.counterTv.text = it.toString()
            Toast.makeText(context, "Текущее значение счетчика = ${counterViewModel.text.value}", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    override fun onClick(v: View?) {
        if(v == null) return

        when (v.id){
            R.id.increase_btn -> counterViewModel.plusOne()
            R.id.decrease_btn -> counterViewModel.minusOne()
        }
    }
}