package com.maximapps.pine.navigation

import com.madfrog.navigation.accompanist.AccompanistTransitions
import com.madfrog.navigation.accompanist.emptyEnterTransition
import com.madfrog.navigation.accompanist.emptyExitTransition
import com.madfrog.navigation.accompanist.slideLeftTransition
import com.madfrog.navigation.accompanist.slideRightTransition

class ScreenTransitions : AccompanistTransitions(emptyEnterTransition(), slideRightTransition())
class ScreenPopTransitions : AccompanistTransitions(slideLeftTransition(), emptyExitTransition())