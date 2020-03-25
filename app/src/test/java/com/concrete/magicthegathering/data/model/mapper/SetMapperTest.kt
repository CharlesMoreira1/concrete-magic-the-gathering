package com.concrete.magicthegathering.data.model.mapper

import com.concrete.magicthegathering.data.model.mapper.mock.MockSets
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class SetMapperTest {
    private val BASE_URL_IMAGE_CARD = "https://gatherer.wizards.com/Handlers/Image.ashx?name=%s&type=card"

    private lateinit var mockSets: MockSets

    @Before
    fun setUp() {
        mockSets = MockSets()
    }

    @Test
    fun `Checks if entity card has become domain card`() {
        val listCardDomain = SetMapper.transformEntityToDomainListCards(mockSets.mockEntityList())
        val outputOne = listCardDomain[1]

        assertEquals(String.format(BASE_URL_IMAGE_CARD, "name card 1"), outputOne.image)
        assertEquals("name card 1", outputOne.name)

        val outputTwo = listCardDomain[2]

        assertEquals(String.format(BASE_URL_IMAGE_CARD, "name card 2"), outputTwo.image)
        assertEquals("name card 2", outputTwo.name)
    }
}