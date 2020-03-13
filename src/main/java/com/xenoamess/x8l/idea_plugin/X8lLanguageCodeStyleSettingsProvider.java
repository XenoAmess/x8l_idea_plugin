//package com.xenoamess.x8l.idea_plugin;
//
//import com.intellij.lang.Language;
//import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable;
//import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider;
//import org.jetbrains.annotations.NotNull;
//
//import static com.xenoamess.x8l.idea_plugin.X8lDataCenter.DEMO_X8L_TEXT;
//
//public class X8lLanguageCodeStyleSettingsProvider extends LanguageCodeStyleSettingsProvider {
//    @NotNull
//    @Override
//    public Language getLanguage() {
//        return X8lLanguage.INSTANCE;
//    }
//
//    @Override
//    public void customizeSettings(@NotNull CodeStyleSettingsCustomizable consumer, @NotNull SettingsType
//    settingsType) {
////        if (settingsType == SettingsType.SPACING_SETTINGS) {
////            consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS");
////            consumer.renameStandardOption("SPACE_AROUND_ASSIGNMENT_OPERATORS", "Separator");
////        } else if (settingsType == SettingsType.BLANK_LINES_SETTINGS) {
////            consumer.showStandardOptions("KEEP_BLANK_LINES_IN_CODE");
////        }
//    }
//
//    @Override
//    public String getCodeSample(@NotNull SettingsType settingsType) {
//        return DEMO_X8L_TEXT;
//    }
//}
