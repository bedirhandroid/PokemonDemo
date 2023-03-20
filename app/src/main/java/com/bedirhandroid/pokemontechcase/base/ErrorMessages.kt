package com.bedirhandroid.pokemontechcase.base

import com.bedirhandroid.pokemontechcase.R

enum class ErrorMessages(val id : Int) {
    ERROR(R.string.error_message),
    UNKNOWN_ERROR(R.string.error_unknown),
    TIME_OUT(R.string.error_time_out),
    TRY_AGAIN(R.string.error_try_again),
    ERROR_EOFE(R.string.error_eofe),
    ERROR_WINDOW_MANAGER(R.string.error_window_manager)
}