package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class TeleportCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {

        if (sender !is Player)
            return false

        if (!sender.checkPermission("teleport"))
            return false

        if (args.isEmpty()) {
            sendUsage(sender)
            return false
        }

        when (commandName.toUpperCase()) {
            "TELEPORT", "TP" -> {
                var target: Player = sender
                var toTarget: Player
                var others = false

                if (args.size == 1) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Config.prefix + " §cPlayer not found!")
                        return false
                    }

                    toTarget = Bukkit.getPlayer(args[0])

                    if (!sender.checkPermission("teleport", others))
                        return false

                    target.teleport(toTarget.location)
                }

                if (args.size == 2) {
                    if (Bukkit.getPlayer(args[0]) == null) {
                        sender.sendMessage(Config.prefix + " §c§l${args[0]} §cnot found!")
                        return false
                    }

                    target = Bukkit.getPlayer(args[0])

                    if (Bukkit.getPlayer(args[1]) == null) {
                        sender.sendMessage(Config.prefix + " §c§l${args[1]} §cnot found!")
                        return false
                    }

                    others = true

                    toTarget = Bukkit.getPlayer(args[1])

                    if (!sender.checkPermission("teleport", others))
                        return false

                    target.teleport(toTarget.location)

                    when {
                        others -> {
                            sender.sendMessage(Config.prefix + " §aTeleported ${target.name} to ${args[1]}")
                            target.sendMessage(Config.prefix + " §aTeleported you to ${args[1]}")
                        }
                        else -> target.sendMessage(Config.prefix + " §aTeleported you to ${args[1]}")
                    }

                }

            }
            "TPPOS" -> {
                var target: Player = sender
                var others = false

                if (args.size < 3) {
                    sendUsage(sender)
                    return false
                }

                var world: World? = null
                val x: Double
                val y: Double
                val z: Double
                val yaw: Float
                val pitch: Float
                var offset = 0

                if (Bukkit.getPlayer(args[0]) != null) {
                    offset = 1
                    target = Bukkit.getPlayer(args[0])
                    others = true
                }

                when (args.size + offset) {
                    6 -> {
                        world = Bukkit.getWorld(args[0 + offset])
                        x = args[1 + offset].toDouble()
                        y = args[2 + offset].toDouble()
                        z = args[3 + offset].toDouble()
                        yaw = args[4 + offset].toFloat()
                        pitch = args[5 + offset].toFloat()
                    }
                    5 -> {
                        x = args[0 + offset].toDouble()
                        y = args[1 + offset].toDouble()
                        z = args[2 + offset].toDouble()
                        yaw = args[3 + offset].toFloat()
                        pitch = args[4 + offset].toFloat()
                    }
                    4 -> {
                        world = Bukkit.getWorld(args[0 + offset])
                        x = args[1 + offset].toDouble()
                        y = args[2 + offset].toDouble()
                        z = args[3 + offset].toDouble()
                        yaw = sender.location.yaw
                        pitch = sender.location.pitch
                    }
                    3 -> {
                        world = sender.world
                        x = args[0 + offset].toDouble()
                        y = args[1 + offset].toDouble()
                        z = args[2 + offset].toDouble()
                        yaw = sender.location.yaw
                        pitch = sender.location.pitch
                    }
                    else -> {
                        sendUsage(sender)
                        return false
                    }
                }

                if (!sender.checkPermission("teleport.position", others))
                    return false

                if (world == null) {
                    sender.sendMessage(Config.prefix + " §cWorld not found!")
                    return false
                }

                val location = Location(world, x, y, z, yaw, pitch)

                target.teleport(location)

                when {
                    others -> {
                        sender.sendMessage(Config.prefix + " §aTeleported ${target.name} to ${world.name} $x $y $z")
                        target.sendMessage(Config.prefix + " §aTeleported you to ${world.name} $x $y $z")
                    }
                    else -> target.sendMessage(Config.prefix + " §aTeleported you to ${world.name} $x $y $z")
                }

            }
            "TPHERE", "S" -> {
                if (Bukkit.getPlayer(args[0]) == null) {
                    sender.sendMessage(Config.prefix + " §cPlayer not found!")
                    return false
                }

                val toTarget: Player = Bukkit.getPlayer(args[0])

                if (!sender.checkPermission("teleport.here"))
                    return false

                toTarget.teleport(sender.location)

                sender.sendMessage(Config.prefix + " §aTeleported ${toTarget.name} to ${sender.name}")
                toTarget.sendMessage(Config.prefix + " §aTeleported you to ${sender.name}")

            }
            else -> sendUsage(sender)
        }

        return false
    }

}

private fun sendUsage(sender: CommandSender) {
    sender.sendMessage("§cUsage: /tp <player> [<otherPlayer>]")
    sender.sendMessage("§cUsage: /tppos [world] <x> <y> <z> [yaw] [pitch]")
    sender.sendMessage("§cUsage: /tphere <player>")
}