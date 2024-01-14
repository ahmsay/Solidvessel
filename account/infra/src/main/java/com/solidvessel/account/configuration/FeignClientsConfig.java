package com.solidvessel.account.configuration;

import com.solidvessel.shared.Profiles;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableFeignClients(basePackages = "com.solidvessel")
@Profile("!" + Profiles.INTEGRATION_TEST)
public class FeignClientsConfig {
}
