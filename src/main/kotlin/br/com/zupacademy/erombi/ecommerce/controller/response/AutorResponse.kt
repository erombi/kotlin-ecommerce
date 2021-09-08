package br.com.zupacademy.erombi.ecommerce.controller.response

import io.micronaut.core.annotation.Introspected

@Introspected
data class AutorResponse(
    val nome: String,
    val email: String,
) {

}
