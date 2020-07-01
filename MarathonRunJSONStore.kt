package org.wit.marathonRun.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging
import org.wit.marathonRun.console.helpers.exists
import org.wit.marathonRun.console.helpers.read
import org.wit.marathonRun.console.helpers.write

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "marathonRun.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<MarathonRunModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class MarathonRunJSONStore : MarathonRunStore {

    var marathonRuns = mutableListOf<MarathonRunModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<MarathonRunModel> {
        return marathonRuns
    }

    override fun findOne(id: Long) : MarathonRunModel? {
        var foundMarathonRun: MarathonRunModel? = marathonRuns.find { p -> p.id == id }
        return foundMarathonRun
    }

    override fun create(marathonRun: MarathonRunModel) {
        marathonRun.id = generateRandomId()
        marathonRuns.add(marathonRun)
        serialize()
    }

    override fun update(marathonRun: MarathonRunModel) {
        var foundMarathonRun = findOne(marathonRun.id!!)
        if (foundMarathonRun != null) {
            foundMarathonRun.place = marathonRun.place
            foundMarathonRun.distance = marathonRun.distance
        }
        serialize()
    }

    override fun delete(marathonRun: MarathonRunModel) {
        marathonRuns.remove(marathonRun)
        serialize()
    }

    internal fun logAll() {
        marathonRuns.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(marathonRuns,
            listType
        )
        write(
            JSON_FILE,
            jsonString
        )
    }

    private fun deserialize() {
        val jsonString =
            read(JSON_FILE)
        marathonRuns = Gson().fromJson(jsonString, listType)
    }
}