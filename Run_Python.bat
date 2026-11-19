@echo off
title Online Food Ordering - Python Analysis

cd /d "%~dp0"

echo Running Sales Analysis...
py .\FDS-Visualization\sales_analysis.py
if errorlevel 1 goto error

echo.
echo Running Data Preprocessing...
py .\FDS-Python\retrieve_preprocess.py
if errorlevel 1 goto error

echo.
echo Creating Visualizations...
py .\FDS-Visualization\create_visualizations.py
if errorlevel 1 goto error

echo.
echo All Python tasks completed successfully!
pause
exit /b 0

:error
echo.
echo A Python task failed. Check the error above.
pause
exit /b 1