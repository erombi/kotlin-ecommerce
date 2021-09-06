package br.com.zupacademy.erombi.suporte.controller

import br.com.zupacademy.erombi.suporte.controller.request.AtualizaTicketRequest
import br.com.zupacademy.erombi.suporte.controller.request.NovoTicketRequest
import br.com.zupacademy.erombi.suporte.controller.response.TicketResponse
import br.com.zupacademy.erombi.suporte.modelo.StatusTicket
import br.com.zupacademy.erombi.suporte.modelo.Ticket
import br.com.zupacademy.erombi.suporte.repository.TicketRepository
import io.micronaut.context.annotation.Parameter
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import java.net.URI
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/tickets")
class TicketController(
    private val repository: TicketRepository
) {

    @Post
    fun cadastrar(@Body @Valid request: NovoTicketRequest) : MutableHttpResponse<UriBuilder> {
        var ticket : Ticket = request.toModel()

        ticket = repository.save(ticket)
        val uri : URI = UriBuilder.of("/tickets/${ticket.id}")
            .scheme("http")
            .host("localhost")
            .port(8080)
            .build()

        return HttpResponse.created(uri)
    }

    @Put("/{id}")
    fun atualizar(@Body @Valid request: AtualizaTicketRequest, @PathVariable id: Long): MutableHttpResponse<Any> {
        val possivelTicket = repository.findById(id)
        if (possivelTicket.isEmpty) return HttpResponse.notFound()

        val ticket = possivelTicket.get()
        ticket.atualizaDados(request)
        repository.update(ticket)

        return HttpResponse.ok()
    }

    @Delete("/{id}")
    fun deletar(@PathVariable id : Long) : MutableHttpResponse<Any> {
        if (validaId(id)) return HttpResponse.notFound()

        repository.deleteById(id)

        return HttpResponse.ok()
    }

    @Get
    fun listar(@Parameter page: Int, @Parameter size: Int): Page<TicketResponse> {
        val pageable = Pageable.from(page, size)

        return repository.find(pageable)
    }

    private fun validaId(id: Long): Boolean {
        val found = repository.existsById(id)
        if (!found) return true
        return false
    }
}