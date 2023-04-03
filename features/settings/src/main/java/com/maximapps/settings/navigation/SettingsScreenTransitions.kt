package com.maximapps.settings.navigation

import com.madfrog.navigation.accompanist.AccompanistTransitions
import com.madfrog.navigation.accompanist.emptyExitTransition
import com.madfrog.navigation.accompanist.slideDownTransition
import com.madfrog.navigation.accompanist.slideInTransition
import com.madfrog.navigation.accompanist.slideLeftTransition
import com.madfrog.navigation.accompanist.slideOutTransition

class SettingsScreenTransitions : AccompanistTransitions(slideDownTransition(), emptyExitTransition())