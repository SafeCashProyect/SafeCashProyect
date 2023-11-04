package com.example.screen.ui.main.ui.deducibles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.screen.databinding.FragmentDeduciblesBinding

class DeduciblesFragment : Fragment() {

    private var _binding: FragmentDeduciblesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val deduciblesViewModel =
            ViewModelProvider(this).get(DeduciblesViewModel::class.java)

        _binding = FragmentDeduciblesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.deductibleTextValue
        deduciblesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}