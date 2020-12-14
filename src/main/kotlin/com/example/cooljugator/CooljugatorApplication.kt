package com.example.cooljugator

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CooljugatorApplication

fun main(args: Array<String>) {
    runApplication<CooljugatorApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}