package com.oganbelema.samplekmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform