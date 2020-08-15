package com.github.avexiie.extensions

import com.github.avexiie.CloudEssentials
import org.bukkit.entity.Entity
import org.bukkit.metadata.FixedMetadataValue

/**
 * applies a metadata easily
 */
internal fun Entity.applyMetadata(metadata: String, value: Any?) =
    this.setMetadata(metadata, FixedMetadataValue(CloudEssentials.instance, value))


/**
 * removes a metadata easily
 */
internal fun Entity.removeMetadata(metadata: String) =
    this.removeMetadata(metadata, CloudEssentials.instance)