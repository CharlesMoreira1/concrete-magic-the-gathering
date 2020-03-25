package com.concrete.magicthegathering.feature.listset.presentation.viewmodel

import com.concrete.magicthegathering.core.helper.Resource
import com.concrete.magicthegathering.data.model.ItemType
import com.concrete.magicthegathering.feature.listset.repository.ISetRepository
import com.concrete.magicthegathering.util.rule.instantLiveDataAndCoroutineRules
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.*
import org.junit.Assert.assertEquals

class SetViewModelTest {
    @get:Rule
    val rule = instantLiveDataAndCoroutineRules

    private val setRepository: ISetRepository = mockk()
    private lateinit var viewModel: SetViewModel

    @Before
    fun setup() {
        viewModel = SetViewModel(setRepository)
    }

    @Test
    fun givenFetchListSetsSuccessfully_shouldEmitSuccess() = runBlocking {
        val repositoryList = listOf<ItemType>(mockk())
        coEvery { setRepository.getListItemType(any(), any()) } returns repositoryList

        viewModel.fetchListSets(1, true)

        assertEquals(Resource.Success(repositoryList), viewModel.getLiveDataListSets.value)
    }
}