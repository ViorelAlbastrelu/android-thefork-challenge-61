package com.thefork.challenge.search

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.thefork.challenge.api.Page
import com.thefork.challenge.api.UserService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(MockitoJUnitRunner::class)
class SearchPresenterTest {

    private val firstPage = 1u

    private val totalPages = 10u

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @Mock
    lateinit var mockUserService: UserService

    @Mock
    lateinit var mockSearchScreen: SearchScreen

    private lateinit var sut: SearchPresenter

    @Before
    fun setUp() {
        sut = SearchPresenter(
            mockUserService,
            testDispatcher,
            testDispatcher
        )
    }

    @Test
    fun init_OnSuccess() = runTest {
        //given
        whenever(mockUserService.getUsers(firstPage)).thenReturn(
            Response.success(Page(listOf(), totalPages))
        )

        //when
        sut.init(mockSearchScreen)

        //then
        verify(mockSearchScreen, times(1)).displayUsers(any())
    }

    @Test
    fun init_OnError_Null_Body() = runTest {
        //given
        whenever(mockUserService.getUsers(firstPage)).thenReturn(
            Response.success(null)
        )

        //when
        sut.init(mockSearchScreen)

        //then
        verify(mockSearchScreen, times(1)).displayError()
    }

    @Test
    fun init_OnError_Server() = runTest {
        //given
        whenever(mockUserService.getUsers(firstPage)).thenReturn(
            Response.error(400, ResponseBody.create(null, ""))
        )

        //when
        sut.init(mockSearchScreen)

        //then
        verify(mockSearchScreen, times(1)).displayError()
    }

}
