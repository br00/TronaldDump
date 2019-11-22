# TronaldDump

In the age of RX and Dependency Injection I want to be an outsider and make something different!

Can we have a decent app and feel hipster without using RX and Dagger? Yes :)

### Some boring technical details:
The app is made using Kotlin and Architecture Components (ViewModel, Databinding, Repository, etc).

The network layer has been made using Retrofit, extending the retrofit Callback make it retyable and accessible to the Repository class using kotlin higher-order functions.

### Testing:
Unit testing, UI testing using espresso and MockWebServer to mock the api.

### Api:
The app use the public https://docs.tronalddump.io/ apis.
