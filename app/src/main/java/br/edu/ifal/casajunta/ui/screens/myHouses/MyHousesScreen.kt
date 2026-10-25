import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable

import br.edu.ifal.casajunta.model.casasMock

@Composable
fun myHouses(
    onEntrarCasaClick: () -> Unit
){

    Column{
        Text(
            text = "Minhas casas"
        )

        Text(
            text = casasMock[0].nome
        )
    }
}