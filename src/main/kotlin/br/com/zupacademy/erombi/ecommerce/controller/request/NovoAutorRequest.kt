package br.com.zupacademy.erombi.ecommerce.controller.request

import br.com.zupacademy.erombi.compartilhado.UniqueValue
import br.com.zupacademy.erombi.ecommerce.modelo.Autor
import io.micronaut.core.annotation.Introspected
import javax.validation.Valid
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
class NovoAutorRequest(
    @field:NotBlank
    @field:UniqueValue
    val nome: String?,

    @field:NotBlank
    @field:Email
    val email: String?,

    @field:Valid
    @field:Size(min = 1)
    val livros : MutableSet<NovoLivroRequest> = HashSet(),

    @field:NotBlank
    val cep : String
) {

    fun toModel(): Autor? {

        this.nome?.let { nome ->
            this.email?.let { email ->
                return Autor(nome, email, this.livros)
            }
        }

        return null
    }

    override fun toString(): String {
        return "NovoAutorRequest(nome='$nome')"
    }
}
