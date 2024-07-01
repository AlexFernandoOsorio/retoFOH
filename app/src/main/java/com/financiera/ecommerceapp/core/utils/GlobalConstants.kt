package com.financiera.ecommerceapp.core.utils

import com.financiera.ecommerceapp.BuildConfig


object GlobalConstants {

    //constantes para el apiservice
    const val BASE_URL = BuildConfig.BASE_URL
    const val POSTER_URL = BuildConfig.BASE_URL_IMAGE
    const val END_POINT_MOVIES = BuildConfig.END_POINT_MOVIES
    const val END_POINT_CANDYS = BuildConfig.END_POINT_CANDYS
    const val END_POINT_TRANSACTION = BuildConfig.END_POINT_TRANSACTION
    const val END_POINT_COMPLETE = BuildConfig.END_POINT_COMPLETE

    //constantes para mensajes de error
    const val noInternet = "No hay conexión a internet"
    const val noSavedMovies = "No tienes Favoritos guardados"
    const val noSearchMovies =  "No se encontraron resultados para: "

    //constantes para repositorios
    const val errorNoUser = "Usuario y/o contraseña incorrectos"
}
