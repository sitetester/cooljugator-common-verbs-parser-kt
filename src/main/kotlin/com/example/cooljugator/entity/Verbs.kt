package com.example.cooljugator.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

// https://spring.io/guides/tutorials/spring-boot-kotlin/
@Entity
class Verbs(

    @Id
    @GeneratedValue
    var id: Long? = null,

    var languageCode: String,
    var commonVerbs: String,
)
