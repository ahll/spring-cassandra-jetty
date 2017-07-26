/*
 * Copyright (c) 2017, discovery and/or its affiliates. All rights reserved.
 *
 * DISCOVERY SOFTWARE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.discovery.npd.api.concurrent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PausableExecutor extends ScheduledThreadPoolExecutor {

    private Continue cont;

    public PausableExecutor(int corePoolSize, ThreadFactory threadFactory, Continue c) {
        super(corePoolSize, threadFactory);
        cont = c;
    }

    protected void beforeExecute(Thread t, Runnable r) {
        try {
            cont.checkIn();
        } catch (InterruptedException ex) {
            Logger.getLogger(PausableExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }
        super.beforeExecute(t, r);
    }
}
