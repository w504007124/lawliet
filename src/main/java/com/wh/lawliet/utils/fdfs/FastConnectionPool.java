////package com.wh.lawliet.utils.fdfs;
////
////import org.apache.commons.logging.Log;
////import org.apache.commons.logging.LogFactory;
////import org.csource.common.MyException;
////import org.csource.fastdfs.*;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Component;
////
////import java.net.InetSocketAddress;
////import java.util.concurrent.LinkedBlockingQueue;
////import java.util.concurrent.TimeUnit;
////
////
////@Component
//@Deprecated
//public class FastConnectionPool {
////
////    private static final Log logger = LogFactory.getLog(FastConnectionPool.class);
////
////    private FastDFSConfig fastDFSConfig;
////
////    /*空闲的连接池*/
////    private LinkedBlockingQueue<StorageClient1> idleConnectionPool = null;
////    /**
////     * 连接池默认最小连接数
////     */
////    private long minPoolSize = 1;
////
////    /**
////     * 连接池默认最大连接数
////     */
////    private long maxPoolSize = 200;
////
////    /**
////     * 默认等待时间（单位：秒）
////     */
////    private long waitTimes = 25000;
////
////    /**
////     * fastdfs客户端重建连接默认5次
////     */
////    private static final int COUNT = 1;
////
////    TrackerServer trackerServer = null;
////
////    @Autowired
////    public FastConnectionPool(FastDFSConfig fastDFSConfig) {
////        /** 初始化连接池 */
////        this.fastDFSConfig = fastDFSConfig;
////        poolInit();
////        /** 注册心跳 */
////        //HeartBeat beat = new HeartBeat(this);
////        //beat.beat();
////    }
////
////    public FastConnectionPool(long minPoolSize, long maxPoolSize, long waitTimes) {
////        logger.info("[线程池构造方法(ConnectionPool)][默认参数：minPoolSize=" + minPoolSize
////                + ",maxPoolSize=" + maxPoolSize + ",waitTimes=" + waitTimes + "]");
////        this.minPoolSize = minPoolSize;
////        this.maxPoolSize = maxPoolSize;
////        this.waitTimes = waitTimes;
////        /** 初始化连接池 */
////        poolInit();
////        /** 注册心跳 */
////        // HeartBeat beat = new HeartBeat(this);
////        //beat.beat();
////    }
////
////    private void poolInit() {
////        try {
////            /** 加载配置文件 */
////            initClientGlobal();
////            /** 初始化空闲连接池 */
////            idleConnectionPool = new LinkedBlockingQueue<StorageClient1>();
////            /** 初始化忙碌连接池 */
////            // busyConnectionPool = new ConcurrentHashMap<StorageClient1, Object>();
////
////            TrackerClient trackerClient = new TrackerClient();
////            trackerServer = trackerClient.getConnection();
////            int flag = 0;
////            while (trackerServer == null && flag < COUNT) {
////                logger.info("[创建TrackerServer(createTrackerServer)][第" + flag + "次重建]");
////                flag++;
////                initClientGlobal();
////                trackerServer = trackerClient.getConnection();
////            }
////            // 测试 Tracker活跃情况
////            // ProtoCommon.activeTest(trackerServer.getSocket());
////
////            /** 往线程池中添加默认大小的线程 */
////            createTrackerServer();
////        } catch (Exception e) {
////            e.printStackTrace();
////            logger.error("[FASTDFS初始化(init)--异常]");
////        }
////    }
////
////    public void createTrackerServer() {
////
////        logger.info("[创建TrackerServer(createTrackerServer)]");
////        TrackerServer trackerServer = null;
////
////        try {
////
////            for (int i = 0; i < minPoolSize; i++) {
////                // 把client1添加到连接池
////                StorageServer storageServer = null;
////                StorageClient1 client1 = new StorageClient1(trackerServer, storageServer);
////                idleConnectionPool.add(client1);
////            }
////
////        } catch (Exception e) {
////            e.printStackTrace();
////            logger.error("[创建TrackerServer(createTrackerServer)][异常：{}]");
////        }
////
////    }
////
////    public StorageClient1 checkout() {
////
////        StorageClient1 client1 = idleConnectionPool.poll();
////
////        if (client1 == null) {
////            if (idleConnectionPool.size() < maxPoolSize) {
////                createTrackerServer();
////                try {
////                    client1 = idleConnectionPool.poll(waitTimes, TimeUnit.SECONDS);
////                } catch (Exception e) {
////                    e.printStackTrace();
////                    logger.error("[获取空闲连接(checkout)-error][error:获取连接超时:{}]");
////                }
////            }
////        }
////
////        // 添加到忙碌连接池
////        // busyConnectionPool.put(client1, obj);
////        logger.info("[获取空闲连接(checkout)][获取空闲连接成功]");
////        return client1;
////    }
////
////    public void checkin(StorageClient1 client1) {
////
////        logger.info("[释放当前连接(checkin)]");
////
////        client1 = null;
////        if (idleConnectionPool.size() < minPoolSize) {
////            createTrackerServer();
////        }
////
////    }
////
////    private void initClientGlobal()
////            throws Exception {
////        String[] szTrackerServers = fastDFSConfig.getTracker_server().split(";");
////        TrackerGroup g_tracker_group;
////        if (szTrackerServers == null) {
////            throw new MyException("item \"tracker_server\" in app.yml not found");
////        } else {
////            InetSocketAddress[] tracker_servers = new InetSocketAddress[szTrackerServers.length];
////            for (int i = 0; i < szTrackerServers.length; ++i) {
////                String[] parts = szTrackerServers[i].split("\\:", 2);
////                if (parts.length != 2) {
////                    throw new MyException("the value of item \"tracker_server\" is invalid, the correct format is host:port");
////                }
////                tracker_servers[i] = new InetSocketAddress(parts[0].trim(), Integer.parseInt(parts[1].trim()));
////            }
////
////            g_tracker_group = new TrackerGroup(tracker_servers);
////            ClientGlobal.setG_tracker_group(g_tracker_group);
////            ClientGlobal.setG_connect_timeout(fastDFSConfig.getConnectTimeout());
////            ClientGlobal.setG_network_timeout(fastDFSConfig.getNetworkTimeout());
////            ClientGlobal.setG_charset(fastDFSConfig.getCharset());
////
////        }
////    }
////    public LinkedBlockingQueue<StorageClient1> getIdleConnectionPool() {
////        return idleConnectionPool;
////    }
////
////    public long getMinPoolSize() {
////        return minPoolSize;
////    }
////
////    public void setMinPoolSize(long minPoolSize) {
////        if (minPoolSize != 0) {
////            this.minPoolSize = minPoolSize;
////        }
////    }
////
////    public long getMaxPoolSize() {
////        return maxPoolSize;
////    }
////
////    public void setMaxPoolSize(long maxPoolSize) {
////        if (maxPoolSize != 0) {
////            this.maxPoolSize = maxPoolSize;
////        }
////    }
////
////    public long getWaitTimes() {
////        return waitTimes;
////    }
////
////    public void setWaitTimes(int waitTimes) {
////        if (waitTimes != 0) {
////            this.waitTimes = waitTimes;
////        }
////    }
//}
