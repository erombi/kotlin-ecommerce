package br.com.zupacademy.erombi.ecommerce.modelo

import br.com.zupacademy.erombi.ecommerce.controller.request.NovoLivroRequest
import javax.persistence.*
import javax.validation.Valid

@Entity
class Autor(
    nome : String,
    email: String
) {

    constructor(nome : String,
                email: String,
                @Valid livros : MutableSet<NovoLivroRequest>) : this(nome, email) {
        this.livros = livros.map { Livro(it, this) }.toMutableSet()
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id : Long? = null

    var nome = nome
    var email = email

    @OneToMany(mappedBy = "autor", cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    var livros : MutableSet<Livro> = HashSet()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Autor

        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }

    override fun toString(): String {
        return "Autor(id=$id, nome='$nome', email='$email', livros=$livros)"
    }


}
