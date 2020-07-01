package org.wit.marathonRun.console.controllers

import mu.KotlinLogging
import org.wit.marathonRun.console.models.MarathonRunJSONStore
import org.wit.marathonRun.console.models.MarathonRunModel
import org.wit.marathonRun.console.views.MarathonRunView

class MarathonRunController {

    // val marathons = MarathonMemStore()
    val marathonRuns = MarathonRunJSONStore()
    val marathonRunView = MarathonRunView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Marathon Console App" }
        println("Marathon Kotlin App Version 1.1")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down MarathonRun Console App" }
    }

    fun menu() :Int { return marathonRunView.menu() }

    fun add(){
        var aMarathonRun = MarathonRunModel()

        if (marathonRunView.addMarathonRunData(aMarathonRun))
            marathonRuns.create(aMarathonRun)
        else
            logger.info("Marathon Run Not Added")
    }

    fun list() {
        marathonRunView.listMarathonRuns(marathonRuns)
    }

    fun update() {

        marathonRunView.listMarathonRuns(marathonRuns)
        var searchId = marathonRunView.getId()
        val aMarathonRun = search(searchId)

        if(aMarathonRun != null) {
            if(marathonRunView.updateMarathonRunData(aMarathonRun)) {
                marathonRuns.update(aMarathonRun)
                marathonRunView.showMarathonRun(aMarathonRun)
                logger.info("Marathon Run Updated : [ $aMarathonRun ]")
            }
            else
                logger.info("Marathon Run Not Updated")
        }
        else
            println("Marathon Run Not Updated...")
    }

    fun search() {
        val aMarathonRun = search(marathonRunView.getId())!!
        marathonRunView.showMarathonRun(aMarathonRun)
    }


    fun search(id: Long) : MarathonRunModel? {
        var foundMarathonRun = marathonRuns.findOne(id)
        return foundMarathonRun
    }

    fun delete() {
        marathonRunView.listMarathonRuns(marathonRuns)
        var searchId = marathonRunView.getId()
        val aMarathonRun = search(searchId)

        if(aMarathonRun != null) {
            marathonRuns.delete(aMarathonRun)
            println("Marathon Run Deleted...")
            marathonRunView.listMarathonRuns(marathonRuns)
        }
        else
            println("Marathon Run Not Deleted...")
    }

    fun dummyData() {
        marathonRuns.create(
            MarathonRunModel(
                place = "New York",
                distance = "50km"
            )
        )
        marathonRuns.create(
            MarathonRunModel(
                place = "Shanghai",
                distance = "37km"
            )
        )
        marathonRuns.create(
            MarathonRunModel(
                place = "Waterford City",
                distance = "33km"
            )
        )
    }
}