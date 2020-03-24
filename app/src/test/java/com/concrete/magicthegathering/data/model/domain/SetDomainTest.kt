package com.concrete.magicthegathering.data.model.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SetDomainTest {

    lateinit var setDomain: SetDomain

    @Before
    fun setUp() {
        setDomain = SetDomain(
            "2ED",
            listOf(CardDomain("image", "name", 2, "typename")))
    }

    @Test
    fun `Should return true if all fields are filled`() {
        assertTrue(setDomain.isValid())
    }

    @Test
    fun `Should return true if the list is not empty`() {
        assertTrue(setDomain.listCardDomain.isNotEmpty())
    }

}