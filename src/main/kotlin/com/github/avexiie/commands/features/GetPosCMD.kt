package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class GetPosCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {

        if (!sender.checkPermission("getpos"))
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

        if (args.isNotEmpty() && sender is Player) {

            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player

            others = true

        }

        if (!sender.checkPermission("getpos", others))
            return false

        val loc = target.location

        sender.sendMessage("§6${target.name}('s) location:")
        sender.sendMessage("§6X: ${(loc.x)}")
        sender.sendMessage("§6Y: ${(loc.y)}")
        sender.sendMessage("§6Z: ${(loc.z)}")
        sender.sendMessage("§6Yaw: ${(loc.yaw)}")
        sender.sendMessage("§6Pitch: ${(loc.pitch)}")
        sender.sendMessage("§6World: ${(loc.world.name)}")
        sender.sendMessage(" ")
        if (sender is Player && sender.location.world == loc.world)
            sender.sendMessage("§6Distance: ${loc.distanceSquared(sender.location)}")


        return false
    }
}