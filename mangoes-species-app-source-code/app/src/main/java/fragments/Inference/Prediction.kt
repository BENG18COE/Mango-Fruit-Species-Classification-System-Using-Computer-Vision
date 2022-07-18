package fragments.Inference

import APIs.InferenceService
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.mangoesquality.R
import com.example.mangoesquality.databinding.PredictionFragmentBinding
import com.jpegkit.Jpeg
import io.ktor.util.cio.*
import kotlinx.coroutines.launch
import models.InferenceRequest
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.runOnUiThread

import java.util.*
import kotlin.math.roundToInt

class Prediction: Fragment() {

    private var _binding: PredictionFragmentBinding? = null
    private val binding get() = _binding!!

    private val inferenceService = InferenceService.create()

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.clear()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = PredictionFragmentBinding.inflate(inflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.takePicture.setOnClickListener {
            view.findNavController().navigate(R.id.takeCamera)
        }

        val imageBase64 = arguments?.getString("image")
        val imageBytes = Base64.decode(imageBase64, Base64.DEFAULT)
        Log.d("Camera", imageBytes.toString())

        binding.inferenceResults.visibility = View.GONE
        binding.loader.visibility = View.VISIBLE

       lifecycleScope.launch {
           val responsePayload = inferenceService.predict(
               InferenceRequest(
                   application = "mangoes-species", image = imageBase64!!
               )
           )
           if (responsePayload != null) {
               runOnUiThread {

                   binding.loader.visibility = View.GONE


                   binding.predictionConfidence.text = String.format("%.2f", (responsePayload.confidence * 100.00))

                   if (responsePayload.results == 0) {
                       binding.predictionResults.text = "Keit"
                   }
                   if (responsePayload.results == 1) {
                       binding.predictionResults.text = "The image is neither Keit nor Rubby"
                   }
                   if (responsePayload.results == 2) {
                       binding.predictionResults.text = "Rubby"
                   }
                   binding.inferenceResults.visibility = View.VISIBLE
               }
           }
           else {
               runOnUiThread {
                   binding.loader.visibility = View.VISIBLE
                   binding.inferenceResults.visibility = View.GONE
               }
           }

       }


        val jpeg = Jpeg(imageBytes)
//
        binding.capturedImage.setJpeg(jpeg)

    }

}