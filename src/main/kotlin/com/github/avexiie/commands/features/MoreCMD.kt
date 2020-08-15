package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class MoreCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player)
            return false

        if (!sender.checkPermission("more"))
            return false

        sender.inventory.itemInHand ?: return false

        val itemInHand = sender.inventory.itemInHand

        itemInHand.amount = 64
        sender.updateInventory()

        return false

    }
}