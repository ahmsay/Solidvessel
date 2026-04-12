package com.solidvessel.shared.test.contract;

import com.solidvessel.shared.Profiles;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(Profiles.CONTRACT_TEST)
@ComponentScan(
        basePackages = "com.solidvessel",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*WebMapperImpl"),
        useDefaultFilters = false
)
public class BaseProducerContractTest {
}
