package br.com.zupacademy.erombi.compartilhado

import io.micronaut.core.annotation.AnnotationValue
import io.micronaut.validation.validator.constraints.ConstraintValidator
import io.micronaut.validation.validator.constraints.ConstraintValidatorContext
import jakarta.inject.Singleton
import javax.validation.Constraint

@MustBeDocumented
@Target(AnnotationTarget.FIELD, AnnotationTarget.CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueValueValidator::class])
annotation class UniqueValue (
    val message : String = "Valor Unico !"
)

@Singleton
class UniqueValueValidator : ConstraintValidator<UniqueValue, Any> {

    override fun isValid(
        value: Any?,
        annotationMetadata: AnnotationValue<UniqueValue>,
        context: ConstraintValidatorContext
    ): Boolean {
        return true
    }

}
