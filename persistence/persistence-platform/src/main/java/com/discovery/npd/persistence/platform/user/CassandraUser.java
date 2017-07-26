/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.platform.user;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.discovery.npd.domain.platform.user.User;
import static com.discovery.npd.persistence.platform.PlatformDataBaseHelper.DATA_BASE;
import static com.discovery.npd.persistence.platform.UserTable.TABLE_NAME;
import java.util.UUID;

/**
 *
 * @inheritDoc
 *
 * @author daniel.eguia
 */
@Table(keyspace = DATA_BASE, name = TABLE_NAME)
public class CassandraUser implements User {

    @PartitionKey
    private UUID id;
    private String nickName;

    static CassandraUser clone(User user) {
        CassandraUser cassandraUser = new CassandraUser();
        cassandraUser.id = user.getId();
        cassandraUser.nickName = user.getNickName();
        return cassandraUser;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

}
