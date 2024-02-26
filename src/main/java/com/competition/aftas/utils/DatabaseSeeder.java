package com.competition.aftas.utils;

import com.competition.aftas.domain.Authority;
import com.competition.aftas.repository.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final AuthorityRepository authorityRepository;

    @Override
    public void run(String... args) throws Exception {
        seedAuthorities();
    }

    //    INSERT INTO authority (name)
//    VALUES ('CAN_EDIT'), ('CAN_ADD'), ('CAN_DELETE'),('CAN_VIEW');

    public void seedAuthorities() {
        if (authorityRepository.count() == 0) {
        authorityRepository.saveAll(List.of(
                new Authority("CAN_EDIT"),
                new Authority("CAN_ADD"),
                new Authority("CAN_DELETE"),
                new Authority("CAN_VIEW")
        ));

        }
    }
}
