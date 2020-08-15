package com.github.avexiie.objects

object Whitelist {

    var whitelistCache = ArrayList<String>()
    var kickMessage = "You are not whitelisted!"
    var enabled = false

    fun add(name: String, save: Boolean = true): Boolean {

        var success = false

        if (!whitelistCache.contains(name.toLowerCase())) {
            whitelistCache.add(name.toLowerCase())
            success = true
        }

        if (save)
            save()
        return success

    }

    fun remove(name: String, save: Boolean = true): Boolean {

        var success = false

        if (whitelistCache.contains(name.toLowerCase())) {
            whitelistCache.remove(name.toLowerCase())
            success = true
        }

        if (save)
            save()
        return success

    }

    fun toggle(boolean: Boolean?): Boolean {

        enabled = boolean ?: !enabled

        save()
        return enabled

    }

    fun list(): List<String> {

        return whitelistCache

    }

    fun isWhitelisted(name: String): Boolean {

        return whitelistCache.contains(name)

    }

    fun clear() {

        whitelistCache.clear()

        save()

    }

    fun reload() {

        Config.reloadWhitelist()

    }

    fun save() {

        Config.whitelistData.set("whitelist.enabled", enabled)
        Config.whitelistData.set("whitelist.kick_message", kickMessage)
        Config.whitelistData.set("whitelist.whitelisted", whitelistCache)

        Config.whitelistData.save(Config.whitelistFile)

    }

}