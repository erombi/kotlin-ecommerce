package br.com.zupacademy.erombi.ecommerce.controller.response

import io.micronaut.core.annotation.Introspected

@Introspected
data class EnderecoResponse(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val ibge: String,
    val gia: String,
    val ddd: String,
    val siafi: String
)