package com.yugj.ehcache.listener;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;


/**
 * 监听cache操作事件
 * 使用：Cache.getCacheEventNotificationService().registerListener(new MyCacheListener());
 * Created by yugj on 2015/6/30.
 */
public class MyCacheListener implements CacheEventListener {


    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
        System.out.println(cache.getName() + " remove -> " + element.getObjectKey());
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
        System.out.println(cache.getName() + " put -> " + element.getObjectKey());
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
        System.out.println(cache.getName() + " update -> " + element.getObjectKey());
    }

    /**
     * 过期
     * @param cache
     * @param element
     */
    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        System.out.println(cache.getName() + " expired -> " + element.getObjectKey());
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        System.out.println(cache.getName() + " evicted -> " + element.getObjectKey());
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
        System.out.println(cache.getName() + " removeAll -> ");
    }

    @Override
    public void dispose() {
        System.out.println("dispose");
    }

    public Object clone() {
        return null;
    }

}
