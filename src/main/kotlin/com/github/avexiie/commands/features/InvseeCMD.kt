package com.github.avexiie.commands.features

import com.github.avexiie.extensions.applyMetadata
import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class InvseeCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player)
            return false

        if (!sender.checkPermission("invsee"))
            return false

        if (args.isEmpty()) {
            sender.sendMessage("§cUsage: /invsee <target> [armorContents = true/false]!")
            return false
        }

        val target =
            if (Bukkit.getPlayer(args[0]) != null) {
                Bukkit.getPlayer(args[0])
            } else {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

        val inventory =
            if (args.size > 1) {
                Bukkit.getServer().createInventory(target, 9, "Equipment")
            } else {
                target.inventory
            }

        if (args.size > 1)
            inventory.contents = target.inventory.armorContents

        sender.closeInventory()
        sender.openInventory(inventory)
        sender.applyMetadata("INVSEE", true)

        return false
    }

}