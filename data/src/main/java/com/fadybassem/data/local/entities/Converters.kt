package com.fadybassem.data.local.entities

import androidx.room.TypeConverter
import com.fadybassem.domain.model.BelongsToCollection
import com.fadybassem.domain.model.Cast
import com.fadybassem.domain.model.Crew
import com.fadybassem.domain.model.Genres
import com.fadybassem.domain.model.ProductionCompanies
import com.fadybassem.domain.model.ProductionCountries
import com.fadybassem.domain.model.SpokenLanguages
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromIntList(data: ArrayList<Int>?): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toIntList(data: String?): ArrayList<Int>? {
        val listType = object : TypeToken<ArrayList<Int>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromStringList(data: ArrayList<String>): String? {
        return gson.toJson(data)
    }

    @TypeConverter
    fun toStringList(data: String?): ArrayList<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromGenresList(genres: ArrayList<Genres>?): String? {
        return gson.toJson(genres)
    }

    @TypeConverter
    fun toGenresList(data: String?): ArrayList<Genres>? {
        val listType = object : TypeToken<ArrayList<Genres>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromProductionCompaniesList(productionCompanies: ArrayList<ProductionCompanies>?): String? {
        return gson.toJson(productionCompanies)
    }

    @TypeConverter
    fun toProductionCompaniesList(data: String?): ArrayList<ProductionCompanies>? {
        val listType = object : TypeToken<ArrayList<ProductionCompanies>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromProductionCountriesList(productionCountries: ArrayList<ProductionCountries>?): String? {
        return gson.toJson(productionCountries)
    }

    @TypeConverter
    fun toProductionCountriesList(data: String?): ArrayList<ProductionCountries>? {
        val listType = object : TypeToken<ArrayList<ProductionCountries>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromSpokenLanguagesList(spokenLanguages: ArrayList<SpokenLanguages>?): String? {
        return gson.toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguagesList(data: String?): ArrayList<SpokenLanguages>? {
        val listType = object : TypeToken<ArrayList<SpokenLanguages>>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromBelongsToCollectionList(belongsToCollection: BelongsToCollection?): String? {
        return gson.toJson(belongsToCollection)
    }

    @TypeConverter
    fun toBelongsToCollectionList(data: String?): BelongsToCollection? {
        val listType = object : TypeToken<BelongsToCollection>() {}.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun fromCastList(cast: ArrayList<Cast>): String? {
        return gson.toJson(cast)
    }

    @TypeConverter
    fun toCastList(cast: String?): ArrayList<Cast>? {
        val listType = object : TypeToken<ArrayList<Cast>>() {}.type
        return gson.fromJson(cast, listType)
    }

    @TypeConverter
    fun fromCrewList(crew: ArrayList<Crew>): String? {
        return gson.toJson(crew)
    }

    @TypeConverter
    fun toCrewList(crew: String?): ArrayList<Crew>? {
        val listType = object : TypeToken<ArrayList<Crew>>() {}.type
        return gson.fromJson(crew, listType)
    }
}
