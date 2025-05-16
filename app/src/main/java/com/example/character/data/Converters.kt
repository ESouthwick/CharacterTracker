package com.example.character.data

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromSkills(skills: Skills): String {
        return gson.toJson(skills)
    }

    @TypeConverter
    fun toSkills(skillsString: String): Skills {
        return gson.fromJson(skillsString, Skills::class.java)
    }

    @TypeConverter
    fun fromHealth(health: Health): String {
        return gson.toJson(health)
    }

    @TypeConverter
    fun toHealth(healthString: String): Health {
        return gson.fromJson(healthString, Health::class.java)
    }

    @TypeConverter
    fun fromInventoryItemList(items: List<InventoryItem>): String {
        return gson.toJson(items)
    }

    @TypeConverter
    fun toInventoryItemList(itemsString: String): List<InventoryItem> {
        val type = object : TypeToken<List<InventoryItem>>() {}.type
        return gson.fromJson(itemsString, type)
    }
} 