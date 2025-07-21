#!/bin/bash

echo "Compiling Java files..."
cd src || exit
javac gradeFormatter/*.java || { echo "Compilation failed"; exit 1; }

echo "Running program..."
java -ea gradeFormatter.MainApp

