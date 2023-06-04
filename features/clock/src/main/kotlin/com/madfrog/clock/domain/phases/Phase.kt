package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session

interface Phase {
    suspend fun proceed(state: Session): Session
}
