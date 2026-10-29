package br.edu.ifal.casajunta.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.edu.ifal.casajunta.ui.CasaJuntaApp
import br.edu.ifal.casajunta.ui.screens.login.LoginRoute
import br.edu.ifal.casajunta.ui.screens.myHouses.MyHousesScreen
import br.edu.ifal.casajunta.ui.screens.onboarding.OnboardingScreen
import br.edu.ifal.casajunta.ui.screens.createHouse.CreateHouseScreen

private object AppRoutes {

    const val HOME = "home"
    const val ONBOARDING = "onboarding"
    const val LOGIN = "login"
    const val MY_HOUSES = "my_houses"

    const val CREATE_HOUSE = "create_house"
}

@Composable
fun AppNavHost(
    isDarkTheme: Boolean,
    onToggleTheme: () -> Unit,
    navController: NavHostController = rememberNavController()
) {

    var destinoAposLogin by rememberSaveable { mutableStateOf(AppRoutes.MY_HOUSES) }

    NavHost(
        navController = navController,
        startDestination = AppRoutes.ONBOARDING
    ){

        composable(AppRoutes.ONBOARDING) {

            OnboardingScreen(
                onMoradorClick = {
                    destinoAposLogin = AppRoutes.MY_HOUSES
                    navController.navigate(AppRoutes.LOGIN)
                },
                onCriarCasaClick = {
                    destinoAposLogin = AppRoutes.CREATE_HOUSE
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
                    navController.navigate(destinoAposLogin) {
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

        composable(AppRoutes.CREATE_HOUSE) {
            CreateHouseScreen(
                onVoltarClick = {
                    navController.navigate(AppRoutes.ONBOARDING){
                        popUpTo(AppRoutes.CREATE_HOUSE){
                            inclusive = true
                        }
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