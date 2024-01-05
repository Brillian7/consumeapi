package com.example.consumeapi.ui.Kontakk.screen

import android.app.UiAutomation.OnAccessibilityEventListener
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumeapi.model.Kontak
import com.example.consumeapi.navigation.DestinasiNavigasi
import com.example.consumeapi.ui.DestinasiHome
import com.example.consumeapi.ui.Kontakk.viewmodel.DetailsKontakUiState
import com.example.consumeapi.ui.Kontakk.viewmodel.DetailsViewModel
import com.example.consumeapi.ui.PenyediaViewModel
import com.example.consumeapi.ui.TopAppBarKontak
import com.example.consumeapi.ui.home.screen.OnError
import com.example.consumeapi.ui.home.screen.OnLoading

object DetailsDestination : DestinasiNavigasi{
    override val route = "item_details"
    override val titleRes =  "Detail Kontak"
    const val kontakId = "itemId"
    val routeWhithArgs = "$route/{$kontakId}"
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    onEditClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit,
    detailsViewModel: DetailsViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes ,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack,

                )
        },
    ) { innerPadding ->

        DetailStatus(kontakUiState = detailsViewModel.detailsKontakUiState,
            retryAction = {
                          detailsViewModel.getKontakById()
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            onEditClick = onEditClick
        )
    }
}

@Composable
fun DetailStatus(
    kontakUiState: DetailsKontakUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onEditClick: (Int) -> Unit
){
    when (kontakUiState){
        is DetailsKontakUiState.Success -> {
            KontakLayout(
                kontak = kontakUiState.kontak,
                modifier = modifier.padding(16.dp),
                onEditClick = {
                    onEditClick(it)
                }
            )
        }
        is DetailsKontakUiState.Loading -> {
            OnLoading(modifier = modifier)
        }

        is  DetailsKontakUiState.Error -> {
            OnError(retryAction = retryAction, modifier = modifier)
        }
    }
}
@Composable
fun KontakLayout(
    kontak: Kontak,
    modifier: Modifier = Modifier,
    onEditClick: (Int) -> Unit = {},
){
    Column (
        modifier = modifier,
    ){
        KontakCard(kontak = kontak, modifier = Modifier
            .fillMaxWidth(),
            )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(onClick = { onEditClick(kontak.id)
        },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Edit")
        }
    }
}


@Composable
fun  KontakCard(
    kontak: Kontak,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ){
        Column (
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
            ){
                Text(
                    text = kontak.nama,
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(modifier.weight(1f))
                Icon(
                    imageVector = Icons.Default.Phone,
                    contentDescription = null,
                )
                Text(
                    text = kontak.nohp,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = kontak.email,
                    style = MaterialTheme.typography.titleMedium,
                )
                Spacer(modifier.weight(1f))
            }
        }
    }
}
