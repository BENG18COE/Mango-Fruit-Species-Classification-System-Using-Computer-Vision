package activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.mangoesquality.R
import com.example.mangoesquality.databinding.CameraFragmentBinding


class Camera: AppCompatActivity (){

    private var _binding: CameraFragmentBinding? = null
    private val binding get() = _binding!!


    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onCreate(
        savedInstanceState: Bundle?
    ) {

        super.onCreate(savedInstanceState)
        val binding = CameraFragmentBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        Navigation.findNavController(this, R.id.cameraNavigation)
//        _binding = CameraFragmentBinding.inflate(inflater, container, false)
//        return  binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        Navigation.findNavController(requireActivity(), R.id.cameraNavigation)
//
//    }
}