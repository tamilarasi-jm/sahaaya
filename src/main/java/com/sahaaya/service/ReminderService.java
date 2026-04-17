package com.sahaaya.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.sahaaya.model.Reminder;
import com.sahaaya.repository.ReminderRepository;

@Service
public class ReminderService {

    private final ReminderRepository repository;

    private final ScheduledExecutorService scheduler =
            Executors.newScheduledThreadPool(1);

    public static boolean reminderTriggered = false;

    public ReminderService(ReminderRepository repository) {
        this.repository = repository;
    }

    public void addReminder(Reminder reminder) {
        reminder.setId(UUID.randomUUID().toString());
        reminder.setStatus("Pending");

        if (reminder.getCreatedAt() == null || reminder.getCreatedAt().isEmpty()) {
            reminder.setCreatedAt(LocalDateTime.now().toString());
        }

        repository.save(reminder);
    }

    public List<Reminder> getAllReminders() {
        return repository.findAll();
    }

    public Reminder getById(String id) {
        return repository.findById(id);
    }

    public void updateReminder(Reminder reminder) {
        repository.save(reminder);
    }

    public void deleteReminder(String id) {
        repository.delete(id);
    }

    public void checkIn(String id) {
        Reminder reminder = repository.findById(id);

        if (reminder != null) {
            reminder.setStatus("Completed");
            repository.save(reminder);
        }
    }

    // simplified for deadline demo
    public void detectMissedReminders() {
        List<Reminder> all = repository.findAll();

        for (Reminder r : all) {
            if ("Pending".equals(r.getStatus())) {
                r.setStatus("Missed");
                repository.save(r);
            }
        }
    }

    public void scheduleReminder(LocalDateTime reminderTime) {

        long delay = Duration.between(
                LocalDateTime.now(),
                reminderTime
        ).toMillis();

        if (delay < 0) {
            System.out.println("Time already passed");
            return;
        }

        scheduler.schedule(() -> {
            System.out.println("🔔 Reminder triggered!");
            reminderTriggered = true;
        }, delay, TimeUnit.MILLISECONDS);
    }
}