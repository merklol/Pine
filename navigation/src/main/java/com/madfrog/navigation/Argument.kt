package com.madfrog.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

data class Argument(
    internal val name: String,
    private val type: Type,
    private val nullable: Boolean = false,
    private val defaultValue: Any? = null
) {

    enum class Type {
        String,
        StringArray,
        Int,
        IntArray,
        Boolean,
        BooleanArray,
        Float,
        FloatArray,
        Long,
        LongArray,
        Reference
    }

    internal fun toNavNamedNavArgument() = navArgument(name) {
        type = this@Argument.type.toNavType()
        nullable = this@Argument.nullable
        defaultValue = this@Argument.defaultValue
    }

    private fun Type.toNavType() = when (this) {
        Type.String -> NavType.StringType
        Type.StringArray -> NavType.StringArrayType
        Type.Int -> NavType.IntType
        Type.IntArray -> NavType.IntArrayType
        Type.Boolean -> NavType.BoolType
        Type.BooleanArray -> NavType.BoolArrayType
        Type.Float -> NavType.FloatType
        Type.FloatArray -> NavType.FloatArrayType
        Type.Long -> NavType.LongType
        Type.LongArray -> NavType.LongArrayType
        Type.Reference -> NavType.ReferenceType
    }
}

fun stringArgument(name: String, nullable: Boolean = false, defaultValue: String = "") =
    Argument(name, Argument.Type.String, nullable, defaultValue)