package br.com.zupacademy.erombi.ecommerce.controller

import br.com.zupacademy.erombi.ecommerce.client.EnderecoClient
import br.com.zupacademy.erombi.ecommerce.controller.request.NovoAutorRequest
import br.com.zupacademy.erombi.ecommerce.modelo.Autor
import br.com.zupacademy.erombi.ecommerce.repository.AutorRepository
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MediaType
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.http.client.exceptions.HttpClientException
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import jakarta.inject.Inject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.net.URI
import javax.validation.Valid

@Validated
@Controller("/autores")
class AutorController(
    val repository: AutorRepository,
    val client: EnderecoClient
) {

    val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    @Post(consumes = [MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON],
            produces = [MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON])
    fun cadastra(@Body @Valid request: NovoAutorRequest): MutableHttpResponse<UriBuilder> {
        try {
            val consulta = client.consulta(request.cep)

            val autor: Autor? = request.toModel()

            autor?.let { possivelAutor ->
                repository.save(possivelAutor)

                val uri: URI = UriBuilder.of("/autores/${request.nome}")
                    .scheme("http")
                    .host("localhost")
                    .port(8080)
                    .build()

                logger.info("Autor cadastrado ! $possivelAutor")
                return HttpResponse.created(uri)
            }

            return HttpResponse.unprocessableEntity()
        } catch (e : HttpClientResponseException) {
            if (e.status == HttpStatus.BAD_REQUEST) return HttpResponse.badRequest()
            return HttpResponse.serverError()
        }

    }
}