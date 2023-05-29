package com.yugabyte.labs.ysql.tc.accounts;

import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.time.Instant;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@ActiveProfiles({"test"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class AccountRepositoryTests {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    @FlywayTest(locationsForMigrate = {"/db/testing"})
    public void findById_expectThat_ReturnsRecordAndMatches() throws Exception {
        var identityRecord = new Account();
        identityRecord.setId(UUID.fromString("d0edfcf9-1afd-4fe0-8846-ee0b0fe82dd5"));
        identityRecord.setUserName("enorthcott8");
        identityRecord.setEmail("jhavile8@amazon.com");
        identityRecord.setActive(false);
        identityRecord.setCreatedAt(Instant.parse("2022-08-25T04:00:00Z"));
        identityRecord.setLastAccessAt(Instant.parse("2023-02-04T05:00:00Z"));

        var result = accountRepository.findById(identityRecord.getId());

        assertThat(result, notNullValue());
        assertThat(result.isPresent(), is(true));

        var returnedRecord = result.get();
        assertThat(returnedRecord, is(identityRecord));
        assertThat(returnedRecord.getUserName(), is(identityRecord.getUserName()));
        assertThat(returnedRecord.getEmail(), is(identityRecord.getEmail()));
        assertThat(returnedRecord.getActive(), is(identityRecord.getActive()));
        assertThat(returnedRecord.getCreatedAt(), is(identityRecord.getCreatedAt()));
        assertThat(returnedRecord.getLastAccessAt(), is(identityRecord.getLastAccessAt()));
    }
}