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

class ExplodeCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {
        if (sender !is Player || !sender.checkPermission("explode"))
            return false

        val nullSet: Set<Material>? = null
        var target = sender
        var targetBlock = sender.getTargetBlock(nullSet, 120)
        var others = false
        var damage = true
        var power = 4f

        if (args.isNotEmpty() && !(args[0].contains("-power=") || args[0].contains("-nodamage"))) {
            if (Bukkit.getPlayer(args[0]) == null) {
                sender.sendMessage(Config.prefix + " §cPlayer not found!")
                return false
            }

            target = Bukkit.getPlayer(args[0]) as Player
            targetBlock = target.location.block

            others = true
        }

        if (!sender.checkPermission("explode", others))
            return false

        val argsAsString = args.joinToString(" ")

        if (argsAsString.toLowerCase().contains("-nodamage"))
            damage = false

        if (argsAsString.toLowerCase().contains("-power="))
            power = argsAsString
                .split("-power=")[1].toFloat()

        val location = Location(
            targetBlock.world, targetBlock.x.toDouble(), (targetBlock.y + 1).toDouble(),
            targetBlock.z.toDouble()
        )

        targetBlock.world.createExplosion(location, power, damage)

        if (others) {
            target.sendMessage(Config.prefix + " §aYou have been exploded!")
        }

        return false

    }

}