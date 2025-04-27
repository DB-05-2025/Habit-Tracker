package com.acevy.habit_tracker.ui.screens.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.acevy.habit_tracker.ui.components.buttons.ButtonPrimary
import com.acevy.habit_tracker.ui.components.forminputs.TextInputField
import com.acevy.habit_tracker.ui.theme.AppColors
import com.acevy.habit_tracker.ui.theme.AppShapes
import com.acevy.habit_tracker.ui.theme.AppType

@Composable
fun GetStartedScreen(
    onSubmit: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var name by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Kenalan Dulu Yuk!",
            style = AppType.bold20,
            color = AppColors.GrayDark,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TextInputField(
            value = name,
            onValueChange = { name = it },
            label = "Nama Kamu",
            placeholder = "Masukkan nama kamu",
        )

        AsyncImage(
            model = "https://ofcnpgzapkplcbfngucb.supabase.co/storage/v1/object/sign/habit-tracker/onboarding-4.png?token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6InN0b3JhZ2UtdXJsLXNpZ25pbmcta2V5XzFmMGNmZDEyLTQzNTktNDZlOS1iODRiLTMyYmViZDFhODY3ZiJ9.eyJ1cmwiOiJoYWJpdC10cmFja2VyL29uYm9hcmRpbmctNC5wbmciLCJpYXQiOjE3NDU2OTA5OTMsImV4cCI6MTc3NzIyNjk5M30.mYtEZLsynInPxCoDjCrtzh45Whb-lHcjSmHHVEPlJOY",
            contentDescription = "Get Started Illustration",
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 24.dp)
        )

        ButtonPrimary(
            text = "Continue",
            onClick = { if (name.isNotBlank()) onSubmit(name) },
            fullWidth = true
        )
    }
}