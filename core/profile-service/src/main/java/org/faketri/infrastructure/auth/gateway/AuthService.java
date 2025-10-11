package org.faketri.infrastructure.auth.gateway;

import org.faketri.infrastructure.pojo.request.RegisterRequestPojo;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<Void> register(RegisterRequestPojo registerRequestPojo);
}
