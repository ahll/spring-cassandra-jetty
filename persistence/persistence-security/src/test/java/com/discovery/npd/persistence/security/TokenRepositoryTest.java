/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.security;

import com.discovery.npd.domain.security.Permission;
import com.discovery.npd.domain.security.token.TokenRepository;
import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/*
 * @author Lili
 */
public class TokenRepositoryTest {

    private TokenRepository tokenRepository;

    @Before
    public void before() throws TTransportException, IOException, InterruptedException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        CassandraSecurityDataBaseHelper helper = CassandraSecurityDataBaseHelper.getCassandraDataSourceHelper(9142, "localhost");
        tokenRepository = new CassandraTokenRepository(helper);
    }

    @Test
    public void repositoryBasicOperationTest() throws InterruptedException {

        Integer ttl = 10;

        UUID id = tokenRepository.create(Lists.newArrayList(Permission.PLATFORM), ttl, UUID.randomUUID());

        assertThat(tokenRepository.exist(id, Permission.PLATFORM)).isNotNull();

        // wait token to die
        TimeUnit.SECONDS.sleep(ttl);
        assertThat(tokenRepository.exist(id, Permission.PLATFORM)).isNull();
    }

    @After
    public void shutdown() {
        EmbeddedCassandraServerHelper.cleanEmbeddedCassandra();
    }

}
