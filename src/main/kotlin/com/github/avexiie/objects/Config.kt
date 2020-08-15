package com.github.avexiie.objects

import com.github.avexiie.CloudEssentials
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

object Config {

    private lateinit var plugin: CloudEssentials

    lateinit var prefix: String
    lateinit var messagesFile: File
    lateinit var messagesData: FileConfiguration
    lateinit var whitelistFile: File
    lateinit var whitelistData: FileConfiguration
    lateinit var slotsFile: File
    lateinit var slotsData: FileConfiguration

    var trollEnabled: Boolean = true

    fun init(plugin: CloudEssentials) {
        this.plugin = plugin

        messagesFile = File(plugin.dataFolder, "messages.yml")
        whitelistFile = File(plugin.dataFolder, "whitelist.yml")
        slotsFile = File(plugin.dataFolder, "slots.yml")

        if (!plugin.dataFolder.exists()) {
            plugin.dataFolder.mkdirs()
            plugin.saveResource("messages.yml", false)
            plugin.saveResource("whitelist.yml", false)
            plugin.saveResource("slots.yml", false)
        }

        messagesData = YamlConfiguration.loadConfiguration(messagesFile)
        whitelistData = YamlConfiguration.loadConfiguration(whitelistFile)
        slotsData = YamlConfiguration.loadConfiguration(slotsFile)

        Bukkit.getScheduler().runTaskLater(plugin, {
            reloadAll()
        }, 1L)

    }

    fun reloadAll() {

        reloadConfig()
        reloadMessages()
        reloadSlots()
        reloadWhitelist()

    }

    private fun reloadConfig() {

        val configFile = File(plugin.dataFolder, "config.yml")

        if (!configFile.exists())
            plugin.saveResource("config.yml", false)

        plugin.reloadConfig()

        trollEnabled = plugin.config.getBoolean("troll-features", true)

    }

    fun reloadMessages() {

        messagesFile = File(plugin.dataFolder, "messages.yml")

        if (!messagesFile.exists())
            plugin.saveResource("messages.yml", false)

        messagesData = YamlConfiguration.loadConfiguration(messagesFile)

        prefix = ChatColor.translateAlternateColorCodes(
            '&',
            messagesData.getString("prefix", "&e&lCloudEssentials &a/")
        )

    }

    fun reloadSlots() {

        Slots.convert()

        slotsFile = File(plugin.dataFolder, "slots.yml")

        if (!slotsFile.exists())
            plugin.saveResource("slots.yml", false)

        slotsData = YamlConfiguration.loadConfiguration(slotsFile)

        Slots.enabled = slotsData.getBoolean("slots.enabled")
        Slots.fullMessage = slotsData.getString("slots.kick_message")
        Slots.amount = slotsData.getInt("slots.max_player")

    }

    fun reloadWhitelist() {

        whitelistFile = File(plugin.dataFolder, "whitelist.yml")

        if (!whitelistFile.exists())
            plugin.saveResource("whitelist.yml", false)

        whitelistData = YamlConfiguration.loadConfiguration(whitelistFile)

        Whitelist.enabled = whitelistData.getBoolean("whitelist.enabled")
        Whitelist.kickMessage = whitelistData.getString("whitelist.kick_message")
        Whitelist.whitelistCache = whitelistData.getStringList("whitelist.whitelisted") as ArrayList<String>

    }

}