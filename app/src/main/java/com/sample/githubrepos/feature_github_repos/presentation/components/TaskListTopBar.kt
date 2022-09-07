package com.sample.githubrepos.feature_github_repos.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Filter
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
@Preview
fun GitHubReposListTopBar(
    modifier: Modifier = Modifier
){


    ConstraintLayout (
        modifier = modifier
            .fillMaxWidth(),
    ){
        val (title) = createRefs()

        Text(
            text = "GitHub Repositories",
            fontSize = 20.sp,
            color = MaterialTheme.colors.primaryVariant,
            modifier = Modifier
                .constrainAs(title) {
                    start.linkTo(parent.start)
                }
                .padding(16.dp),
            fontWeight = FontWeight(600)
        )

    }
}