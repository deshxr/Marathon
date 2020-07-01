package org.wit.marathonRun.console.models

interface MarathonRunStore {
    fun findAll(): List<MarathonRunModel>
    fun findOne(id: Long): MarathonRunModel?
    fun create(marathonRun: MarathonRunModel)
    fun update(marathonRun: MarathonRunModel)
    fun delete(marathonRun: MarathonRunModel)
}