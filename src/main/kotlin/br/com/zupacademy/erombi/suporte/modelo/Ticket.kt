package br.com.zupacademy.erombi.suporte.modelo

import br.com.zupacademy.erombi.suporte.controller.request.AtualizaTicketRequest
import java.time.Instant
import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Ticket(
    titulo: String,
    descricao: String
) {
    fun atualizaDados(request: AtualizaTicketRequest) {
        this.titulo = request.titulo
        this.descricao = request.descricao
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val dataCriacao : LocalDateTime = LocalDateTime.now()

    @Enumerated(EnumType.STRING)
    var status : StatusTicket = StatusTicket.ABERTO
        private set

    @Column(length = 4000)
    var descricao = descricao
        private set

    @Column(length = 60)
    var titulo = titulo
        private set
}