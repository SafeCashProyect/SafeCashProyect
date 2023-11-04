package com.example.screen.ui.main.ui.gastos

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.screen.R
import com.example.screen.databinding.FragmentGastosBinding
import com.example.screen.ui.main.ui.gastos.GastosViewModel

class GastosFragment : Fragment() {

    private var _binding: FragmentGastosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val gastosViewModel =
            ViewModelProvider(this).get(GastosViewModel::class.java)

        _binding = FragmentGastosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.costTextValue
        gastosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}