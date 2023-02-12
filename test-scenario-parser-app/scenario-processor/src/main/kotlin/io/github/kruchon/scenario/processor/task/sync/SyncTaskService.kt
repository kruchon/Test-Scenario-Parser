package io.github.kruchon.scenario.processor.task.sync

import io.github.kruchon.test.scenario.parser.api.TestScenario
import io.github.kruchon.test.scenario.parser.api.TestScenarioParser
import org.springframework.stereotype.Service

@Service
class SyncTaskService {

    fun processRequests(syncTaskRequest: SyncTaskRequest): SyncTaskResponse {
        val testScenarios = syncTaskRequest.scenarios.map { TestScenario(it.name, it.content) }
        val parsingResult = TestScenarioParser.parse(testScenarios)
        return parsingResult.sources.map { SyncTaskResponse.File(it.name, it.content) }.let { SyncTaskResponse(it) }
    }

}