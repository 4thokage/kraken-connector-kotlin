package pt.zenit.helpers.kraken.connector.client.utils

import java.security.MessageDigest
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


object SignatureGenerator {

    /*
     * Authentication Algorithm
     */
    fun getSignature(
        apiPrivateKey: String,
        apiPath: String,
        endPointName: String,
        nonce: String,
        apiPostBodyData: String
    ): String {
        return try {
            // GET 256 HASH
            val md = MessageDigest.getInstance("SHA-256")
            md.update((nonce + apiPostBodyData).toByteArray())
            val sha256Hash = md.digest()

            // GET 512 HASH
            val mac = Mac.getInstance("HmacSHA512")
            mac.init(SecretKeySpec(Base64.getDecoder().decode(apiPrivateKey.toByteArray()), "HmacSHA512"))
            mac.update((apiPath + endPointName).toByteArray())

            // CREATE API SIGNATURE
            Base64.getEncoder().encodeToString(mac.doFinal(sha256Hash))
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}
