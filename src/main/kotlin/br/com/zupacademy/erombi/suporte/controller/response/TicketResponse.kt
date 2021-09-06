package br.com.zupacademy.erombi.suporte.controller.response

import br.com.zupacademy.erombi.suporte.modelo.StatusTicket
import com.fasterxml.jackson.annotation.JsonFormat
import io.micronaut.core.annotation.Introspected
import java.time.LocalDateTime

@Introspected
data class TicketResponse(
    var id: Long,
    var titulo: String,
    var descricao: String,
    @JsonFormat(pattern = "yyyy-MM-dd", shape =JsonFormat.Shape.STRING)
    var dataCriacao: LocalDateTime,
    var status: StatusTicket
) {


}
