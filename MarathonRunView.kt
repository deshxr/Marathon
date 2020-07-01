package org.wit.marathonRun.console.views

import org.wit.marathonRun.console.models.MarathonRunJSONStore
import org.wit.marathonRun.console.models.MarathonRunModel

class MarathonRunView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add a Marathon Run")
        println(" 2. Update a Marathon Run")
        println(" 3. Display All Marathon Runs")
        println(" 4. Display Single Marathon Run Details")
        println(" 5. Delete a Marathon Run")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listMarathonRuns(marathonRuns: MarathonRunJSONStore) {
        println("List All Marathon Runs")
        println()
        marathonRuns.logAll()
        println()
    }

    fun showMarathonRun(marathonRun : MarathonRunModel) {
        if(marathonRun != null)
            println("Marathon Run Details [ $marathonRun ]")
        else
            println("Marathon Run Not Found...")
    }

    fun addMarathonRunData(marathonRun : MarathonRunModel) : Boolean {

        println()
        print("Enter the Place you run : ")
        marathonRun.place = readLine()!!
        print("Enter how Far you ran : ")
        marathonRun.distance = readLine()!!

        return marathonRun.place.isNotEmpty() && marathonRun.distance.isNotEmpty()
    }

    fun updateMarathonRunData(marathonRun : MarathonRunModel) : Boolean {

        var tempPlace: String?
        var tempDistance: String?

        if (marathonRun != null) {
            print("Enter a new Place for [ " + marathonRun.place + " ] : ")
            tempPlace = readLine()!!
            print("Enter a new Distance for [ " + marathonRun.distance + " ] : ")
            tempDistance = readLine()!!

            if (!tempPlace.isNullOrEmpty() && !tempDistance.isNullOrEmpty()) {
                marathonRun.place = tempPlace
                marathonRun.distance = tempDistance
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }

}