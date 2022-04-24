package com.ingressaca.bookstoretask.validation.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class BaseValidator
{
    @Autowired
    protected HttpServletRequest httpServletRequest;

    @PersistenceContext
    EntityManager entityManager;

    public BaseValidator() {
    }

    protected Long getIdFromUri()
    {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) httpServletRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        return map.get("id") == null ? 0 : Long.parseLong(map.get("id"));
    }
}
