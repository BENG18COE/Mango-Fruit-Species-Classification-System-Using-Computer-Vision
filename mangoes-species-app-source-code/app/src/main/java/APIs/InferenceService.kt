package APIs

import io.ktor.client.*
import models.InferenceRequest
import io.ktor.client.request.post
import models.InferenceResponse
import utils.ktorHttpClient


interface InferenceService {
    suspend fun predict(inferenceRequest: InferenceRequest): InferenceResponse?

    companion object {
        fun create(): InferenceService {
            return InferenceServiceImpl(
                client = ktorHttpClient
            )
        }
    }

}