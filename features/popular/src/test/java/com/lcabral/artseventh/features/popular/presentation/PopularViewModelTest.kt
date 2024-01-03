package com.lcabral.artseventh.features.popular.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lcabral.artseventh.core.domain.usecase.GetPopularUseCase
import com.lcabral.artseventh.features.popular.presentation.viewmodel.FAILURE_CHILD
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewAction
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewModel
import com.lcabral.artseventh.features.popular.presentation.viewmodel.PopularViewState
import com.lcabral.artseventh.features.popular.presentation.viewmodel.SUCCESS_CHILD
import com.lcabral.artseventh.libraries.arch.test.utils.movieDataTest
import com.lcabral.artseventh.libraries.arch.test.utils.moviesData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
internal class PopularViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val getPopularUseCase: GetPopularUseCase = mockk(relaxed = true)
    private lateinit var subject: PopularViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    private fun setupSubject() {
        subject = PopularViewModel(
            popularUseCase = getPopularUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPopular Should show popular When is invoked`() = runTest {
        // Given
        val expectedState = PopularViewState(
            flipperChild = SUCCESS_CHILD,
            getPopularResultItems = moviesData()
        )

        coEvery { getPopularUseCase() } returns flowOf(moviesData())

        // When
        setupSubject()

        // Then
        assertEquals(expectedState, subject.viewState.value)
    }

    @Test
    fun `getPopular Should catch error When throws on error`() = runBlocking {
        // Given
        val exception = ClassCastException()

        coEvery { getPopularUseCase() } throws exception

        // When
        setupSubject()

        // Then
        coVerify { getPopularUseCase().invoke(exception.toString()) }
    }

    @Test
    fun `getPopular Should return error When is handleError`() {
        // Given
        val expectedState = PopularViewState(
            flipperChild = FAILURE_CHILD,
            getPopularResultItems = null
        )
        coEvery { getPopularUseCase() } returns flowOf(emptyList())

        // When
        setupSubject()

        // Then
        verify { getPopularUseCase().invoke(expectedState.toString()) }
    }

    @Test
    fun `onAdapterItemClicked Should emit expected action goToDetails`() = runBlocking {
        // Given
        val expectedAction = PopularViewAction.GoToDetails(movieDataTest())
        every { getPopularUseCase() } returns mockk(relaxed = true)
        setupSubject()

        // When
        subject.onAdapterItemClicked(movieDataTest())

        //Then
        assertEquals(expectedAction, subject.viewAction.value)
    }
}

