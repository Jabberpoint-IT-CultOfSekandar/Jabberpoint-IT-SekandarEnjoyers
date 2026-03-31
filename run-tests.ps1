<#
Simple test runner instructions.

Requirements:
- Download JUnit4 jar (junit-4.13.2.jar) and Hamcrest core (hamcrest-core-1.3.jar)
  and place them in the `lib` directory at repository root, or adjust `$jars` below.

Usage (PowerShell):
  .\run-tests.ps1

This script compiles sources and tests and runs JUnit tests using the junit jar on the classpath.
#>

$ErrorActionPreference = 'Stop'

$libDir = Join-Path $PSScriptRoot 'lib'
if (-not (Test-Path $libDir)) { Write-Host "lib folder not found. Create 'lib' and add junit-4.13.2.jar and hamcrest-core-1.3.jar"; exit 1 }

$jars = Get-ChildItem -Path $libDir -Filter '*.jar' | ForEach-Object { $_.FullName } -join ';'
if (-not $jars) { Write-Host "No jars found in lib"; exit 1 }

Write-Host "Compiling sources..."
javac -d bin src\*.java 2>&1
Write-Host "Compiling tests..."
javac -d bin -cp "bin;$jars" test\*.java 2>&1

Write-Host "Running tests..."
java -cp "bin;$jars" org.junit.runner.JUnitCore PresentationTest CommandTest KeyControllerTest
