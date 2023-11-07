package com.example.taskproject.showing_items_feature

import com.example.taskproject.base.MainCoroutineExt
import com.example.taskproject.core.utils.Resource
import com.example.taskproject.showing_items_feature.presentation.viewmodel.ItemsViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ItemsViewModelTest {
    @get:Rule
    val mainCoroutineExt = MainCoroutineExt()
    private lateinit var viewModel: ItemsViewModel
    private lateinit var fakeItemsUseCase: FakeItemsUseCase

    @Before
    fun setup() {
        fakeItemsUseCase = FakeItemsUseCase(fakeSuccessItemsRepository)
        viewModel = ItemsViewModel(fakeItemsUseCase)
    }

    @Test
    fun `when getItems is called with success state then the list of ItemsDateResponse objects should be retrieved`() {

        viewModel.getItems()

        val expectedItemsList = listOf(expectedItems)
        val actualItemsList = viewModel.items.value.data?.map {
            it.date }

        assertEquals(expectedItemsList, actualItemsList)
    }

    @Test
    fun `when getItems is called with failure state then error state should be retrieved`() {
        val fakeItemsUseCase = FakeItemsUseCase(fakeErrorItemsRepository)
        viewModel = ItemsViewModel(fakeItemsUseCase)

        viewModel.getItems()

        val itemsState = viewModel.items.value
        assertTrue(itemsState is Resource.Error)
    }

}