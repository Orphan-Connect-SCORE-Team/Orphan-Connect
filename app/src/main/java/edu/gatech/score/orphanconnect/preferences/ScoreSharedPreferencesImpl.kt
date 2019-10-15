package edu.gatech.score.orphanconnect.preferences

import android.app.Application
import android.content.Context
import edu.gatech.score.orphanconnect.utils.Constants
import edu.gatech.score.orphanconnect.utils.extentions.getDouble
import edu.gatech.score.orphanconnect.utils.extentions.putDouble

class ScoreSharedPreferencesImpl(application: Application) : ScoreSharedPreferences {
    private val preferences = application.getSharedPreferences(Constants.Preferences.SHARED_PREFERENCES_ID, Context.MODE_PRIVATE)

    companion object {
        const val AUTH_TOKEN_KEY = "auth_token_key"
        const val USER_ID_KEY = "user_id_key"
        const val EMAIL_KEY = "email_key"
        const val FIRST_NAME_KEY = "first_name_key"
        const val LAST_NAME_KEY = "last_name_key"
        const val PHOTO_ID_KEY = "photo_id_key"
        const val AMOUNT_DONATED_KEY = "amount_donated_key"
    }

    override var auth_token: String?
        get() = preferences.getString(AUTH_TOKEN_KEY, null)
        set(value) = preferences.edit().putString(AUTH_TOKEN_KEY, value).apply()

    override var user_id: String?
        get() = preferences.getString(USER_ID_KEY, null)
        set(value) = preferences.edit().putString(USER_ID_KEY, value).apply()

    override var email: String?
        get() = preferences.getString(EMAIL_KEY, null)
        set(value) = preferences.edit().putString(EMAIL_KEY, value).apply()

    override var first_name: String?
        get() = preferences.getString(FIRST_NAME_KEY, null)
        set(value) = preferences.edit().putString(FIRST_NAME_KEY, value).apply()

    override var last_name: String?
        get() = preferences.getString(LAST_NAME_KEY, null)
        set(value) = preferences.edit().putString(LAST_NAME_KEY, value).apply()

    override var photo_id: String?
        get() = preferences.getString(PHOTO_ID_KEY, null)
        set(value) = preferences.edit().putString(PHOTO_ID_KEY, value).apply()

    override var amount_donated: Double
        get() = preferences.getDouble(AMOUNT_DONATED_KEY, 0.0)
        set(value) = preferences.edit().putDouble(AMOUNT_DONATED_KEY, value).apply()

}
