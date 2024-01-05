package com.example.consumeapi.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumeapi.navigation.DestinasiNavigasi
import com.example.consumeapi.ui.home.screen.HomeStatus
import com.example.consumeapi.ui.home.viewmodel.HomeViewModel

object DestinasiHome : DestinasiNavigasi {
        override val route = "home"
    override val titleRes = "Kontak"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory=PenyediaViewModel.Factory)
){
    val  scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    
    Scaffold (
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes,
                canNavigateBack = false,
                scrollBehavior= scrollBehavior,
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon (
                   imageVector = Icons.Default.Add,
                    contentDescription = "Add Kontak"
                )
            }
        },
    ){innerPadding ->
        HomeStatus(
            kontakUIState = viewModel.kontakUIState,
            retryAction = {
                viewModel.getKontak()
            },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKontak(it.id)
                viewModel.getKontak()
            }
        )
        
    }
}
