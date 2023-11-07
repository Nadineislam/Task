package com.example.taskproject.showing_items_feature

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class ItemsUseCaseTest {

    private lateinit var successItemsUseCase: FakeItemsUseCase
    private lateinit var errorItemsUseCase: FakeErrorItemsUseCase

    @Before
    fun setup() {
        successItemsUseCase = FakeItemsUseCase(fakeSuccessItemsRepository)
        errorItemsUseCase = FakeErrorItemsUseCase()
    }

    @Test
    fun `when invoke is called with success state then a list of combined responses should be retrieved`() {
        runBlocking {

            assertEquals(listOf(expectedCombinedResponse), successItemsUseCase().body())
        }
    }

    @Test
    fun `when an error response is encountered during invocation then an error response should be returned`() {
        runBlocking {

            assertFalse(errorItemsUseCase().isSuccessful)

        }
    }
}