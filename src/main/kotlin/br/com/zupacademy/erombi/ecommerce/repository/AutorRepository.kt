package br.com.zupacademy.erombi.ecommerce.repository

import br.com.zupacademy.erombi.ecommerce.modelo.Autor
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

}