package br.edu.ifal.casajunta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.casajunta.ui.CasaJuntaApp
import br.edu.ifal.casajunta.ui.screens.login.LoginRoute
import br.edu.ifal.casajunta.ui.screens.myHouses.MyHousesScreen
import br.edu.ifal.casajunta.ui.screens.onboarding.OnboardingScreen

private object AppRoutes {

    const val HOME = "home"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val MY_HOUSES = "my_houses"
}

@Composable
fun AppNavHost(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.ONBOARDING
    ) {

        composable(AppRoutes.ONBOARDING) {
            OnboardingScreen(
                onMoradorClick = {
                    navController.navigate(AppRoutes.LOGIN)
                }
            )
        }

        composable(AppRoutes.LOGIN) {
            LoginRoute(
                onVoltarClick = {
                    navController.popBackStack()
                },
                onLoginSuccess = {
                    navController.navigate(AppRoutes.MY_HOUSES) {
                        popUpTo(AppRoutes.ONBOARDING) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.MY_HOUSES) {
            MyHousesScreen(
                onEntrarCasaClick = { casaId ->
                    navController.navigate(AppRoutes.HOME) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(AppRoutes.HOME) {
            CasaJuntaApp(
                isDarkTheme = isDarkTheme,
                onToggleTheme = onToggleTheme
            )
        }
    }
}