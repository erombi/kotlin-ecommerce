package br.com.zupacademy.erombi.controller

import br.com.zupacademy.erombi.ecommerce.controller.response.AutorResponse
import br.com.zupacademy.erombi.ecommerce.controller.response.EnderecoResponse
import br.com.zupacademy.erombi.ecommerce.modelo.Autor
import br.com.zupacademy.erombi.ecommerce.repository.AutorRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.net.http.HttpResponse

@MicronautTest
class AutorControllerTest {

    @field:Inject lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client : HttpClient

    lateinit var autor: Autor

    @BeforeEach
    fun setUp() {
        val enderecoResponse = EnderecoResponse("01021-200", "Rua Vinte e Cinco de Março",
                "de 551 ao fim - lado ímpar", "Centro", "São Paulo", "SP", "3550308",
            "1004", "11", "7107")
        autor = Autor("Eduardo", "eduardo@zup.com")
        autorRepository.save(autor)
    }

    @AfterEach
    fun down() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `deve buscar um autor quando um email valido informado`() {
        val response = client.toBlocking().exchange("/autores?email=${autor.email}", AutorResponse::class.java)
        val body = response.body()

        Assertions.assertEquals(HttpStatus.OK, response.status)
        Assertions.assertNotNull(body)
        Assertions.assertEquals("Eduardo", body?.nome)
        Assertions.assertEquals("eduardo@zup.com", body?.email)
    }


}