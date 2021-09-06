package br.com.zupacademy.erombi

import br.com.zupacademy.erombi.suporte.modelo.StatusTicket
import io.micronaut.runtime.Micronaut.*
fun main(args: Array<String>) {
	print(StatusTicket::class)

	build()
	    .args(*args)
		.packages("br.com.zupacademy.erombi")
		.start()
}

