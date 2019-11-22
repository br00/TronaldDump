package com.mimecast.tronalddump.base

/**
 *
 * Nice solution to make Singleton and passing a parameter
 * This solution is thread safe
 * Read here for more info https://medium.com/@BladeCoder/kotlin-singletons-with-argument-194ef06edd9e
 *
 */
open class SingletonHolder<out T, in A>(private val constructor: (A) -> T) {

    @Volatile
    private var instance: T? = null

    fun getInstance(arg: A): T {
        return when {
            instance != null -> instance!!
            else -> synchronized(this) {
                if (instance == null) instance = constructor(arg)
                instance!!
            }
        }
    }
}