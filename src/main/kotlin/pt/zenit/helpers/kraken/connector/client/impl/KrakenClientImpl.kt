package pt.zenit.helpers.kraken.connector.client.impl

import pt.zenit.helpers.kraken.connector.client.KrakenClient
import pt.zenit.helpers.kraken.connector.client.utils.RequestHandler

class KrakenClientImpl(private val apiKey: String?, private val secretKey: String?) : KrakenClient {

    override fun getBalance() =
        String(RequestHandler(apiKey, secretKey)
            .queryPrivateEndpoint("Balance", "")
            .second.data)

}
