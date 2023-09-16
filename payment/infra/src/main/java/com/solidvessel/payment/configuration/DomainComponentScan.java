package com.solidvessel.payment.configuration;

import com.solidvessel.shared.service.DomainComponent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "com.solidvessel",
        includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {DomainComponent.class})
        })
public class DomainComponentScan {
}
