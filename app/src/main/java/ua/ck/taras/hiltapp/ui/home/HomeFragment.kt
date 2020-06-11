package ua.ck.taras.hiltapp.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import dagger.hilt.android.AndroidEntryPoint
import ua.ck.taras.hiltapp.R

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Check viewModel injection
        with(homeViewModel.stateSaveUi ?: "Null UI State") {
            Log.i("HomeFragment", "onViewCreated => UIState: $this")
        }
        Log.i("HomeFragment", "onViewCreated: ${homeViewModel.getDataList().size}")
    }

    override fun onPause() {
        super.onPause()

        // Save UI State
        homeViewModel.stateSaveUi = "Saved UI State"
    }
}