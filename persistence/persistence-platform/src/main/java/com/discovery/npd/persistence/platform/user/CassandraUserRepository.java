/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.persistence.platform.user;

import com.discovery.npd.domain.platform.user.User;
import com.discovery.npd.domain.platform.user.UserRepository;
import com.discovery.npd.persistence.CassandraEntityRepository;
import com.discovery.npd.persistence.platform.PlatformDataBaseHelper;
import com.discovery.npd.persistence.platform.UserTable;
import java.util.UUID;

/**
 *
 * @author lili.he
 */
public class CassandraUserRepository
        extends CassandraEntityRepository<UUID, User, CassandraUser>
        implements UserRepository {

    public CassandraUserRepository(PlatformDataBaseHelper dataSourceHelper) {
        mapper = dataSourceHelper.getDataMapper(CassandraUser.class);
        session = mapper.getManager().getSession();
        keySpace = PlatformDataBaseHelper.DATA_BASE;
        tableName = UserTable.TABLE_NAME;
        idColumn = UserTable.ID_COLUMN;
    }

    @Override
    protected CassandraUser parse(User entity) {
        return CassandraUser.clone(entity);
    }

}
