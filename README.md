# A1-HaizhuangZhao25259735 — Health Service Appointment System

PROG2004 Object Oriented Programming — Assessment 1

## Overview

A Java program that models a simple appointment system for a health service.
There is no UI, login or data persistence — the program is a set of
well-designed classes plus a scripted simulation in `main` that exercises them.

## Project structure

```
src/
├── AssignmentOne.java        # main method — Parts 3 & 6 demonstration
├── HealthProfessional.java   # Part 1 — abstract base class
├── GeneralPractitioner.java  # Part 2 — concrete type
├── Specialist.java           # Part 2 — concrete type
├── Surgeon.java              # Part 2 — concrete type
├── Nurse.java                # Part 2 — concrete type
├── Patient.java              # Part 4 — patient details
├── Appointment.java          # Part 4 — an appointment (Comparable)
└── Clinic.java               # Part 5 — appointment management
```

## How to run

Compile:

```
javac src/*.java
```

Run:

```
java -cp src AssignmentOne
```

## Class design

- **HealthProfessional** (abstract): id, name, working days. Encapsulated with
  private fields, getters, validation, `toString`, `equals`/`hashCode` on id,
  abstract `getProfessionType()`.
- **GeneralPractitioner / Specialist / Surgeon / Nurse**: inherit the base
  class and add type-specific data and behaviour.
- **Patient**: name and phone number.
- **Appointment**: links a `Patient` to any `HealthProfessional` at a time
  (stored as minutes since midnight); implements `Comparable`.
- **Clinic**: manages appointments — add (with double-booking and opening-hours
  checks), view all / filter by professional id or patient phone, sort by time,
  cancel by professional+time or by patient phone.
