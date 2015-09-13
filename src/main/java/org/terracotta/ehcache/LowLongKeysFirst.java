package org.terracotta.ehcache;

import org.ehcache.Cache;
import org.ehcache.config.Eviction;
import org.ehcache.config.EvictionPrioritizer;

public class LowLongKeysFirst implements EvictionPrioritizer<Object, Object> {

  @Override
  public int compare(Cache.Entry<Object, Object> o1, Cache.Entry<Object, Object> o2) {
    Object k1 = o1.getKey();
    Object k2 = o2.getKey();

    if (k1 instanceof Long && k2 instanceof Long) {
      return Long.compare((Long) k2, (Long) k1);
    } else {
      return Eviction.Prioritizer.LFU.compare(o1, o2);
    }
  }
}
