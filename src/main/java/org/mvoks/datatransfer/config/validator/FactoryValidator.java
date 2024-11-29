package org.mvoks.datatransfer.config.validator;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.glassfish.hk2.api.Factory;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.messageinterpolation.ParameterMessageInterpolator;

public class FactoryValidator implements Factory<Validator> {

    private final Validator validator;

    public FactoryValidator() {
        final ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
            .configure()
            .messageInterpolator(new ParameterMessageInterpolator())
            .buildValidatorFactory();
        this.validator = validatorFactory.getValidator();
    }

    @Override
    public Validator provide() {
        return validator;
    }

    @Override
    public void dispose(Validator instance) {
    }
}