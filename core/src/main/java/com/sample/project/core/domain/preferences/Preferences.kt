package com.sample.project.core.domain.preferences

interface Preferences {
    /**
     * Save new state when onaboarding was finished
     * @param shouldShow The `boolean` key. `false` - when onboarding finished.
     */
    fun saveShouldShowOnboarding(shouldShow: Boolean)

    /**
     * Get actual state to know if onboarding was finished.
     */
    fun loadShouldShowOnboarding(): Boolean

    companion object {
        const val KEY_SHOULD_SHOW_ONBOARDING = "should_show_onboarding"
    }
}
