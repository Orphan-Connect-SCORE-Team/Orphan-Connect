package edu.gatech.score.orphanconnect.preferences

interface ScoreSharedPreferences {
    var auth_token: String?
    var user_id: String?
    var email: String?
    var first_name: String?
    var last_name: String?
    var photo_id: String?
    var amount_donated: Double
}
