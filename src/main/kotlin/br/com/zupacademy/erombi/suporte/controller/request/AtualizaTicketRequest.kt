package br.com.zupacademy.erombi.suporte.controller.request

import br.com.zupacademy.erombi.suporte.modelo.Ticket
import br.com.zupacademy.erombi.suporte.repository.TicketRepository
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
class AtualizaTicketRequest(
    @field:NotBlank @field:Size(max = 60) val titulo: String,
    @field:NotBlank @field:Size(max = 4000) val descricao: String,
) {

}
