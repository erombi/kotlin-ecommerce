package br.com.zupacademy.erombi.ecommerce.modelo

import br.com.zupacademy.erombi.ecommerce.controller.request.NovoLivroRequest
import java.time.LocalDate
import javax.persistence.*

@Entity
class Livro(
    titulo: String,
    isbn: String,
    dataPublicacao: LocalDate = LocalDate.now(),
    autor: Autor

) {

    constructor(livroRequest: NovoLivroRequest, autor: Autor)
            : this(livroRequest.titulo, livroRequest.isbn, livroRequest.dataPublicacao, autor)

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    var titulo = titulo

    var isbn = isbn
    var dataPublicacao = dataPublicacao

    @ManyToOne
    var autor = autor
    override fun toString(): String {
        return "Livro(id=$id, titulo='$titulo', isbn='$isbn', dataPublicacao=$dataPublicacao)"
    }


}
