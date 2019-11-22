package com.mimecast.tronalddump.domain.entities

// Unnecessary object has to be created because api is not designed well here
data class Embedded(
    val tags: List<Quote>  // tags are actually quotes, api is wrong here
)