package com.example.consumeapi.ui.Kontakk.screen

import android.content.ClipData.Item
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.consumeapi.navigation.DestinasiNavigasi
import com.example.consumeapi.ui.DestinasiHome
import com.example.consumeapi.ui.Kontakk.viewmodel.EditViewModel
import com.example.consumeapi.ui.PenyediaViewModel
import com.example.consumeapi.ui.TopAppBarKontak
import kotlinx.coroutines.launch

object EditDestination : DestinasiNavigasi{
    override val route = "edit"
    override val titleRes = "Edit Kontak"
    const val kontakId = "itemId"
    val routeWithArgs = "$route/{$kontakId}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEditScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditViewModel = viewModel(factory = PenyediaViewModel.Factory)
){
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBarKontak(
                title = DestinasiHome.titleRes ,
                canNavigateBack = true,
                navigateUp = navigateBack,
                )
        },
        modifier = modifier
    ){innerPadding ->
        EntryKontakBody(
            insertUiState = viewModel.editKontakState,
            onsiswaValueChange = viewModel::updateInsertKontakState ,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKontak()
                    onNavigateUp()
                }
            },
            modifier = Modifier.padding(innerPadding)
        )
    }
}