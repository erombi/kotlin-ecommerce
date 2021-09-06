package br.com.zupacademy.erombi.suporte.controller.request

import br.com.zupacademy.erombi.suporte.modelo.Ticket
import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoTicketRequest(
    @field:NotBlank @field:Size(max = 60) val titulo: String,
    @field:NotBlank @field:Size(max = 4000) val descricao: String,
) {
    fun toModel(): Ticket {
        return Ticket(this.titulo, this.descricao)
    }

}
