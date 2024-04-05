package org.d3if0142.myfood.ui.screen

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0142.myfood.R
import org.d3if0142.myfood.navigation.Screen
import org.d3if0142.myfood.ui.theme.MyfoodTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.About.route) }) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.tentang_aplikasi),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { padding ->
        ScreenContent(Modifier.padding(padding))
    }
}

@Composable
fun ScreenContent(modifier: Modifier) {
    var umur by rememberSaveable { mutableStateOf("") }
    var umurError by rememberSaveable { mutableStateOf(false) }

    var pagi by rememberSaveable { mutableStateOf("") }
    var pagiError by rememberSaveable { mutableStateOf(false) }

    var siang by rememberSaveable { mutableStateOf("") }
    var siangError by rememberSaveable { mutableStateOf(false) }

    var malam by rememberSaveable { mutableStateOf("") }
    var malamError by rememberSaveable { mutableStateOf(false) }

    val radioOptions = listOf(
        stringResource(id = R.string.pria),
        stringResource(id = R.string.wanita)
    )
    var gender by rememberSaveable { mutableStateOf(radioOptions[0]) }

    var kalori by rememberSaveable { mutableIntStateOf(0)  }
    var kategori by rememberSaveable { mutableIntStateOf(0) }

    val context = LocalContext.current

    Column(
        modifier = modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.myfood_intro),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = umur,
            onValueChange = { umur = it },
            label = { Text(text = stringResource(R.string.umur)) },
            isError = umurError,
            trailingIcon = { IconPicker(umurError, "years") },
            supportingText = { ErrorHint(umurError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = pagi,
            onValueChange = { pagi = it },
            label = { Text(text = stringResource(R.string.makan_pagi)) },
            isError = pagiError,
            trailingIcon = { IconPicker(pagiError, "cal") },
            supportingText = { ErrorHint(pagiError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = siang,
            onValueChange = { siang = it },
            label = { Text(text = stringResource(R.string.makan_siang)) },
            isError = siangError,
            trailingIcon = { IconPicker(siangError, "cal") },
            supportingText = { ErrorHint(siangError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = malam,
            onValueChange = { malam = it },
            label = { Text(text = stringResource(R.string.makan_malam)) },
            isError = malamError,
            trailingIcon = { IconPicker(malamError, "cal") },
            supportingText = { ErrorHint(malamError) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Row(
            modifier = Modifier
                .padding(top = 6.dp)
                .border(1.dp, Color.Gray, RoundedCornerShape(4.dp))
        ) {
            radioOptions.forEach { text ->
                GenderOption(
                    label = text,
                    isSelected = gender == text,
                    modifier = Modifier
                        .selectable(
                            selected = gender == text,
                            onClick = { gender = text },
                            role = Role.RadioButton
                        )
                        .weight(1f)
                        .padding(16.dp)
                )
            }
        }
        Button(
            onClick = {
                umurError = (umur == "" || umur == "0")
                pagiError = (pagi == "" || pagi == "0")
                siangError = (siang == "" || siang == "0")
                malamError = (malam == "" || malam == "0")
                if (umurError || pagiError || siangError || malamError ) return@Button

                kalori = hitungKal(pagi.toInt(), siang.toInt(), malam.toInt())
                kategori = getKategori(kalori, gender == radioOptions[0], umur.toInt())
            },
            modifier = Modifier.padding(top = 8.dp),
            contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)
        ) {
            Text(text = stringResource(R.string.hitung))
        }

            if (kalori != 0) {
                Divider(
                    modifier = Modifier.padding(vertical = 8.dp),
                    thickness = 1.dp
                )
                Text(
                    text = stringResource(R.string.kal_x, kalori),
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = stringResource(kategori).uppercase(),
                    style = MaterialTheme.typography.headlineLarge
                )
                Button(
                    onClick = {
                        shareData(
                            context = context,
                            message = context.getString(R.string.bagikan_template,
                                umur, pagi, gender, kalori,
                                context.getString(kategori).uppercase())
                        )
                    },
                    modifier = Modifier.padding(top = 8.dp),
                    contentPadding = PaddingValues(horizontal=32.dp, vertical=16.dp)
                ) {
                    Text(text = stringResource(R.string.bagikan))
                }
        }
    }
}

    @Composable
    fun GenderOption(label: String, isSelected: Boolean, modifier: Modifier) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(selected = isSelected, onClick = null)
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }

    @Composable
    fun IconPicker(isError: Boolean, unit: String) {
        if (isError) {
            Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
        } else {
            Text(text = unit)
        }
    }

    @Composable
    fun ErrorHint(isError: Boolean) {
        if (isError) {
            Text(text = stringResource(R.string.input_invalid))
        }
    }

private fun hitungKal(pagi: Int, siang: Int, malam: Int): Int {
    return pagi + siang + malam
}

private fun getKategori(kalori: Int, isMale: Boolean, umur: Int): Int {
    return if (umur < 10) {
        when {
            umur in 1..3 -> {
                when {
                    kalori <= 1080 -> R.string.kurang
                    kalori in 1081..1619 -> R.string.ideal
                    else -> R.string.lebih
                }
            }
            umur in 4..6 -> {
                when {
                    kalori <= 1120 -> R.string.kurang
                    kalori in 1121..1679 -> R.string.ideal
                    else -> R.string.lebih
                }
            }
            umur in 7..9 -> {
                when {
                    kalori <= 1320 -> R.string.kurang
                    kalori in 1321..1979 -> R.string.ideal
                    else -> R.string.lebih
                }
            }
            else -> R.string.unknown
        }
    } else {
        when {
            isMale -> {
                when {
                    umur in 10..12 -> {
                        when {
                            kalori <= 1600 -> R.string.kurang
                            kalori in 1601..2399 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 13..15 -> {
                        when {
                            kalori <= 1920 -> R.string.kurang
                            kalori in 1921..2879 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 16..29 -> {
                        when {
                            kalori <= 2120 -> R.string.kurang
                            kalori in 2121..3179 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 30..49 -> {
                        when {
                            kalori <= 2040 -> R.string.kurang
                            kalori in 2041..3059 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 50..64 -> {
                        when {
                            kalori <= 1720 -> R.string.kurang
                            kalori in 1721..2579 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 65..80 -> {
                        when {
                            kalori <= 1440 -> R.string.kurang
                            kalori in 1441..2159 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur > 80 -> {
                        when {
                            kalori <= 1280 -> R.string.kurang
                            kalori in 1281..1919 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    else -> R.string.unknown
                }
            }
            !isMale -> {
                when {
                    umur in 10..12 -> {
                        when {
                            kalori <= 1520 -> R.string.kurang
                            kalori in 1521..2279 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 13..15 -> {
                        when {
                            kalori <= 1640 -> R.string.kurang
                            kalori in 1641..2459 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 16..18 -> {
                        when {
                            kalori <= 1680 -> R.string.kurang
                            kalori in 1681..2519 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 19..29 -> {
                        when {
                            kalori <= 1800 -> R.string.kurang
                            kalori in 1801..2699 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 30..49 -> {
                        when {
                            kalori <= 1720 -> R.string.kurang
                            kalori in 1721..2579 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 50..64 -> {
                        when {
                            kalori <= 1440 -> R.string.kurang
                            kalori in 1441..2159 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur in 65..80 -> {
                        when {
                            kalori <= 1240 -> R.string.kurang
                            kalori in 1241..1859 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    umur > 80 -> {
                        when {
                            kalori <= 1120 -> R.string.kurang
                            kalori in 1121..1679 -> R.string.ideal
                            else -> R.string.lebih
                        }
                    }
                    else -> R.string.unknown
                }
            }
            else -> R.string.unknown
        }
    }
}

    private fun shareData(context: Context, message: String) {
        val shareIntent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, message)
        }
        if (shareIntent.resolveActivity(context.packageManager) != null) {
            context.startActivity(shareIntent)
        }
    }

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    MyfoodTheme {
        MainScreen(rememberNavController())
    }
}