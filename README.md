# kraken-connector-kotlin
API client for Kraken exchange in kotlin

![example workflow](https://github.com/4thokage/kraken-connector-kotlin/actions/workflows/main.yml/badge.svg) 
![example workflow](https://github.com/4thokage/kraken-connector-kotlin/actions/workflows/main-release.yml/badge.svg) 
[![codecov](https://codecov.io/gh/4thokage/kraken-connector-kotlin/branch/main/graph/badge.svg?token=96M5A7MZ1R)](https://codecov.io/gh/4thokage/binance-connector-kotlin)

## Usage example:

```kotlin
import pt.zenit.helpers.kraken.connector.client.impl.KrakenClientImpl

val client = KrakenClientImpl(apiKey, secretKey)
val data = client.getBalance() // String containing api response
```
