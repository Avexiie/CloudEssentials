package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SmiteCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("smite"))
            return false

        val nullSet: Set<Material>? = null
        var target = sender
        var targetBlock = sender.getTargetBlock(nullSet, 120)
        var others = false

        if (args.isNotEmpty()) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player
            targetBlock = target.location.block

            others = true
        }

        if (!sender.checkPermission("smite", others))
            return false

        val location = Location(
            targetBlock.world, targetBlock.x.toDouble(), (targetBlock.y + 1).toDouble(),
            targetBlock.z.toDouble()
        )

        targetBlock.world.strikeLightning(location)

        if (others) {
            target.sendMessage(Config.prefix + " §aYou have been smitten!")
        }

        return false

    }

}