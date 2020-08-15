package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class FixCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (!sender.checkPermission("fix"))
            return false

        var target: Player

        // casts target
        target =
                // if console executes this
            if (sender !is Player) {
                // console must specify a player
                if (args.isEmpty()) {
                    sender.sendMessage(Config.prefix + " §cInvalid usage!")
                    return false
                }

                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }

                Bukkit.getPlayer(args[0])

                // if executed by player
            } else
                sender

        var others = false
        var offset = 0

        if (args.isNotEmpty() && sender is Player &&
            !(args[0].equals("hand", true) || args[0].equals("all", true))
        ) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player

            others = true
            offset = 1
        }


        if (args.isEmpty() || args[0 + offset].equals("hand", true)) {

            if (!sender.checkPermission("fix", others))
                return false

            repairHand(target)

        } else if (args[0 + offset].equals("all", true)) {

            if (!sender.checkPermission("fix.all", others))
                return false

            repairAll(target)

        } else {

            sendUsage(sender)

        }

        return false

    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /fix [player] [all/hand]")
}

private fun repairHand(player: Player) {
    val itemInHand: ItemStack? = player.inventory.itemInHand

    if (itemInHand == null || itemInHand.type.isBlock || itemInHand.durability == 0.toShort() || itemInHand.type.maxDurability < 1) {
        player.sendMessage(Config.prefix + " §cYou can't fix that!")
        return
    }

    if (itemInHand.durability == 0.toShort()) {
        player.sendMessage(Config.prefix + " §cThis item doesn't need repairing!")
        return
    }

    itemInHand.durability = 0.toShort()
    player.sendMessage(Config.prefix + " §aItem repaired!")

    player.updateInventory()

}

private fun repairAll(player: Player) {
    var count = 0
    for (item in player.inventory.contents) {

        if (item == null || item.type.isBlock || item.durability == 0.toShort() || item.type.maxDurability < 1) {
            continue
        }

        if (item.durability == 0.toShort()) {
            continue
        }

        item.durability = 0.toShort()
        count++

    }

    for (item in player.inventory.armorContents) {

        if (item == null || item.type.isBlock || item.durability == 0.toShort() || item.type.maxDurability < 1) {
            continue
        }

        if (item.durability == 0.toShort()) {
            continue
        }

        item.durability = 0.toShort()
        count++

    }

    player.sendMessage(Config.prefix + " §a$count items repaired!")
}