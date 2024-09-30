package com.spring.Webflux.repository;

import com.spring.Webflux.entities.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository extends ReactiveCrudRepository<User, Long> {
    Flux<User> findByName(String name);

    @Query("update userinfo SET company = :company")
    Mono<Void> updatingColumn(String company);
}
