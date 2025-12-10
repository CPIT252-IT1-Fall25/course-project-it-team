[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/Fv869B0L)
# SmartExpenseTracker

A simple Java application that helps users calculate, store, and display their daily and monthly expenses.  
The system provides an easy interface to add new expense records, filter them, save notes, and manage the remaining balance.

---

## Features
- Add and store new expense records.
- Display all expenses in a clear interface.
- Filter expenses by category or date.
- Save notes and summaries using a Singleton note writer.
- Track remaining balance and savings.
- GUI built with Java Swing.



## Design Patterns Used in SmartExpenseTracker

### 1) Singleton Pattern
**Where it was used:**  
- Used in the `NoteWriter` class to ensure that only one instance handles writing notes to the file.  
This prevents multiple objects from writing simultaneously and keeps file writing consistent.


### 2) Builder Pattern
**Where it was used:**  
- Used (implicitly or explicitly) in the construction of `DailyRecord` objects.  
The class includes multiple fields (amount, category, date, note), which makes it suitable for a builder-style creation pattern.  
This allows clean and flexible creation of expense records without needing many constructors.


### 3) Strategy Pattern
**Where it was used:**  
- Implemented in the `RecordFilter` class to support different filtering strategies—such as filtering by category or date.  
This lets the program switch between filtering methods without modifying the main logic.

---

**LLM Usage Disclosure:**  
AI assistance (ChatGPT) was used to help design and implement parts of the graphical user interface (GUI), as well as to provide guidance during the creation of the `RecordFilter` class and its filtering logic. All generated content was reviewed, modified, and approved by the development team.

---

## License
The primary purpose of this project is to practice and demonstrate the use of software design patterns in an educational context.

---

##  Contribution Statement

