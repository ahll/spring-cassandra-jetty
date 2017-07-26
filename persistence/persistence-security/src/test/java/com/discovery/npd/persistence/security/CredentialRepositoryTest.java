/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.credential.CredentialRepository;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.UUID;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * @author Lili
 */
public class CredentialRepositoryTest {

    private CredentialRepository repository;
    private CassandraCredential credential;

    @Before
    public void before() throws TTransportException, IOException, InterruptedException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        CassandraSecurityDataBaseHelper helper = CassandraSecurityDataBaseHelper.getCassandraDataSourceHelper(9142, "localhost");
        repository = new CassandraCredentialRepository(helper);
        credential = new CassandraCredential();
        credential.setEmail("email");
        credential.setPassword("password");
        credential.setPermissionList(Lists.newArrayList(Permission.PLATFORM));
    }

    @Test
    public void repositoryBasicOperationTest() {
        UUID id = repository.create(credential).getId();
        assertThat(repository.one(id)).isNotNull();
        assertThat(repository.findByEmail(credential.getEmail())).isNotNull();

        credential.setId(id);
        credential.setEmail("new email");
        repository.save(credential);
        assertThat(repository.findByEmail(credential.getEmail())).isNotNull();
    }

    @After
    public void shutdown() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

}
