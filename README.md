# Rocket Stage Delta-V & Orbital Feasibility Calculator

A lightweight, object-oriented Java console application designed to calculate multi-stage rocket velocity changes ($\Delta v$) and assess Low Earth Orbit (LEO) insertion feasibility using Tsiolkovsky's Rocket Equation.

---

## Features
- **Launch Vehicle Inventory:** Register single-stage (SSTO) or multi-stage launch vehicles with custom dry masses, propellant masses, and engine $I_{sp}$.
- **Tsiolkovsky Physics Engine:** Calculates stage-by-stage burnouts, mass decay, structural payload limits, and total cumulative $\Delta v$.
- **Orbital Feasibility Assessment:** Automatically benchmarks total velocity output against the standard Low Earth Orbit requirement ($9,300 \text{ m/s}$).
- **Crash-Resilient Console UI:** Built-in input validation to handle incorrect data types and invalid numerical entries safely.

---

## Technical Architecture
The project consists of 4 core Java classes located inside the `src/` directory for minimal footprint and clean separation of concerns:

| Class | Description |
| :--- | :--- |
| `Stage.java` | Model representing individual stage dry mass, propellant mass, and specific impulse ($I_{sp}$). |
| `Rocket.java` | Model representing the complete launch vehicle holding a list of stages and structural limits. |
| `DeltaVCalculator.java` | Physics computation engine implementing logarithmic rocket equations. |
| `MainApp.java` | Entry point handling menu loops, user interactions, and input validation. |

---

## Prerequisites
- **Java Development Kit (JDK):** Version 17 or higher.
- **Terminal / Command Prompt / Shell**
- **Any compiler of choice like Google Collab or VS Code**
---

## How to Build & Run

1. Clone the Repository
   ```bash
   git clone [https://github.com/your-username/rocket-deltav-calculator.git](https://github.com/your-username/rocket-deltav-calculator.git)
   cd rocket-deltav-calculator
2. Save all the required files into the computer
3. Open the compiler
4. Run the Program 'MainApp.java' 
5. Select the required option and input all the required values
6. Run the program for the desired output
