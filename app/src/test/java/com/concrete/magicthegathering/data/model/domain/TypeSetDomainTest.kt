package com.concrete.magicthegathering.data.model.domain

import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class TypeSetDomainTest {

    lateinit var typeSetDomain: TypeSetDomain

    @Before
    fun setUp() {
        typeSetDomain = TypeSetDomain(
            "nameType")
    }

    @Test
    fun `Should return true if all fields are filled`() {
        assertTrue(typeSetDomain.isValid())
    }
}