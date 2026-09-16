package com.zeshanaslam.actionhealth.support;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;

public class MythicMobsSupport {

    public String getMythicName(Entity entity) {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("MythicMobs");
        if (plugin == null) {
            return null;
        }

        try {
            Object apiHelper = plugin.getClass().getMethod("getAPIHelper").invoke(plugin);
            Boolean isMythicMob = (Boolean) apiHelper.getClass().getMethod("isMythicMob", Entity.class).invoke(apiHelper, entity);
            if (isMythicMob == null || !isMythicMob) {
                return null;
            }
            Object mythicMobInstance = apiHelper.getClass().getMethod("getMythicMobInstance", Entity.class).invoke(apiHelper, entity);
            if (mythicMobInstance == null) {
                return null;
            }

            Object type = mythicMobInstance.getClass().getMethod("getType").invoke(mythicMobInstance);
            if (type == null) {        
                return null;
            }            
            Object internalName = type.getClass().getMethod("getInternalName").invoke(type);
            return internalName instanceof String ? (String) internalName : null;
        } catch (Exception ignored) {
            return null;
        }
    }
}
