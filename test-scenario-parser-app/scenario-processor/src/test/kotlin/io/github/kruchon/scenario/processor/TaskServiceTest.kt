package io.github.kruchon.scenario.processor

import io.github.kruchon.scenario.configurator.processor.TaskRequest
import java.util.UUID
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class TaskServiceTest : BaseTest() {

    @Test
    fun `automatic test is generated from scenario`() {
        mockMvc.post("/api/processor/sync/task") {
            content = objectMapper.writeValueAsString(
                TaskRequest(
                    listOf(
                        TaskRequest.Scenario(
                            "TariffTest",
                            "User registered in registration form. User paid simple tariff."
                        )
                    ),
                    generationPackage = "io.github.kruchon",
                    implementationPackage = "test.package",
                    UUID.randomUUID()
                )
            )
            contentType = MediaType.APPLICATION_JSON
        }.andExpect {
            status {
                isOk()
            }
            content {
                json(
                    //language=json
                    """
                    {
                       "files":[
                          {
                             "name":"User.kt",
                             "content":"package io.github.kruchon\r\n\r\nimport io.github.kruchon.Tariff\nimport io.github.kruchon.Form\r\n\r\ninterface User {\r\n    infix fun pay(tariff: Tariff)\r\n    infix fun `register in`(form: Form)\r\n}"
                          },
                          {
                             "name":"Form.kt",
                             "content":"package io.github.kruchon\r\n\r\ndata class Form(\r\n    val value: String\r\n)"
                          },
                          {
                             "name":"Tariff.kt",
                             "content":"package io.github.kruchon\r\n\r\ndata class Tariff(\r\n    val value: String\r\n)"
                          },
                          {
                             "name":"TariffTest.kt",
                             "content":"package io.github.kruchon\r\n\r\nimport test.package.UserImpl\r\nimport io.github.kruchon.Form\r\nimport io.github.kruchon.Tariff\r\n\r\nclass Test {\r\n    @Test\r\n    fun test() {\r\n        val user = UserImpl()\r\n        user `register in` Form(value = \"registration\")\r\n        user pay Tariff(value = \"simple\")\r\n    }\r\n}"
                          }
                       ]
                    }
                        """
                )
            }
        }
    }

}