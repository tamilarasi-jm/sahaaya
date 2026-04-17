package com.sahaaya.repository;

import com.sahaaya.model.Reminder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class ReminderRepository {

    private static final String KEY_PREFIX = "reminder:";
    private final RedisTemplate<String, Object> redisTemplate;

    public ReminderRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void save(Reminder reminder) {
        redisTemplate.opsForValue().set(KEY_PREFIX + reminder.getId(), reminder);
    }

    public Reminder findById(String id) {
        return (Reminder) redisTemplate.opsForValue().get(KEY_PREFIX + id);
    }

    public List<Reminder> findAll() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        List<Reminder> reminders = new ArrayList<>();
        if (keys != null) {
            for (String key : keys) {
                Reminder r = (Reminder) redisTemplate.opsForValue().get(key);
                if (r != null) reminders.add(r);
            }
        }
        return reminders;
    }

    public void delete(String id) {
        redisTemplate.delete(KEY_PREFIX + id);
    }
}