package com.sample.project.testing.data.preferences

import com.sample.project.core.domain.preferences.Preferences

class TestPreferences : Preferences {

    private var shouldShowOnboarding: Boolean = true

    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        shouldShowOnboarding = shouldShow
    }

    override fun loadShouldShowOnboarding(): Boolean {
        return shouldShowOnboarding
    }
}
