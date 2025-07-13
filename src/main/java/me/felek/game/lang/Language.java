package me.felek.game.lang;

public enum Language {
    en_US("en_US"),
    ru_RU("ru_RU");

    private String language;
    private Language(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
