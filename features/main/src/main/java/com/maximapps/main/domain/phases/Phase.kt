package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session

interface Phase {
    fun proceed(state: Session): Session
}
