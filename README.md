# 🌸 Sahaaya – Smart Elderly Care Reminder System

## 📌 Project Overview

Sahaaya is a smart elderly care reminder platform designed to help senior citizens and caregivers manage medicine schedules, daily routines, and wellness tasks efficiently.

The system allows users to create reminders, track completion status, and manage schedules through a simple and user-friendly dashboard.

---

## 🎯 Problem Statement

Many elderly people forget medicines, appointments, hydration, or daily routines. Caregivers may also find it difficult to monitor tasks consistently.

Sahaaya solves this by providing:

- Timely reminders
- Easy task management
- Status tracking
- Cloud-backed storage
- Accessible interface for elderly users

---

## 🚀 Key Features

### 👤 User Onboarding
- Enter user details (name, age)
- Personalized dashboard greeting

### ⏰ Reminder Management
- Add reminders
- Edit reminders
- Delete reminders
- Mark reminders as completed

### 📊 Smart Dashboard
- View all reminders
- Status badges:
  - Pending
  - Completed
  - Missed
- Live clock display

### 🔔 Notifications
- Browser notification support
- Reminder trigger simulation

### 🔊 Accessibility
- Voice reminder support (browser speech synthesis)

### ☁ Cloud Integration
- Redis used for fast reminder storage and retrieval
- Deployable on cloud platforms

---

## 🛠 Tech Stack

| Layer | Technology |
|------|------------|
| Frontend | HTML, CSS, JavaScript |
| Backend | Java Spring Boot |
| Database | Redis |
| Build Tool | Maven |
| Version Control | Git + GitHub |
| Cloud | Azure App Service / Azure Redis |

---

## 🏗 Project Architecture

User → Web Interface → Spring Boot Backend → Redis Database → Cloud Hosting

---

## 📁 Project Structure

```text
Sahaaya/
│── src/main/java/com/sahaaya/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   └── model/
│
│── src/main/resources/
│   ├── templates/
│   │   ├── index.html
│   │   ├── user.html
│   │   ├── dashboard.html
│   │   ├── add.html
│   │   └── edit.html
│   └── static/css/style.css
│
│── pom.xml
│── README.md
