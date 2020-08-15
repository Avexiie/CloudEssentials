package com.github.avexiie.commands.features

import com.github.avexiie.extensions.applyMetadata
import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.extensions.removeMetadata
import dev.luckynetwork.id.lyrams.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GodCMD : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, cmd: String, args: Array<out String>): Boolean {

        if (!sender.checkPermission("god"))
            return false

        var target: Player

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

        if (args.isNotEmpty() && sender is Player) {
            if (Bukkit.getPlayer(args[0]) == null) {
                target.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0])
            others = true

        }

        if (!sender.checkPermission("god", others))
            return false

        val state =
            if (target.hasMetadata("GOD")) {
                target.removeMetadata("GOD")
                false
            } else {
                target.applyMetadata("GOD", true)
                true
            }


        when {
            others ->
                if (state) {
                    sender.sendMessage(Config.prefix + " §aEnabled god mode for §l" + target.name + "!")
                    target.sendMessage(Config.prefix + " §aGod mode enabled!")
                } else {
                    sender.sendMessage(Config.prefix + " §cDisabled god mode for §l" + target.name + "!")
                    target.sendMessage(Config.prefix + " §cGod mode disabled!")
                }
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aGod mode enabled!")
                else
                    target.sendMessage(Config.prefix + " §cGod mode disabled!")
        }

        return false

    }

}