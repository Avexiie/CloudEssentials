package com.github.avexiie.commands

import com.github.avexiie.CloudEssentials
import com.github.avexiie.extensions.checkPermission
import com.github.avexiie.objects.Config
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class CloudEssentialsCMD : CommandExecutor {

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        commandName: String,
        args: Array<out String>
    ): Boolean {

        val plugin = CloudEssentials.instance

        if (args.isEmpty()) {
            sender.sendMessage(
                Config.prefix + " §aCurrently using §eCloudEssentials §dv" + plugin.description.version +
                        " §aby §e" +
                        plugin.description.authors.toString()
                            .replace("[", "")
                            .replace("]", "")
            )
            return false
        }

        if (args[0].equals("reload", true)) {

            if (!sender.checkPermission("reload"))
                return false

            Config.reloadAll()

            sender.sendMessage(Config.prefix + " §aConfig Reloaded!")

        }

        return false

    }

}