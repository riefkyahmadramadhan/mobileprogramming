package org.d3if0142.mobpro1.navigation

import org.d3if0142.mobpro1.ui.screen.KEY_ID_CATATAN

sealed class Screen (val route: String){
    data object Home: Screen("mainscreen")
    data object FormBaru: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_CATATAN}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
}