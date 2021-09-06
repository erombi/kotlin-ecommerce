package br.com.zupacademy.erombi.ecommerce.client

import br.com.zupacademy.erombi.ecommerce.controller.response.EnderecoResponse
import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.client.annotation.Client

@Client("https://viacep.com.br/ws")
interface EnderecoClient {

    @Get("/{cep}/json/")
    fun consulta(@PathVariable cep: String): HttpResponse<EnderecoResponse>

}