package io.github.kruchon.scenario.configurator.project.view

import io.github.kruchon.scenario.configurator.scenario.view.ScenarioView
import io.github.kruchon.scenario.configurator.source.view.SourceView
import java.util.UUID

class ProjectView(
    val id: UUID,
    val name: String,
    val generationPackage: String,
    val implementationPackage: String,
    val scenarios: List<ScenarioView>,
    val sources: List<SourceView>
)