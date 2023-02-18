package io.github.kruchon.configurator.source.repository

import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SourceRepository: JpaRepository<Source, UUID> {
    fun findByProjectId(projectId: UUID): List<Source>
}