package com.example.taskproject.showing_items_feature

import com.example.taskproject.base.MainCoroutineExt
import com.example.taskproject.core.utils.Resource
import com.example.taskproject.showing_items_feature.presentation.viewmodel.ItemsViewModel
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
    fun testGetItems() {

        viewModel.getItems()

        val itemsState = viewModel.items.value
        assertTrue(itemsState is Resource.Success)
    }

    @Test
    fun testGetErrorItems() {
        val fakeItemsUseCase = FakeItemsUseCase(fakeErrorItemsRepository)
        viewModel = ItemsViewModel(fakeItemsUseCase)

        viewModel.getItems()

        val itemsState = viewModel.items.value
        assertTrue(itemsState is Resource.Error)
    }

}