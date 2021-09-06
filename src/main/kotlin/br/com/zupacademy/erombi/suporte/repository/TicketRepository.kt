package br.com.zupacademy.erombi.suporte.repository

import br.com.zupacademy.erombi.suporte.controller.response.TicketResponse
import br.com.zupacademy.erombi.suporte.modelo.Ticket
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import io.micronaut.data.model.Page
import io.micronaut.data.model.Pageable

@Repository
interface TicketRepository : JpaRepository<Ticket, Long> {

    fun find(pageable: Pageable): Page<TicketResponse>
}