package com.ingressaca.bookstoretask.validation.validator;

import com.ingressaca.bookstoretask.validation.UniqueField;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueFieldValidator extends BaseValidator implements ConstraintValidator<UniqueField, Object> {

    private String entityName;
    private String fieldName;

    @Override
    public void initialize(UniqueField constraintAnnotation) {
        this.entityName = constraintAnnotation.entityClass().getSimpleName();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object fieldValue, ConstraintValidatorContext context) {

        long uriId = this.getIdFromUri();

        try {
            Query query = entityManager.createQuery("select t.id from " + entityName + " t where t." + fieldName + " = :fieldValue ");
            Long id = (Long) query.setParameter("fieldValue", fieldValue).getSingleResult();

            if (uriId > 0) {
                return id == null || !Boolean.FALSE.equals(id.equals(uriId));
            } else {
                return id == null;
            }
        } catch (NoResultException ignored) {
            return true;
        }
    }
}
