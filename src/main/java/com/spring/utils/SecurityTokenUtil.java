package com.spring.utils;

import com.hazelcast.config.HazelCast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;

@Configuration
public class SecurityTokenUtil {
    private static HazelcastInstance hazelCast = HazelCast.instance("app");
    private static IMap<String, Authentication> map = hazelCast.getMap("tokenMap");

    public static void save(String key, Authentication value) {

        map.put(key, value);
    }

    public static Authentication get(String key) {

        return map.get(key);

    }

}
