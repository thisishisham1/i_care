package com.example.icare.core.util.reusablecomponent

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.icare.core.util.Dimens
import com.example.icare.domain.model.enums.ErrorInfo
import com.example.icare.domain.model.enums.ErrorTypes
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ErrorComposable(errorState: StateFlow<ErrorInfo?>) {
    val errorInfo by errorState.collectAsState(null)
    errorInfo?.let { info ->
        Text(
            text = stringResource(id = info.errorType.errorMessage),
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(horizontal = Dimens.mediumPadding)
        )
    }
}
