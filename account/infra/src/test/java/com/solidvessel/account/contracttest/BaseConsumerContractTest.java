package com.solidvessel.account.contracttest;

import com.solidvessel.shared.Profiles;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.bind.annotation.RestController;

@ActiveProfiles(Profiles.CONTRACT_TEST)
@EnableFeignClients(basePackages = "com.solidvessel")
@ImportAutoConfiguration({FeignAutoConfiguration.class})
@WebMvcTest(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {RestController.class}))
public class BaseConsumerContractTest {
}
