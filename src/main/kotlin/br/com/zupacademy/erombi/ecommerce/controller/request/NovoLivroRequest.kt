package br.com.zupacademy.erombi.ecommerce.controller.request

import br.com.zupacademy.erombi.ecommerce.controller.validations.UniqueIsbnValue
import io.micronaut.core.annotation.Introspected
import java.time.LocalDate
import javax.validation.constraints.NotBlank
import javax.validation.constraints.PastOrPresent

@Introspected
class NovoLivroRequest(
    @field:NotBlank
    val titulo: String,

    @field:UniqueIsbnValue
    @field:NotBlank
    val isbn: String,

    @field:PastOrPresent
    val dataPublicacao: LocalDate
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NovoLivroRequest

        if (isbn != other.isbn) return false

        return true
    }

    override fun hashCode(): Int {
        return isbn.hashCode()
    }


}
