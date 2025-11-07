package com.indocyber.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ComponentClass {

    private final SimpleClass simpleClass;

    //COMPONENT DEPENDENCY INJECTION
    public ComponentClass(@Qualifier("simpleClass1") SimpleClass simpleClass) {
        this.simpleClass = simpleClass;
    }
}
