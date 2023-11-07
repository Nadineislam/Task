package com.example.taskproject.showing_items_feature

import com.example.taskproject.base.MainCoroutineExt
import com.example.taskproject.showing_items_feature.presentation.viewmodel.ItemsViewModel
import junit.framework.TestCase.assertEquals
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

        assertEquals(listOf(expectedItems), viewModel.items.value.data?.map {
            it.date
        })
    }

    @Test
    fun `when getItems is called with failure state then error should be retrieved`() {
        val fakeItemsUseCase = FakeItemsUseCase(fakeErrorItemsRepository)
        viewModel = ItemsViewModel(fakeItemsUseCase)

        viewModel.getItems()

        assertEquals("An error occurred", viewModel.items.value.message)
    }

}