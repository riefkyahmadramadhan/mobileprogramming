package org.d3if0142.mobpro1.ui.screen

import androidx.lifecycle.ViewModel
import org.d3if0142.mobpro1.model.Catatan

class DetailViewModel : ViewModel() {

    fun getCatatan(id: Long): Catatan {
        return Catatan(
            id,
            "Kuliah Mobpro $id Feb",
            "Yey, hari ini belajar membuat aplikasi Android counter dan berhasil. Hehe.. Mudah2an modul selanjutnya juga lancar. Aamiin.",
            "2024-02-$id 12:34:56"
        )
    }
}