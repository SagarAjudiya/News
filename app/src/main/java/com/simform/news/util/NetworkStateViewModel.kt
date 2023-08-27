package com.simform.news.util

import android.app.Application
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class NetworkStateViewModel(private val application: Application) : AndroidViewModel(application) {

    private val networkStateLiveData = MutableLiveData<Boolean>()

    val networkState: MutableLiveData<Boolean>
        get() = networkStateLiveData

    fun getNetworkUpdate() {

        val networkRequest = NetworkRequest.Builder()
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()


        val TAG = "Network"

        val networkCallback = object : ConnectivityManager.NetworkCallback() {

            override fun onAvailable(network: Network) {
                super.onAvailable(network)
//                Toast.makeText(application, "onAvailable", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onAvailable")
                networkStateLiveData.postValue(true)
            }

            override fun onUnavailable() {
                super.onUnavailable()
//                Toast.makeText(application, "onUnavailable", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onUnavailable")
                networkStateLiveData.postValue(false)
            }


            override fun onLost(network: Network) {
                super.onLost(network)
//                Toast.makeText(application, "Lost", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onLost")
                networkStateLiveData.postValue(false)
            }

            override fun onLosing(network: Network, maxMsToLive: Int) {
                super.onLosing(network, maxMsToLive)
//                Toast.makeText(application, "on Losing", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onLosing")

            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
//                Toast.makeText(application, "onCapabilitiesChanged", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "onCapabilitiesChanged")
            }
        }

        val connectivityManager =
            application.getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)
    }
}