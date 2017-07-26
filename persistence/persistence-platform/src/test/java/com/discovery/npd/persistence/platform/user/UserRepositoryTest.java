/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.platform.user;

import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.persistence.platform.PlatformDataBaseHelper;
import java.io.IOException;
import java.util.UUID;
import org.apache.thrift.transport.TTransportException;
import org.cassandraunit.utils.EmbeddedCassandraServerHelper;
import static org.fest.assertions.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

/*
 * @author lili.he
 */
public class UserRepositoryTest {

    private UserRepository userRepository;
    private CassandraUser user;

    private UUID id;

    @Before
    public void before() throws TTransportException, IOException, InterruptedException {
        EmbeddedCassandraServerHelper.startEmbeddedCassandra();
        PlatformDataBaseHelper helper = PlatformDataBaseHelper.getCassandraDataSourceHelper(9142, "localhost");
        userRepository = new CassandraUserRepository(helper);
        user = new CassandraUser();
        id = UUID.randomUUID();
        user.setId(id);
        user.setNickName("testNickName");

    }

    @Test
    public void repositoryBasicOperationTest() {
        userRepository.save(user);
        assertThat(userRepository.one(id)).isNotNull();
    }

}
