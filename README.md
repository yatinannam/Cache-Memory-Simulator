# Cache Memory Design and Optimization

A simple and interactive **Cache Memory Simulator** built using **Java Swing** that demonstrates the internal working of cache memory in computer architecture.  
This project visually shows how **cache hits**, **cache misses**, and **memory block mapping** occur in real-time based on user inputs.

---

## Project Overview

The project simulates **Direct Mapped Cache** and calculates:
- Cache hits and misses
- Hit ratio and miss ratio
- Mapping of memory blocks to cache lines

It provides a user-friendly **GUI interface** where users can enter cache parameters such as cache size, block size, and memory addresses.  
The simulator processes these inputs and displays a detailed log of cache operations step-by-step.

---

## Features

- **Interactive GUI** built using Java Swing  
- Accepts user inputs for cache size, block size, and address sequence  
- Displays hits, misses, and final performance summary  
- Educational visualization of **Direct Mapping**  
- Helps understand **cache design**, **locality**, and **optimization** concepts

---

## Technologies Used

- **Language:** Java  
- **Framework:** Java Swing (GUI)  
- **IDE:** Visual Studio Code / Eclipse / IntelliJ IDEA  
- **Concepts Covered:** Cache Mapping, Cache Hits & Misses, Memory Hierarchy  

---

## How to Run

1. Clone this repository:
```bash
git clone https://github.com/<your-username>/Cache-Memory-Simulator.git
cd Cache-Memory-Simulator
```

2. Compile the program:
```bash
javac CacheMemorySimulator.java
```

3. Run the simulator:
```bash
java CacheMemorySimulator
```

4. Enter:
- Cache Size (e.g., 4)
- Block Size (e.g., 2)
- Memory Addresses (comma-separated, e.g., 2,3,4,5,6,3,8,2,9,4,3,2)
- Click Simulate to view cache operations and results.

---

## Example Output
```bash
CACHE MEMORY SIMULATION (DIRECT MAPPING)
----------------------------------------------------
Access  1: Address 2    -> Block 1   -> MISS
Access  2: Address 3    -> Block 1   -> HIT
Access  3: Address 4    -> Block 2   -> MISS
Access  4: Address 5    -> Block 2   -> HIT
Access  5: Address 6    -> Block 3   -> MISS
Access  6: Address 3    -> Block 1   -> HIT
----------------------------------------------------
Total Accesses: 6
Cache Hits: 3
Cache Misses: 3
Hit Ratio: 50.00%
```

---

## Conclusion

The **Cache Memory Design and Optimization** project demonstrates how cache memory enhances processor performance by reducing memory access time.<br>

Through an interactive Java Swing simulator, it effectively visualizes cache operations such as hits, misses, and block mapping, helping users understand key concepts of memory hierarchy and optimization.<br>

This project bridges theoretical knowledge with practical application, serving as an educational tool for students studying Computer Organization and Architecture.

---
