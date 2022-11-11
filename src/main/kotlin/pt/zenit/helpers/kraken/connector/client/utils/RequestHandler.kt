package pt.zenit.helpers.kraken.connector.client.utils

import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.ResponseResultOf


class RequestHandler(private val apiKey: String?, private val secretKey: String?) {
    constructor() : this(null, null)


    /**
     * Query public endpoint
     *
     * @param endPointName
     * @param inputParameters
     * @return
     */
    fun queryPublicEndpoint(endPointName: String, inputParameters: String): ResponseResultOf<String> {
        val baseDomain = "https://api.kraken.com"
        val publicPath = "/0/public/"
        val apiEndpointFullURL = "$baseDomain$publicPath$endPointName?$inputParameters"

        return Fuel.get(apiEndpointFullURL).responseString()
    }

    /*
     * Private REST API Endpoints
     */
    fun queryPrivateEndpoint(
        endPointName: String,
        inputParameters: String,
    ): ResponseResultOf<String> {
        val baseDomain = "https://api.kraken.com"
        val privatePath = "/0/private/"
        val apiEndpointFullURL = "$baseDomain$privatePath$endPointName?$inputParameters"
        val nonce = System.currentTimeMillis().toString()
        val apiPostBodyData = "nonce=$nonce&$inputParameters"
        val signature: String = SignatureGenerator.getSignature(
            secretKey!!,
            privatePath,
            endPointName,
            nonce,
            apiPostBodyData
        )
        return Fuel.post(apiEndpointFullURL)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .header("API-Key", apiKey!!)
            .header("API-Sign", signature)
            .body(apiPostBodyData)
            .responseString()

    }

}
