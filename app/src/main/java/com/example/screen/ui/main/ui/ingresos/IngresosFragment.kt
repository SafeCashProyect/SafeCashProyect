package com.example.screen.ui.main.ui.ingresos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.screen.databinding.FragmentIngresosBinding
class IngresosFragment : Fragment() {

    private var _binding: FragmentIngresosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val ingresosViewModel =
            ViewModelProvider(this).get(IngresosViewModel::class.java)

        _binding = FragmentIngresosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.inflowTextValue
        ingresosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}