package com.concrete.magicthegathering.data.model.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CardDomainTest {

    lateinit var cardDomain: CardDomain

    @Before
    fun setUp() {
        cardDomain = CardDomain(
            "image",
            "name",
            "2",
            "typeName")
    }

    @Test
    fun `Should return true if all fields are filled`() {
        assertTrue(cardDomain.isValid())
    }
}