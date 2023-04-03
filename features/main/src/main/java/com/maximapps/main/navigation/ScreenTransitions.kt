package com.maximapps.main.navigation

import com.madfrog.navigation.accompanist.AccompanistTransitions
import com.madfrog.navigation.accompanist.emptyEnterTransition
import com.madfrog.navigation.accompanist.emptyExitTransition
import com.madfrog.navigation.accompanist.slideInTransition
import com.madfrog.navigation.accompanist.slideLeftTransition
import com.madfrog.navigation.accompanist.slideOutTransition
import com.madfrog.navigation.accompanist.slideRightTransition

class ScreenTransitions : AccompanistTransitions(emptyEnterTransition(), slideOutTransition())
class ScreenPopTransitions : AccompanistTransitions(slideInTransition(), emptyExitTransition())