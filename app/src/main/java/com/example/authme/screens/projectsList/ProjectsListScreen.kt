package com.example.authme.screens.projectsList

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import com.example.authme.R
import com.example.authme.data.ProjectsListData
import com.example.authme.data.ProjectsListModel

@Composable
fun ProjectsListScreen(
    onProjectHandler: (projectType: Int) -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            stringResource(R.string.list_of_available_projects),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 20.dp)
        )
        ProjectsList(
            ProjectsListData,
            modifier = Modifier.fillMaxSize(),
            onProjectHandler = onProjectHandler
        )
    }
}

@Composable
fun ProjectsList(
    projectsList: List<ProjectsListModel>,
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    onProjectHandler: (projectType: Int) -> Unit = {}
) {
    LazyColumn(modifier = modifier, state = listState) {
        items(projectsList) { projectItem ->
            Button(
                onClick = { onProjectHandler(projectItem.type) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = projectItem.name)
            }
        }
    }
}