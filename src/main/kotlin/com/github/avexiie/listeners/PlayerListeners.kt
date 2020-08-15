package com.github.avexiie.listeners

import com.github.avexiie.CloudEssentials
import com.github.avexiie.extensions.checkPermissionSilent
import com.github.avexiie.extensions.removeMetadata
import com.github.avexiie.objects.Slots
import com.github.avexiie.objects.Whitelist
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.EventPriority
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByBlockEvent
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryType
import org.bukkit.event.player.PlayerChangedWorldEvent
import org.bukkit.event.player.PlayerLoginEvent
import org.bukkit.event.player.PlayerQuitEvent

class PlayerListeners : Listener {

    @EventHandler
    fun onPlayerDamage(event: EntityDamageEvent) {
        if (event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player

        if (victim.hasMetadata("GOD"))
            event.isCancelled = true

    }

    @EventHandler
    fun onPlayerDamage2(event: EntityDamageByBlockEvent) {
        if (event.damager == null || event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player

        if (victim.hasMetadata("GOD"))
            event.isCancelled = true

    }

    @EventHandler
    fun onPlayerDamage3(event: EntityDamageByEntityEvent) {
        if (event.damager == null || event.entity == null || event.entity !is Player)
            return

        val victim = event.entity as Player

        if (victim.hasMetadata("GOD"))
            event.isCancelled = true

    }

    @EventHandler
    fun onDisconnect(event: PlayerQuitEvent) {
        val player = event.player

        if (player.hasMetadata("GOD"))
            player.removeMetadata("GOD")
        if (player.hasMetadata("INVSEE"))
            player.removeMetadata("INVSEE")

    }

    @EventHandler
    fun onConnect(event: PlayerLoginEvent) {
        val player = event.player

        if (!Whitelist.enabled)
            return

        if (Whitelist.isWhitelisted(player.name.toLowerCase()))
            return

        event.disallow(
            PlayerLoginEvent.Result.KICK_WHITELIST,
            ChatColor.translateAlternateColorCodes('&', Whitelist.kickMessage)
        )
    }

    /*
    * Original code from MYSlots
     */
    @EventHandler(priority = EventPriority.HIGHEST)
    fun onConnect2(event: PlayerLoginEvent) {
        if (!Slots.enabled)
            return

        val player = event.player

        when (event.result) {
            PlayerLoginEvent.Result.KICK_FULL, PlayerLoginEvent.Result.ALLOWED -> {

                if (Bukkit.getOnlinePlayers().size < Slots.getSlots())
                    event.allow()
                else if (Bukkit.getOnlinePlayers().size >= Slots.amount && player.checkPermissionSilent("join_full"))
                    event.allow()
                else
                    event.disallow(
                        PlayerLoginEvent.Result.KICK_FULL,
                        ChatColor.translateAlternateColorCodes('&', Slots.fullMessage)
                    )

            }

            else -> return

        }

    }

    @EventHandler
    fun onWorldChange(event: PlayerChangedWorldEvent) {
        val player = event.player

        if (player.checkPermissionSilent("keepfly"))
            return

        if (player.allowFlight)
            player.allowFlight = false

    }

    /*
    * Original code from EssentialsX
     */
    @EventHandler
    fun onInventoryClick(event: InventoryClickEvent) {
        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        var refreshPlayer: Player? = null

        when {
            inventoryType == InventoryType.PLAYER -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is HumanEntity)
                    return

                inventoryOwner as Player

                if (whoClicked.hasMetadata("INVSEE")) {
                    refreshPlayer = whoClicked

                    if ((!whoClicked.checkPermissionSilent("invsee.modify") || !inventoryOwner.isOnline))
                        event.isCancelled = true
                    else
                        Bukkit.getScheduler()
                            .scheduleSyncDelayedTask(CloudEssentials.instance, inventoryOwner::updateInventory, 1)
                }

            }

            inventoryType == InventoryType.CHEST && topInventory.size == 9 -> {
                val whoClicked = event.whoClicked as Player
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is HumanEntity)
                    return

                if (whoClicked.hasMetadata("INVSEE")) {
                    event.isCancelled = true
                    refreshPlayer = whoClicked
                }

            }

        }

        refreshPlayer ?: return

        Bukkit.getScheduler().scheduleSyncDelayedTask(CloudEssentials.instance, refreshPlayer::updateInventory, 1)

    }

    /*
    * Original code from EssentialsX
     */
    @EventHandler
    fun onInventoryClose(event: InventoryCloseEvent) {
        val topInventory = event.view.topInventory
        val inventoryType = topInventory.type
        val player = event.player as Player
        val refreshPlayer: Player

        when (inventoryType) {
            InventoryType.PLAYER -> {
                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

                refreshPlayer = player
            }

            InventoryType.CHEST -> {
                val inventoryOwner = topInventory.holder

                if (inventoryOwner !is Player)
                    return

                if (player.hasMetadata("INVSEE"))
                    player.removeMetadata("INVSEE")

                refreshPlayer = player

            }

            else -> return

        }

        Bukkit.getScheduler().scheduleSyncDelayedTask(CloudEssentials.instance, refreshPlayer::updateInventory, 1)

    }

}