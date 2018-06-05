package com.att.eg.profile.mysubscriptions.info.util;

import com.att.eg.monitoring.annotations.statuscodes.CommonStatusCodes;
import com.att.eg.monitoring.yawl.MetaBuilder;
import com.att.eg.monitoring.yawl.YawlLogger;
import com.att.eg.profile.mysubscriptions.info.common.Constants;

import org.slf4j.MDC;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MDCExecutorService extends ThreadPoolExecutor {

    private static final YawlLogger log = new YawlLogger(MDCExecutorService.class);

    // set constants for creating a cached thread pool
    private static final long KEEP_ALIVE_TIME = 0L;
    private static final TimeUnit TIME_UNIT = TimeUnit.MILLISECONDS;
    private final BlockingQueue<Runnable> queue;

    public MDCExecutorService(int threadPoolSize, BlockingQueue<Runnable> blockingQueue) {
        super(threadPoolSize, threadPoolSize, KEEP_ALIVE_TIME, TIME_UNIT, blockingQueue);
        queue = blockingQueue;
        log.info(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setReason("Thread Pool Info")
                .setKeyAndValue("Pool_Size", String.valueOf(threadPoolSize))
                .create());
    }

    @Override
    public void execute(Runnable command) {
        log.debug(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                .setKeyAndValue(Constants.QUEUE_SIZE, String.valueOf(getTaskCount()))
                .create());
        super.execute(addMDCContext(command, MDC.getCopyOfContextMap()));
    }

    public static Runnable addMDCContext(final Runnable runnable, final Map<String, String> context) {
        return new Runnable() { //NOSONAR
            @Override
            public void run() {
                MDC.clear();
                if (context != null) {
                    MDC.setContextMap(context);
                } else {
                    log.warn(CommonStatusCodes.INFORMATIONAL, new MetaBuilder()
                            .setReason("Context is null")
                            .create());
                }
                try {
                    runnable.run();
                } finally {
                    MDC.clear();
                }
            }

        };
    }

    public Integer getQueueSize() {
        return Objects.nonNull(queue) ? queue.size() : 0;
    }
}

