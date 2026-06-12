package org.example

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody

fun main() {
    // Example 1: Simple GET request
    println("=== GET Request Example ===")
    getRequest("https://jsonplaceholder.typicode.com/posts/1")

    // Example 2: POST request
    println("\n=== POST Request Example ===")
    postRequest("https://jsonplaceholder.typicode.com/posts")
}

fun getRequest(url: String) {
    val client = OkHttpClient()

    val request = Request.Builder()
        .url(url)
        .build()

    try {
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val body = response.body?.string()
            println("Response Code: ${response.code}")
            println("Response Body: $body")
        } else {
            println("Request failed with code: ${response.code}")
        }
    } catch (e: Exception) {
        println("Error occurred: ${e.message}")
        e.printStackTrace()
    }
}

fun postRequest(url: String) {
    val client = OkHttpClient()

    val jsonBody = """
        {
            "title": "My new post",
            "body": "This is a test post",
            "userId": 1
        }
    """.trimIndent()

    val mediaType = "application/json; charset=utf-8".toMediaType()
    val requestBody = jsonBody.toRequestBody(mediaType)

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    try {
        val response = client.newCall(request).execute()

        if (response.isSuccessful) {
            val body = response.body?.string()
            println("Response Code: ${response.code}")
            println("Response Body: $body")
        } else {
            println("Request failed with code: ${response.code}")
        }
    } catch (e: Exception) {
        println("Error occurred: ${e.message}")
        e.printStackTrace()
    }
}