package com.yugj.ehcache.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Status;
import net.sf.ehcache.event.CacheManagerEventListener;

/**
 * 用于监听CacheManager，增加cache移除cache事件
 * 使用：CacheManager.setCacheManagerEventListener(new MyCacheManagerListener());
 * Created by yugj on 2015/6/30.
 */
public class MyCacheManagerListener implements CacheManagerEventListener{
    @Override
    public void init() throws CacheException {
        System.out.println("MyCacheManagerListener init");
    }

    @Override
    public Status getStatus() {
        return null;
    }

    @Override
    public void dispose() throws CacheException {

    }

    @Override
    public void notifyCacheAdded(String cacheName) {
        System.out.println("notifyCacheAdded:" + cacheName);
    }

    @Override
    public void notifyCacheRemoved(String cacheName) {
        System.out.println("notifyCacheRemoved:" + cacheName);

    }
}
