package com.github.avexiie.extensions

import com.github.avexiie.objects.Config
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

internal fun Player.checkPermission(permission: String, others: Boolean = false): Boolean {
    var toCheck = "cloudessentials." + permission.toLowerCase()

    if (others)
        toCheck = "cloudessentials." + permission.toLowerCase() + ".others"

    val boolean = this.hasPermission(toCheck)

    if (!boolean) {
        this.sendMessage(Config.prefix + " §cYou do not have permission $toCheck!")
    }

    return boolean
}

internal fun Player.checkPermissionSilent(permission: String, others: Boolean = false): Boolean {
    var toCheck = "cloudessentials." + permission.toLowerCase()

    if (others)
        toCheck = "cloudessentials." + permission.toLowerCase() + ".others"

    return hasPermission(toCheck)
}

internal fun CommandSender.checkPermission(permission: String, others: Boolean = false): Boolean {
    var toCheck = "cloudessentials." + permission.toLowerCase()

    if (others)
        toCheck = "cloudessentials." + permission.toLowerCase() + ".others"

    val boolean = this.hasPermission(toCheck)

    if (!boolean) {
        this.sendMessage(Config.prefix + " §cYou do not have permission $toCheck!")
    }

    return boolean
}