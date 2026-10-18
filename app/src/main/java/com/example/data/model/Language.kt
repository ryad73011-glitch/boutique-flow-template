package com.example.data.model

enum class AppLanguage(val code: String, val label: String, val nativeName: String) {
  FRENCH("fr", "Français", "Français"),
  ARABIC("ar", "العربية", "العربية");

  val isRtl: Boolean
    get() = this == ARABIC
}
