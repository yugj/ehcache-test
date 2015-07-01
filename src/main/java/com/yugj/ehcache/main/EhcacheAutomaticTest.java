package com.yugj.ehcache.main;

import java.rmi.RemoteException;
import java.util.Scanner;

import com.yugj.ehcache.listener.MyCacheListener;
import com.yugj.ehcache.utils.EhCacheUtils;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;


/**
 * 自动发现测试
 */
public class EhcacheAutomaticTest {

    private static CacheManager manager = null;
    private static Cache cache = null;

    static {
        //System.setProperty("net.sf.ehcache.skipUpdateCheck", "true");
        manager = CacheManager.newInstance("./src/main/resources/ehcache-automatic.xml");
        cache = manager.getCache("myCache");
        /**
         * 注册cache监听器，监听cache事件，ru：put、update、。。。
         */
        cache.getCacheEventNotificationService().registerListener(new MyCacheListener());
    }

    @SuppressWarnings("resource")
    public static void main(String[] args) throws RemoteException {
        Scanner scan = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("******************************************************");
                System.out.println("*  Ehcache Automatic Peer Discovery Test");
                System.out.println("*   - use [get:key] get element from cache eg: get:key");
                System.out.println("*   - use [put:key:value] put element into cache eg: put:key:value");
                System.out.println("*   - use [remove:key] remove element from cache eg: remove:key");
                System.out.println("******************************************************");
                System.out.print("please input:");
                String next = scan.nextLine();
                System.out.println();

                if (next.startsWith("put")) {
                    String[] eles = next.split(":");
                    cache.put(new Element(eles[1], eles[2]));
                    System.out.println("------------------------------------------------------");
                    System.out.println(">> put " + eles[1] + ":" + eles[2] + " to cache");
                    System.out.println("All keys :" + cache.getKeys());
                    System.out.println("------------------------------------------------------\n");
                } else if (next.startsWith("get")) {
                    String[] eles = next.split(":");
                    System.out.println("------------------------------------------------------");
                    System.out.println(">> get " + eles[0] + " from cache:" + cache.get(eles[1]));
                    System.out.println("All keys :" + cache.getKeys());
                    System.out.println("------------------------------------------------------\n");
                } else if (next.startsWith("remove")) {
                    String[] eles = next.split(":");
                    System.out.println("------------------------------------------------------");
                    System.out.println(">> remove " + eles[1] + " from cache " + cache.remove(eles[1]));
                    System.out.println("All keys :" + cache.getKeys());
                    System.out.println("------------------------------------------------------\n");
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println("wrong input!");
                continue;
            }
        }

    }
}