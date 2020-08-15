package com.github.avexiie.commands.features

import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.extensions.checkPermissionSilent
import com.github.avexiie.objects.Config
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class KickAllCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {

        if (!sender.checkPermission("kickall"))
            return false

        var ignoreStaff = false

        if (args.isNotEmpty() && args[0] == "**")
            ignoreStaff = true

        val reason =
            if (ignoreStaff)
                if (args.size > 1)
                    args.joinToString("** ")
                else
                    "Kicked by a staff member"
            else
                if (args.isNotEmpty())
                    args.joinToString(" ")
                else
                    "Kicked by a staff member"

        for (online in Bukkit.getOnlinePlayers()) {
            if (sender !is Player || !online.name.equals(sender.name, true)) {
                if (online.checkPermissionSilent("kickall.exempt") && !ignoreStaff) {
                    continue
                }
                online.kickPlayer(reason)
            }
        }

        sender.sendMessage(Config.prefix + " §aKicked all players!")

        return false

    }

}