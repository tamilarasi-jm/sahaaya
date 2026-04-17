package com.sahaaya.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sahaaya.model.Reminder;
import com.sahaaya.service.ReminderService;

@Controller
public class ReminderController {

    private final ReminderService service;

    public ReminderController(ReminderService service) {
        this.service = service;
    }

    // 🌟 Landing Page
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // 📊 Dashboard
   @GetMapping("/dashboard")
   public String dashboard(Model model) {
    try {
        model.addAttribute("reminders", service.getAllReminders());
    } catch (Exception e) {
        System.out.println("Error loading reminders: " + e.getMessage());
        model.addAttribute("reminders", new java.util.ArrayList<>());
    }
    return "dashboard";
}

    // 👤 User page
    @GetMapping("/user")
    public String userPage() {
        return "user";
    }

    // Save user
    @PostMapping("/saveUser")
    public String saveUser(@RequestParam String name,
                           @RequestParam int age,
                           jakarta.servlet.http.HttpSession session) {

        session.setAttribute("name", name);
        session.setAttribute("age", age);

        return "redirect:/dashboard"; // 🔥 IMPORTANT
    }

    // ➕ Add form
    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("reminder", new Reminder());
        return "add";
    }

    // Save reminder
    @PostMapping("/add")
    public String addReminder(@ModelAttribute Reminder reminder) {

        service.addReminder(reminder);

        // Schedule reminder at the correct scheduled time
        java.time.LocalDateTime scheduledDateTime = parseScheduledTime(reminder.getScheduledTime());
        if (scheduledDateTime != null) {
            service.scheduleReminder(scheduledDateTime);
        }

        return "redirect:/dashboard"; // 🔥 IMPORTANT
    }
    
    // Helper method to parse time string (e.g., "08:00 AM") to LocalDateTime
    private java.time.LocalDateTime parseScheduledTime(String timeString) {
        try {
            java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("hh:mm a");
            java.time.LocalTime time = java.time.LocalTime.parse(timeString, formatter);
            return java.time.LocalDateTime.of(java.time.LocalDate.now(), time);
        } catch (Exception e) {
            System.out.println("Error parsing scheduled time: " + e.getMessage());
            return null;
        }
    }

    // 🔔 Notification check
    @GetMapping("/checkReminder")
    @ResponseBody
    public String checkReminder() {
        if (ReminderService.reminderTriggered) {
            ReminderService.reminderTriggered = false;
            return "trigger";
        }
        return "none";
    }

    // ✏ Edit
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable String id, Model model) {
        model.addAttribute("reminder", service.getById(id));
        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editReminder(@PathVariable String id,
                               @ModelAttribute Reminder reminder) {
        reminder.setId(id);
        service.updateReminder(reminder);
        return "redirect:/dashboard";
    }

    // ❌ Delete
    @GetMapping("/delete/{id}")
    public String deleteReminder(@PathVariable String id) {
        service.deleteReminder(id);
        return "redirect:/dashboard";
    }

    // ✔ Check-in
    @GetMapping("/checkin/{id}")
    public String checkIn(@PathVariable String id) {
        service.checkIn(id);
        return "redirect:/dashboard";
    }
}