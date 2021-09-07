package com.nuu.cmoney_technology_interview.config

class AppConfig {

    companion object{
        val target = Target.PROD

        //base url
        const val PROD_BASE_API = "https://raw.githubusercontent.com"

        //end point
        const val PROD_ENDPOINT = "/cmmobile/NasaDataSet/main/apod.json"

        val URL_CALL_PHOTOS = serviceBaseApi + serviceEndpoint

        private val serviceBaseApi: String
            private get() {
                return when (target) {
//                    Target.DEV -> DEV_BASE_API
                    Target.PROD -> PROD_BASE_API
                    else -> PROD_BASE_API
                }
            }

        private val serviceEndpoint: String
            private get() {
                return when (target) {
//                    Target.DEV -> DEV_BASE_API
                    Target.PROD -> PROD_ENDPOINT
                    else -> PROD_ENDPOINT
                }
            }

        enum class Target {
            DEV, TEST, PROD
        }
    }
}