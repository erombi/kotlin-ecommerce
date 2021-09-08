package br.com.zupacademy.erombi.controller

import br.com.zupacademy.erombi.ecommerce.client.EnderecoClient
import br.com.zupacademy.erombi.ecommerce.controller.request.NovoAutorRequest
import br.com.zupacademy.erombi.ecommerce.controller.request.NovoLivroRequest
import io.micronaut.http.HttpMethod
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.annotation.MockBean
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import java.time.LocalDate

@MicronautTest
class AutorControllerMockTest {

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    @field:Inject
    lateinit var enderecoClient: EnderecoClient

    lateinit var novoLivroRequest: NovoLivroRequest

    @BeforeEach
    fun setUp() {
        novoLivroRequest = NovoLivroRequest("JPA", "123", LocalDate.of(2020, 5, 1))
    }

    @Test
    internal fun `deve cadastrar um novo autor`() {
        val novoAutorRequest = NovoAutorRequest("Jorge", "jorge@zup.com", hashSetOf(novoLivroRequest), "0101001")

        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep)).thenReturn(HttpResponse.ok())

        val request = HttpRequest.POST("/autores", novoAutorRequest)
        val response = client.toBlocking().exchange(request, Any::class.java)

        Assertions.assertEquals(HttpStatus.CREATED, response.status)
        Assertions.assertTrue(response.headers.contains("Location"))
        Assertions.assertEquals("/autores/${novoAutorRequest.nome}", response.header("Location"))


    }


    @MockBean(EnderecoClient::class)
    fun mockEndereco(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }

}