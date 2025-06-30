# DAA-LAB-PROGRAMS 🚀

![Java](https://img.shields.io/badge/Java-007396?style=flat&logo=java&logoColor=white) ![Algorithms](https://img.shields.io/badge/Algorithms-and%20Data%20Structures-4B8BBE?style=flat) ![GitHub Releases](https://img.shields.io/badge/Releases-v1.0.0-blue?style=flat&logo=github) 

Welcome to the **DAA-LAB-PROGRAMS** repository! This project provides a complete Java implementation of lab exercises from the Design and Analysis of Algorithms (DAA) course (Course Code: 23CS253). It aligns with the VTU syllabus and covers a range of algorithm strategies including brute-force, divide and conquer, greedy, dynamic programming, and backtracking. 

You can download the latest release [here](https://github.com/mariiarechkova/DAA-LAB-PROGRAMS/releases). Each release includes the code, problem statements, and performance analysis plots.

## Table of Contents

1. [Introduction](#introduction)
2. [Getting Started](#getting-started)
3. [Directory Structure](#directory-structure)
4. [Algorithms Covered](#algorithms-covered)
5. [How to Run the Programs](#how-to-run-the-programs)
6. [Performance Analysis](#performance-analysis)
7. [Contributing](#contributing)
8. [License](#license)
9. [Contact](#contact)

## Introduction

The **DAA-LAB-PROGRAMS** repository is designed for students and professionals who want to deepen their understanding of algorithms. The implementations are straightforward and aim to clarify concepts through practical examples. Each lab exercise is accompanied by a problem statement, making it easier to grasp the application of the algorithms.

## Getting Started

To get started, clone this repository to your local machine using the following command:

```bash
git clone https://github.com/mariiarechkova/DAA-LAB-PROGRAMS.git
```

After cloning, navigate to the project directory:

```bash
cd DAA-LAB-PROGRAMS
```

You can download the latest release [here](https://github.com/mariiarechkova/DAA-LAB-PROGRAMS/releases). Make sure to execute the relevant files for the lab exercises.

## Directory Structure

The repository follows a clear directory structure for easy navigation:

```
DAA-LAB-PROGRAMS/
├── brute_force/
│   ├── Problem1.java
│   ├── Problem2.java
├── divide_and_conquer/
│   ├── MergeSort.java
│   ├── QuickSort.java
├── greedy/
│   ├── KruskalsAlgorithm.java
│   ├── PrimsAlgorithm.java
├── dynamic_programming/
│   ├── DijkstraAlgorithm.java
│   ├── FloydWarshallAlgorithm.java
├── backtracking/
│   ├── NQueens.java
│   ├── SudokuSolver.java
└── README.md
```

## Algorithms Covered

This repository includes implementations of the following algorithms:

- **Brute Force**: Simple and straightforward solutions to problems, focusing on exhaustive search.
- **Divide and Conquer**: Algorithms that break a problem into smaller subproblems, solve them independently, and combine the results.
  - **Merge Sort**: A sorting algorithm that divides the array into halves, sorts them, and merges them back.
  - **Quick Sort**: A fast sorting algorithm that selects a 'pivot' and partitions the array around it.
- **Greedy Algorithms**: Algorithms that make the locally optimal choice at each stage.
  - **Kruskal's Algorithm**: A method for finding the minimum spanning tree of a graph.
  - **Prim's Algorithm**: Another method for finding the minimum spanning tree, focusing on connecting nodes.
- **Dynamic Programming**: Techniques for solving complex problems by breaking them down into simpler subproblems.
  - **Dijkstra's Algorithm**: An algorithm for finding the shortest paths between nodes in a graph.
  - **Floyd-Warshall Algorithm**: A method for finding shortest paths in a weighted graph with positive or negative edge weights.
- **Backtracking**: A method for solving problems incrementally, by trying partial solutions and abandoning them if they fail to satisfy the conditions.
  - **N-Queens Problem**: A classic problem of placing N queens on a chessboard so that no two queens threaten each other.
  - **Sudoku Solver**: A program that solves Sudoku puzzles using backtracking.

## How to Run the Programs

To run any program, follow these steps:

1. Navigate to the directory of the specific algorithm you want to run. For example, to run MergeSort:

```bash
cd brute_force/
```

2. Compile the Java file:

```bash
javac Problem1.java
```

3. Run the compiled Java program:

```bash
java Problem1
```

Make sure you have Java installed on your machine. You can download it from the [official Java website](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

## Performance Analysis

Each algorithm comes with performance analysis plots that illustrate their efficiency. These plots compare execution times and memory usage for various input sizes. You can find these plots in the respective directories for each algorithm.

### Example Plot

![Performance Analysis](https://via.placeholder.com/800x400?text=Performance+Analysis+Plot)

## Contributing

Contributions are welcome! If you want to improve the repository or add new algorithms, feel free to fork the repository and submit a pull request. Please ensure that your code is well-documented and follows the existing coding style.

## License

This project is licensed under the MIT License. You can freely use and modify the code as long as you include the original license.

## Contact

For any questions or suggestions, feel free to reach out:

- **Name**: Your Name
- **Email**: your.email@example.com
- **GitHub**: [Your GitHub Profile](https://github.com/yourusername)

Thank you for visiting the **DAA-LAB-PROGRAMS** repository! You can download the latest release [here](https://github.com/mariiarechkova/DAA-LAB-PROGRAMS/releases) and start exploring the fascinating world of algorithms.