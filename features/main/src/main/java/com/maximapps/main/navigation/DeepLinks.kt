package com.maximapps.main.navigation

import android.content.Intent
import com.madfrog.navigation.DeepLink

object MainDeepLink : DeepLink(uriPattern = "https://maximapps.pine.com/main", Intent.ACTION_VIEW)
