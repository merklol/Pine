package com.madfrog.navigation

import androidx.navigation.navDeepLink

abstract class DeepLink(
    val uriPattern: String? = null,
    val action: String? = null,
    val mimeType: String? = null
) {

    override fun toString() = """
        DeepLink {
            uriPattern: $uriPattern,
            action: $action,
            mimeType: $mimeType,
        }
        """.trimIndent()
}

internal fun DeepLink.toNavDeepLink() = navDeepLink {
    uriPattern = this@toNavDeepLink.uriPattern
    action = this@toNavDeepLink.action
    mimeType = this@toNavDeepLink.mimeType
}

