package com.xenoamess.x8l.idea_plugin;

import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

public class X8lDataCenter {
    public static final String X8L_LANGUAGE_EXTENSION = "x8l";
    public static final String X8L_LANGUAGE_NAME = "X8l";
    public static final Icon X8L_LANGUAGE_ICON = IconLoader.getIcon("/icons/x8l_language_icon.svg");
    public static final String DEMO_TEXT = "<settingFile settingFormatVersion=0.3.0>\n" +
            "    <commonSettings\n" +
            "        gameName=CyanPotionRpgModuleDemo\n" +
            "        gameVersion=0.161.4\n" +
            "        titleTextID=0\n" +
            "        textFilePath=resources/text/text.x8l\n" +
            "        defaultFontResourceURI=[\"com.xenoamess.cyan_potion.base.visual.Font\",\"ttfFile\",\"resources/www/fonts/SourceHanSans-Normal.ttc\"]\n" +
            "        language=english\n" +
            "        gameWindowClassName=com.xenoamess.cyan_potion.base.GameWindow\n" +
            "        titleClassName=com.xenoamess.cyan_potion.demo.world.WorldForDemo\n" +
            "        steam_appid=480\n" +
            "        runWithSteam=1\n" +
            "        maxFPS=-1\n" +
            "    >>\n" +
            "    <views\n" +
            "        logicWindowWidth=1280\n" +
            "        logicWindowHeight=1024\n" +
            "        realWindowWidth=1280\n" +
            "        realWindowHeight=1024\n" +
            "        scale=2.0\n" +
            "        fullScreen=0\n" +
            "        gameWindowResizable=1\n" +
            "    >>\n" +
            "    <specialSettings>>\n" +
            "    <codePlugins [>\n" +
            "        <rightAfterResourceManagerCreate>SimpleFunctionObject:com.xenoamess.cyan_potion.base.render.Texture:PUT_TEXTURE_LOADERS>\n" +
            "        <rightAfterResourceManagerCreate>SimpleFunctionObject:com.xenoamess.cyan_potion.base.audio.WaveData:PUT_WAVEDATA_LOADER_MUSIC>\n" +
            "        <rightAfterResourceManagerCreate>SimpleFunctionObject:com.xenoamess.cyan_potion.base.visual.Font:PUT_FONT_LOADER_TTF_FILE>\n" +
            "        <rightAfterResourceManagerCreate>SimpleFunctionObject:com.xenoamess.cyan_potion.rpg_module.plugins.CodePlugins:PLUGIN_RPG_MODULE_TEXTURE_LOADERS>\n" +
            "        <rightAfterResourceManagerCreate>SimpleFunctionObject:com.xenoamess.cyan_potion.base.steam.SteamTextureUtils:PUT_TEXTURE_LOADER_STEAM_AVATAR>\n" +
            "    >\n" +
            "    <debug=1>>\n" +
            "    <keymap using>\n" +
            "        <GLFW_KEY_W>XENOAMESS_KEY_UP>\n" +
            "        <GLFW_KEY_A>XENOAMESS_KEY_LEFT>\n" +
            "        <GLFW_KEY_S>XENOAMESS_KEY_DOWN>\n" +
            "        <GLFW_KEY_D>XENOAMESS_KEY_RIGHT>\n" +
            "        <GLFW_KEY_UP>XENOAMESS_KEY_UP>\n" +
            "        <GLFW_KEY_LEFT>XENOAMESS_KEY_LEFT>\n" +
            "        <GLFW_KEY_DOWN>XENOAMESS_KEY_DOWN>\n" +
            "        <GLFW_KEY_RIGHT>XENOAMESS_KEY_RIGHT>\n" +
            "        <GLFW_KEY_ESCAPE>XENOAMESS_KEY_ESCAPE>\n" +
            "        <GLFW_KEY_ENTER>XENOAMESS_KEY_ENTER>\n" +
            "        <GLFW_KEY_SPACE>XENOAMESS_KEY_SPACE>\n" +
            "        <GLFW_KEY_LEFT_SHIFT>XENOAMESS_KEY_LEFT_SHIFT>\n" +
            "        <GLFW_KEY_RIGHT_SHIFT>XENOAMESS_KEY_RIGHT_SHIFT>\n" +
            "        <GLFW_MOUSE_BUTTON_LEFT>XENOAMESS_MOUSE_BUTTON_LEFT>\n" +
            "        <GLFW_MOUSE_BUTTON_RIGHT>XENOAMESS_MOUSE_BUTTON_RIGHT>\n" +
            "        <GLFW_MOUSE_BUTTON_MIDDLE>XENOAMESS_MOUSE_BUTTON_MIDDLE>\n" +
            "\n" +
            "        <JXINPUT_KEY_UP>XENOAMESS_KEY_UP>\n" +
            "        <JXINPUT_KEY_LEFT>XENOAMESS_KEY_LEFT>\n" +
            "        <JXINPUT_KEY_DOWN>XENOAMESS_KEY_DOWN>\n" +
            "        <JXINPUT_KEY_RIGHT>XENOAMESS_KEY_RIGHT>\n" +
            "        <JAMEPAD_KEY_UP>XENOAMESS_KEY_UP>\n" +
            "        <JAMEPAD_KEY_LEFT>XENOAMESS_KEY_LEFT>\n" +
            "        <JAMEPAD_KEY_DOWN>XENOAMESS_KEY_DOWN>\n" +
            "        <JAMEPAD_KEY_RIGHT>XENOAMESS_KEY_RIGHT>\n" +
            "    >\n" +
            "    <<comment>\n" +
            ">\n";
}
