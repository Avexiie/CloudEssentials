package com.github.avexiie.objects

import org.bukkit.enchantments.Enchantment

object XEnchantment {
    private val ENCHANTMENT_MAP: HashMap<String, Enchantment> = HashMap()

    init {
        ENCHANTMENT_MAP["PROT"] = Enchantment.PROTECTION_ENVIRONMENTAL
        ENCHANTMENT_MAP["PROTECTION"] = Enchantment.PROTECTION_ENVIRONMENTAL
        ENCHANTMENT_MAP["FIREPROT"] = Enchantment.PROTECTION_FIRE
        ENCHANTMENT_MAP["FIREPROTECTION"] = Enchantment.PROTECTION_FIRE
        ENCHANTMENT_MAP["FIRE_PROTECTION"] = Enchantment.PROTECTION_FIRE
        ENCHANTMENT_MAP["FEATHERFALLING"] = Enchantment.PROTECTION_FALL
        ENCHANTMENT_MAP["FEATHER_FALLING"] = Enchantment.PROTECTION_FALL
        ENCHANTMENT_MAP["FEATHERFALL"] = Enchantment.PROTECTION_FALL
        ENCHANTMENT_MAP["FF"] = Enchantment.PROTECTION_FALL
        ENCHANTMENT_MAP["BLASTPROT"] = Enchantment.PROTECTION_EXPLOSIONS
        ENCHANTMENT_MAP["BLASTPROTECTION"] = Enchantment.PROTECTION_EXPLOSIONS
        ENCHANTMENT_MAP["BLAST_PROTECTION"] = Enchantment.PROTECTION_EXPLOSIONS
        ENCHANTMENT_MAP["PROJPROT"] = Enchantment.PROTECTION_PROJECTILE
        ENCHANTMENT_MAP["PROJECTILEPROT"] = Enchantment.PROTECTION_PROJECTILE
        ENCHANTMENT_MAP["PROJECTILEPROTECTION"] = Enchantment.PROTECTION_PROJECTILE
        ENCHANTMENT_MAP["PROJECTILE_PROTECTION"] = Enchantment.PROTECTION_PROJECTILE

        ENCHANTMENT_MAP["AQUAINFINITY"] = Enchantment.WATER_WORKER
        ENCHANTMENT_MAP["AQUA_INFINITY"] = Enchantment.WATER_WORKER
        ENCHANTMENT_MAP["AQUAAFFINITY"] = Enchantment.WATER_WORKER
        ENCHANTMENT_MAP["AQUA_AFFINITY"] = Enchantment.WATER_WORKER
        ENCHANTMENT_MAP["DEPTHSTRIDER"] = Enchantment.DEPTH_STRIDER
        ENCHANTMENT_MAP["DEPTH_STRIDER"] = Enchantment.DEPTH_STRIDER
        ENCHANTMENT_MAP["DS"] = Enchantment.DEPTH_STRIDER


        ENCHANTMENT_MAP["SHARP"] = Enchantment.DAMAGE_ALL
        ENCHANTMENT_MAP["SHARPNESS"] = Enchantment.DAMAGE_ALL
        ENCHANTMENT_MAP["SMITE"] = Enchantment.DAMAGE_UNDEAD
        ENCHANTMENT_MAP["BOA"] = Enchantment.DAMAGE_ARTHROPODS
        ENCHANTMENT_MAP["BANE"] = Enchantment.DAMAGE_ARTHROPODS
        ENCHANTMENT_MAP["BANEOFATHROPODS"] = Enchantment.DAMAGE_ARTHROPODS
        ENCHANTMENT_MAP["BANE_OF_ATHROPODS"] = Enchantment.DAMAGE_ARTHROPODS
        ENCHANTMENT_MAP["KB"] = Enchantment.KNOCKBACK
        ENCHANTMENT_MAP["FA"] = Enchantment.FIRE_ASPECT
        ENCHANTMENT_MAP["FIRE"] = Enchantment.FIRE_ASPECT
        ENCHANTMENT_MAP["FIREASPECT"] = Enchantment.FIRE_ASPECT
        ENCHANTMENT_MAP["FIRE_ASPECT"] = Enchantment.FIRE_ASPECT
        ENCHANTMENT_MAP["LOOTING"] = Enchantment.LOOT_BONUS_MOBS

        ENCHANTMENT_MAP["EFFICIENCY"] = Enchantment.DIG_SPEED
        ENCHANTMENT_MAP["EFF"] = Enchantment.DIG_SPEED
        ENCHANTMENT_MAP["SILKTOUCH"] = Enchantment.SILK_TOUCH
        ENCHANTMENT_MAP["SILK_TOUCH"] = Enchantment.SILK_TOUCH
        ENCHANTMENT_MAP["SILK"] = Enchantment.SILK_TOUCH
        ENCHANTMENT_MAP["UNBREAKING"] = Enchantment.DURABILITY
        ENCHANTMENT_MAP["UNBREAK"] = Enchantment.DURABILITY
        ENCHANTMENT_MAP["FORTUNE"] = Enchantment.LOOT_BONUS_BLOCKS

        ENCHANTMENT_MAP["POWER"] = Enchantment.ARROW_DAMAGE
        ENCHANTMENT_MAP["PUNCH"] = Enchantment.ARROW_KNOCKBACK
        ENCHANTMENT_MAP["FLAME"] = Enchantment.ARROW_FIRE
        ENCHANTMENT_MAP["INFINITY"] = Enchantment.ARROW_INFINITE

        ENCHANTMENT_MAP["LOTS"] = Enchantment.LUCK
        ENCHANTMENT_MAP["LUCKOFTHESEA"] = Enchantment.LUCK
        ENCHANTMENT_MAP["LUCK_OF_THE_SEA"] = Enchantment.LUCK
        ENCHANTMENT_MAP["LURE"] = Enchantment.LURE

        // 1.9
        try {
            val frostWalker =
                Enchantment.getByName("FROST_WALKER")
            if (frostWalker != null) {
                ENCHANTMENT_MAP["FROSTWALKER"] = frostWalker
                ENCHANTMENT_MAP["FROST"] = frostWalker
            }
            val mending = Enchantment.getByName("MENDING")
            if (mending != null) {
                ENCHANTMENT_MAP["MENDING"] = mending
            }
        } catch (ignored: IllegalArgumentException) {
        }

        // 1.11
        try {
            val bindingCurse =
                Enchantment.getByName("BINDING_CURSE")
            if (bindingCurse != null) {
                ENCHANTMENT_MAP["BINDINGCURSE"] = bindingCurse
                ENCHANTMENT_MAP["BINDING"] = bindingCurse
                ENCHANTMENT_MAP["BIND"] = bindingCurse
            }
            val vanishingCurse =
                Enchantment.getByName("VANISHING_CURSE")
            if (vanishingCurse != null) {
                ENCHANTMENT_MAP["VANISHINGCURSE"] = vanishingCurse
                ENCHANTMENT_MAP["VANISHING"] = vanishingCurse
                ENCHANTMENT_MAP["VANISH"] = vanishingCurse
            }
            val sweeping =
                Enchantment.getByName("SWEEPING_EDGE")
            if (sweeping != null) {
                ENCHANTMENT_MAP["SWEEPINGEDGE"] = sweeping
                ENCHANTMENT_MAP["SWEEPEDGE"] = sweeping
                ENCHANTMENT_MAP["SWEEP"] = sweeping
            }
        } catch (ignored: IllegalArgumentException) {
        }

    }


    /**
     * gets an enchantment by the 'human' name
     *
     * @param name = the 'human' item name
     */
    fun getByName(name: String): Enchantment {
        var enchantment: Enchantment? = null

        // tries to get it the normal way
        if (Enchantment.getByName(name.toUpperCase()) != null)
            enchantment = Enchantment.getByName(name.toUpperCase())

        // tries to get it from the map
        if (enchantment == null)
            enchantment = ENCHANTMENT_MAP[name.toUpperCase()]

        return enchantment ?: Enchantment.LUCK

    }

}