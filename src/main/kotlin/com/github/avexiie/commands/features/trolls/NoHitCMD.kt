package com.github.avexiie.commands.features.trolls

import com.github.avexiie.extensions.applyMetadata
import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.extensions.removeMetadata
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class NoHitCMD : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, cmd: String, args: Array<out String>): Boolean {

        if (!Config.trollEnabled)
            return false

        if (!sender.checkPermission("troll.nohit"))
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

        if (!sender.checkPermission("troll.nohit", others))
            return false

        val state =
            if (target.hasMetadata("NOHIT")) {
                target.removeMetadata("NOHIT")
                true
            } else {
                target.applyMetadata("NOHIT", true)
                false
            }


        when {

            others ->
                if (state)
                    sender.sendMessage(Config.prefix + " §aEnabled hit-others for §l" + target.name + "!")
                else
                    sender.sendMessage(Config.prefix + " §cDisabled hit-others for §l" + target.name + "!")
            else ->
                if (state)
                    target.sendMessage(Config.prefix + " §aYou can now hit other entities!")
                else
                    target.sendMessage(Config.prefix + " §cYou can no longer hit other entities!")

        }

        return false

    }

}