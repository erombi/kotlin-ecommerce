package br.com.zupacademy.erombi.ecommerce.controller.validations

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.transaction.SynchronousTransactionManager
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton
import java.sql.Connection
import javax.persistence.EntityManager
import javax.validation.Constraint
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueValueIsbnValidator::class])
annotation class UniqueIsbnValue(
    val message: String = "Email já cadastrado no sistema",
    val groups: Array<KClass<Any>> = [],
    val payload: Array<KClass<Any>> = [],
)

@Singleton
class UniqueValueIsbnValidator(private val manager: EntityManager, private val executor : SynchronousTransactionManager<Connection>)
        : ConstraintValidator<UniqueIsbnValue, String> {

    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<UniqueIsbnValue>,
        context: ConstraintValidatorContext
    ): Boolean {

        var resultList : MutableList<Any?>? = null

        executor.executeWrite {
            val query = manager.createQuery("select 1 from Livro obj where obj.isbn = :value")
            query.setParameter("value", value)
            resultList = query.resultList
        }

        return resultList!!.isEmpty()
    }

}