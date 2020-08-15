package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TopCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {

        if (sender !is Player)
            return false

        if (!sender.checkPermission("top"))
            return false

        sender.teleport(getHighestLocation(sender.location))

        sender.sendMessage(Config.prefix + " §aTeleporting!")

        return false
    }
}

fun getHighestLocation(location: Location): Location {
    var i = 255.0

    while (location.world.getBlockAt(Location(location.world, location.x, i, location.z)).type == Material.AIR)
        i--

    return Location(location.world, location.x, i + 1, location.z)

}