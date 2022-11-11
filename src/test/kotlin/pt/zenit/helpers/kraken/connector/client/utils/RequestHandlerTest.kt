package pt.zenit.helpers.kraken.connector.client.utils

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

internal class RequestHandlerTest {

    @Test
    fun testPublicEndpoints() {

        val response = RequestHandler().queryPublicEndpoint("AssetPairs", "pair=ethusd,xbtusd")
        assertEquals(200, response.second.statusCode)
    }

    @Test
    fun testPrivateEndpoints() {

        val response = RequestHandler(
            "MyKey",
            "MySecret"
        ).queryPrivateEndpoint("Balance", "")
        assertEquals(200, response.second.statusCode)
    }
}
