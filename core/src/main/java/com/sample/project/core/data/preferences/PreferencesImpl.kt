package com.sample.project.core.data.preferences

import android.content.SharedPreferences
import com.sample.project.core.domain.preferences.Preferences

class PreferencesImpl(private val preferences: SharedPreferences) : Preferences {
    override fun saveShouldShowOnboarding(shouldShow: Boolean) {
        preferences.edit()
            .putBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, shouldShow)
            .apply()
    }

    override fun loadShouldShowOnboarding(): Boolean = preferences
        .getBoolean(Preferences.KEY_SHOULD_SHOW_ONBOARDING, true)
}
