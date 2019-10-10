package com.andreaseisele.mussig.support.jooq;

import org.jibx.schema.codegen.extend.DefaultNameConverter;
import org.jibx.schema.codegen.extend.NameConverter;
import org.jooq.codegen.DefaultGeneratorStrategy;
import org.jooq.meta.Definition;

public class DepluralizeNamingStrategy extends DefaultGeneratorStrategy {

    private final NameConverter nameConverter = new DefaultNameConverter();

    @Override
    public String getJavaClassName(Definition definition, Mode mode) {
        final String javaClassName = super.getJavaClassName(definition, mode);
        return nameConverter.depluralize(javaClassName);
    }

}
