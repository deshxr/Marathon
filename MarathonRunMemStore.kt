package org.wit.marathonRun.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class MarathonRunMemStore : MarathonRunStore {

    val marathonRuns = ArrayList<MarathonRunModel>()

    override fun findAll(): List<MarathonRunModel> {
        return marathonRuns
    }

    override fun findOne(id: Long) : MarathonRunModel? {
        var foundMarathonRun: MarathonRunModel? = marathonRuns.find { p -> p.id == id }
        return foundMarathonRun
    }

    override fun create(marathonRun: MarathonRunModel) {
        marathonRun.id = getId()
        marathonRuns.add(marathonRun)
        logAll()
    }

    override fun update(marathonRun: MarathonRunModel) {
        var foundMarathonRun = findOne(marathonRun.id!!)
        if (foundMarathonRun != null) {
            foundMarathonRun.place = marathonRun.place
            foundMarathonRun.distance = marathonRun.distance
        }
    }

    override fun delete(marathonRun: MarathonRunModel) {
        marathonRuns.remove(marathonRun)
    }

    internal fun logAll() {
        marathonRuns.forEach { logger.info("${it}") }
    }
}