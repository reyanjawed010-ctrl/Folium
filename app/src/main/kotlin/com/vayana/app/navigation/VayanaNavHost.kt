package com.folium.app.navigation

import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleOut
import androidx.compose.runtime.Composable
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.fillMaxSize
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.folium.core.designsystem.theme.LocalDisplayProfile
import com.folium.core.designsystem.theme.LocalMotionSetting
import com.folium.core.designsystem.theme.vayanaNavEnter
import com.folium.core.designsystem.theme.vayanaNavExit
import com.folium.core.designsystem.theme.vayanaNavPopEnter
import com.folium.core.designsystem.theme.vayanaNavPopExit
import com.folium.core.designsystem.theme.vayanaSharedElementEnter
import com.folium.core.designsystem.theme.vayanaSharedElementExit
import com.folium.core.designsystem.theme.vayanaSharedElementCrossfadeEnter
import com.folium.core.designsystem.theme.vayanaSharedElementCrossfadeExit
import com.folium.core.designsystem.theme.vayanaNavTabEnter
import com.folium.core.designsystem.theme.vayanaNavTabExit
import com.folium.feature.gutenberg.GutenbergRoute as GutenbergScreenRoute
import com.folium.feature.opds.OpdsBrowseRoute as OpdsBrowseScreenRoute
import com.folium.feature.opds.OpdsCatalogsRoute as OpdsCatalogsScreenRoute
import com.folium.feature.library.OfflineBooksRoute as OfflineBooksScreenRoute
import com.folium.feature.library.RecentlyDeletedRoute as RecentlyDeletedScreenRoute
import com.folium.feature.library.LibraryAddAction
import com.folium.feature.library.BookOpenTransitionSource
import com.folium.feature.library.ProvideBookSharedTransitionScopes
import com.folium.feature.library.LibraryViewModel
import com.folium.feature.library.ShelfDetailRoute as ShelfDetailScreenRoute
import com.folium.feature.library.ShelvesRoute as ShelvesScreenRoute
import com.folium.feature.library.SeriesFolderScreenRoute
import com.folium.feature.notes.NotesRoute
import com.folium.feature.help.HelpRoute as HelpScreenRoute
import com.folium.feature.reader.ReaderRoute as ReaderScreenRoute
import com.folium.feature.search.SearchRoute as SearchScreenRoute
import com.folium.feature.settings.DiagnosticsRoute as DiagnosticsScreenRoute
import com.folium.feature.settings.DataUsageRoute as DataUsageScreenRoute
import com.folium.feature.settings.PrivacyRoute as PrivacyScreenRoute
import com.folium.feature.settings.SettingsRoute as SettingsScreenRoute
import com.folium.feature.statistics.HighlightReviewRoute as HighlightReviewScreenRoute
import com.folium.feature.statistics.LearnWordsRoute as LearnWordsScreenRoute
import com.folium.feature.statistics.StatisticsRoute
import com.folium.feature.statistics.VocabularyReviewRoute as VocabularyReviewScreenRoute

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun VayanaNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: TopLevelRoute = TopLevelRoute.Library,
) {
    val displayProfile = LocalDisplayProfile.current
    val motionSetting = LocalMotionSetting.current
    val motionScheme = MaterialTheme.motionScheme

    SharedTransitionLayout(modifier = modifier) {
        NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.fillMaxSize(),
        enterTransition = {
            when {
                targetState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeEnter(displayProfile, motionSetting)
                targetState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementEnter(displayProfile, motionSetting)
                initialState.destination.isTopLevelTab() && targetState.destination.isTopLevelTab() ->
                    vayanaNavTabEnter(displayProfile, motionSetting, motionScheme, initialState.destination.tabDirectionTo(targetState.destination))
                else -> vayanaNavEnter(displayProfile, motionSetting, motionScheme)
            }
        },
        exitTransition = {
            when {
                targetState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeExit(displayProfile, motionSetting)
                targetState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementExit(displayProfile, motionSetting)
                initialState.destination.isTopLevelTab() && targetState.destination.isTopLevelTab() ->
                    vayanaNavTabExit(displayProfile, motionSetting, motionScheme, initialState.destination.tabDirectionTo(targetState.destination))
                else -> vayanaNavExit(displayProfile, motionSetting, motionScheme)
            }
        },
        popEnterTransition = {
            when {
                initialState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeEnter(displayProfile, motionSetting)
                initialState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementEnter(displayProfile, motionSetting)
                initialState.destination.isTopLevelTab() && targetState.destination.isTopLevelTab() ->
                    vayanaNavTabEnter(displayProfile, motionSetting, motionScheme, initialState.destination.tabDirectionTo(targetState.destination))
                else -> vayanaNavPopEnter(displayProfile, motionSetting, motionScheme)
            }
        },
        popExitTransition = {
            when {
                initialState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeExit(displayProfile, motionSetting)
                initialState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementExit(displayProfile, motionSetting)
                initialState.destination.isTopLevelTab() && targetState.destination.isTopLevelTab() ->
                    vayanaNavTabExit(displayProfile, motionSetting, motionScheme, initialState.destination.tabDirectionTo(targetState.destination))
                else -> vayanaNavPopExit(displayProfile, motionSetting, motionScheme)
            }
        },
        predictivePopEnterTransition = { _ ->
            when {
                initialState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeEnter(displayProfile, motionSetting)
                initialState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementEnter(displayProfile, motionSetting)
                else -> fadeIn(animationSpec = spring(dampingRatio = 1f, stiffness = 1_600f))
            }
        },
        predictivePopExitTransition = { _ ->
            when {
                initialState.bookOpenTransitionSource() == BookOpenTransitionSource.COVER ->
                    vayanaSharedElementCrossfadeExit(displayProfile, motionSetting)
                initialState.destination.hasRoute<BookDetailRoute>() ->
                    vayanaSharedElementExit(displayProfile, motionSetting)
                else -> scaleOut(targetScale = 0.7f)
            }
        },
    ) {
        composable<TopLevelRoute.Library> { backStackEntry ->
            val addBookActionName by backStackEntry.savedStateHandle
                .getStateFlow<String?>(LIBRARY_ADD_ACTION_KEY, null)
                .collectAsStateWithLifecycle()
            LibraryListDetailRoute(
                navController = navController,
                sharedTransitionScope = this@SharedTransitionLayout,
                animatedVisibilityScope = this@composable,
                onSettingsClick = { navController.navigate(SettingsRoute) },
                onSearchClick = { navController.navigate(SearchRoute) },
                onRecentlyDeletedClick = { navController.navigate(RecentlyDeletedRoute) },
                onShelvesClick = { navController.navigate(ShelvesRoute) },
                onOfflineBooksClick = { navController.navigate(OfflineBooksRoute) },
                onFreeBooksClick = { navController.navigate(GutenbergRoute) },
                onReviewWords = { navController.navigate(VocabularyReviewRoute) },
                onReviewHighlights = { navController.navigate(HighlightReviewRoute) },
                onContinueReading = { bookId, locator ->
                    navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator))
                },
                addBookAction = LibraryAddAction.entries.find { action -> action.name == addBookActionName },
                onAddBookActionHandled = {
                    backStackEntry.savedStateHandle[LIBRARY_ADD_ACTION_KEY] = null
                },
            )
        }
        composable<TopLevelRoute.Notes> {
            NotesRoute(
                onOpenReader = { bookId, locator ->
                    navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator))
                },
            )
        }
        composable<TopLevelRoute.Statistics> {
            StatisticsRoute(
                onOpenBook = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
                onReviewVocabulary = { navController.navigate(VocabularyReviewRoute) },
                onOpenLearnWords = { navController.navigate(LearnWordsRoute) },
                onReviewHighlights = { navController.navigate(HighlightReviewRoute) },
            )
        }
        composable<VocabularyReviewRoute> {
            VocabularyReviewScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<HighlightReviewRoute> {
            HighlightReviewScreenRoute(
                onBack = { navController.popBackStack() },
                onOpenReader = { bookId, locator -> navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator)) },
            )
        }
        composable<LearnWordsRoute> {
            LearnWordsScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<SettingsRoute> {
            SettingsScreenRoute(
                onBack = { navController.popBackStack() },
                onOpenLibrary = { navController.popBackStack() },
                onHelpClick = { navController.navigate(HelpRoute) },
                onDiagnosticsClick = { navController.navigate(DiagnosticsRoute) },
                onDataUsageClick = { navController.navigate(DataUsageRoute) },
                onPrivacyClick = { navController.navigate(PrivacyRoute) },
            )
        }
        composable<HelpRoute> {
            HelpScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<DiagnosticsRoute> {
            DiagnosticsScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<PrivacyRoute> {
            PrivacyScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<DataUsageRoute> {
            DataUsageScreenRoute(onBack = { navController.popBackStack() })
        }
        composable<SearchRoute> {
            SearchScreenRoute(
                onBack = { navController.popBackStack() },
                onOpenBook = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
                onOpenReader = { bookId, locator -> navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator)) },
            )
        }
        composable<RecentlyDeletedRoute> { backStackEntry ->
            RecentlyDeletedScreenRoute(
                onBack = { navController.popBackStack() },
                viewModel = navController.sharedLibraryViewModel(backStackEntry),
            )
        }
        composable<GutenbergRoute> {
            GutenbergScreenRoute(
                onBack = { navController.popBackStack() },
                // The library shows the import's progress and the new book.
                onImported = { navController.popBackStack() },
                onOpenBook = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
                onOpenCatalogs = { navController.navigate(OpdsCatalogsRoute) },
            )
        }
        composable<OpdsCatalogsRoute> {
            OpdsCatalogsScreenRoute(
                onBack = { navController.popBackStack() },
                onOpenCatalog = { catalogId -> navController.navigate(OpdsBrowseRoute(catalogId)) },
            )
        }
        composable<OpdsBrowseRoute> {
            OpdsBrowseScreenRoute(
                onBack = { navController.popBackStack() },
                // The library shows the import's progress and the new book.
                onImported = { navController.popBackStack(TopLevelRoute.Library, inclusive = false) },
                onOpenBook = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
            )
        }
        composable<OfflineBooksRoute> { backStackEntry ->
            val addBookRequested by backStackEntry.savedStateHandle
                .getStateFlow(OFFLINE_ADD_BOOK_KEY, false)
                .collectAsStateWithLifecycle()
            OfflineBooksScreenRoute(
                addBookRequested = addBookRequested,
                onAddBookRequestHandled = { backStackEntry.savedStateHandle[OFFLINE_ADD_BOOK_KEY] = false },
                viewModel = navController.sharedLibraryViewModel(backStackEntry),
                onBack = { navController.popBackStack() },
                onBookClick = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
                onFetchGoodreads = { bookId -> navController.navigate(BookDetailRoute(bookId, openGoodreads = true)) },
            )
        }
        composable<ShelvesRoute> { backStackEntry ->
            ShelvesScreenRoute(
                viewModel = navController.sharedLibraryViewModel(backStackEntry),
                onBack = { navController.popBackStack() },
                onShelfClick = { shelfId -> navController.navigate(ShelfDetailRoute(shelfId)) },
                onBookClick = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
            )
        }
        composable<ShelfDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<ShelfDetailRoute>()
            ShelfDetailScreenRoute(
                viewModel = navController.sharedLibraryViewModel(backStackEntry),
                shelfId = route.shelfId,
                onBack = { navController.popBackStack() },
                onBookClick = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
            )
        }
        composable<SeriesFolderRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<SeriesFolderRoute>()
            SeriesFolderScreenRoute(
                viewModel = navController.sharedLibraryViewModel(backStackEntry),
                seriesKey = route.seriesKey,
                onBack = { navController.popBackStack() },
                onBookClick = { bookId -> navController.navigate(BookDetailRoute(bookId)) },
            )
        }
        composable<BookDetailRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<BookDetailRoute>()
            val transitionSource = remember(route.transitionSource) {
                BookOpenTransitionSource.entries.firstOrNull { source -> source.name == route.transitionSource }
            }
            val libraryViewModel = navController.sharedLibraryViewModel(backStackEntry)
            ProvideBookSharedTransitionScopes(this@SharedTransitionLayout, this@composable) {
                com.folium.feature.library.BookDetailRoute(
                    bookId = route.bookId,
                    transitionSource = transitionSource,
                    openGoodreads = route.openGoodreads,
                    viewModel = libraryViewModel,
                    onBack = { navController.popBackStack() },
                    onReadableSourceChanged = { readable ->
                        backStackEntry.savedStateHandle[BOOK_DETAIL_READABLE_KEY] = readable
                    },
                    onContinueReading = { bookId, locator ->
                        navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator))
                    },
                    onOpenNotes = { bookId -> navController.navigate(BookNotesRoute(bookId)) },
                    onReadFromStart = { bookId -> navController.navigate(ReaderRoute(bookId = bookId, fromStart = true)) },
                )
            }
        }
        composable<BookNotesRoute> { backStackEntry ->
            val route = backStackEntry.toRoute<BookNotesRoute>()
            NotesRoute(
                onOpenReader = { bookId, locator ->
                    navController.navigate(ReaderRoute(bookId = bookId, targetLocator = locator))
                },
                bookId = route.bookId,
                onBack = { navController.popBackStack() },
            )
        }
        composable<ReaderRoute> {
            ReaderScreenRoute(
                onBack = { navController.popBackStack() },
                onReviewVocabulary = { navController.navigate(VocabularyReviewRoute) },
            )
        }
        }
    }
}

private fun NavDestination.isTopLevelTab(): Boolean =
    TopLevelDestination.entries.any { destination -> hasRoute(destination.routeClass) }

private fun NavBackStackEntry.bookOpenTransitionSource(): BookOpenTransitionSource? {
    if (!destination.hasRoute<BookDetailRoute>()) return null
    val sourceName = toRoute<BookDetailRoute>().transitionSource ?: return null
    return BookOpenTransitionSource.entries.firstOrNull { source -> source.name == sourceName }
}

private fun NavDestination.tabDirectionTo(target: NavDestination): Int {
    val initialIndex = TopLevelDestination.entries.indexOfFirst { hasRoute(it.routeClass) }
    val targetIndex = TopLevelDestination.entries.indexOfFirst { target.hasRoute(it.routeClass) }
    return if (targetIndex >= initialIndex) 1 else -1
}

/** The Books tab's entry, which owns the shared [LibraryViewModel]; [fallback] when it isn't on the back stack. */
private fun NavHostController.libraryEntryOr(fallback: NavBackStackEntry): NavBackStackEntry =
    runCatching { getBackStackEntry<TopLevelRoute.Library>() }.getOrDefault(fallback)

/**
 * The Books tab's [LibraryViewModel] for screens reached from it (details, shelves, folders, offline books), instead
 * of a fresh copy per screen that would start its own library flows and launch checks.
 */
@Composable
private fun NavHostController.sharedLibraryViewModel(entry: NavBackStackEntry): LibraryViewModel =
    hiltViewModel(remember(entry) { libraryEntryOr(entry) })
