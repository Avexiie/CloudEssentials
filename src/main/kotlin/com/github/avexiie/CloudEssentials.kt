package com.github.avexiie

import com.github.avexiie.commands.CloudEssentialsCMD
import com.github.avexiie.commands.features.*
import com.github.avexiie.commands.features.trolls.*
import com.github.avexiie.listeners.PlayerListeners
import com.github.avexiie.listeners.trolls.TrollPlayerListeners
import com.github.avexiie.lyrams.objects.Config
import com.github.avexiie.objects.Slots
import com.github.avexiie.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class CloudEssentials : JavaPlugin() {

    companion object {

        lateinit var instance: CloudEssentials

    }


    override fun onEnable() {

        if (Bukkit.getPluginManager().getPlugin("LuckyInjector") == null)
            Bukkit.getLogger().warning("LuckyInjector not found! Plugin might not load!")

        instance = this

        Config.init(this)

        registerCommands()
        registerListeners()

        Bukkit.getScheduler().runTaskLater(this, {
            Whitelist.reload()
            Slots.convert()
            Slots.reload()
        }, 1L)

    }

    override fun onDisable() {

        Bukkit.getScheduler().cancelTasks(this)

    }

    private fun registerCommands() {

        getCommand("cloudessentials").executor = CloudEssentialsCMD()

        getCommand("editsign").executor = EditSignCMD()
        getCommand("enchant").executor = EnchantCMD()
        getCommand("ewhitelist").executor = EWhitelistCMD()
        getCommand("explode").executor = ExplodeCMD()
        getCommand("fakeplace").executor = FakePlaceCMD()
        getCommand("feed").executor = FeedCMD()
        getCommand("fix").executor = FixCMD()
        getCommand("fly").executor = FlyCMD()
        getCommand("getpos").executor = GetPosCMD()
        getCommand("god").executor = GodCMD()
        getCommand("heal").executor = HealCMD()
        getCommand("invsee").executor = InvseeCMD()
        getCommand("kickall").executor = KickAllCMD()
        getCommand("more").executor = MoreCMD()
        getCommand("nobreak").executor = NoBreakCMD()
        getCommand("nodamage").executor = NoDamageCMD()
        getCommand("nohit").executor = NoHitCMD()
        getCommand("nointeract").executor = NoInteractCMD()
        getCommand("nopickup").executor = NoPickupCMD()
        getCommand("noplace").executor = NoPlaceCMD()
        getCommand("onetap").executor = OneTapCMD()
        getCommand("slots").executor = SlotsCMD()
        getCommand("smite").executor = SmiteCMD()
        getCommand("speed").executor = SpeedCMD()
        getCommand("sudo").executor = SudoCMD()
        getCommand("teleport").executor = TeleportCMD()
        getCommand("top").executor = TopCMD()
        getCommand("troll").executor = TrollCMD()

    }

    private fun registerListeners() {

        val pluginManager = server.pluginManager

        pluginManager.registerEvents(PlayerListeners(), this)
        pluginManager.registerEvents(TrollPlayerListeners(), this)

    }

}