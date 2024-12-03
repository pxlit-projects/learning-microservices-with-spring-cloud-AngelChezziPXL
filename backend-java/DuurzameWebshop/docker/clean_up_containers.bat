@echo off
setlocal

REM Set the project name for Docker Compose
set COMPOSE_PROJECT_NAME=java_exam_mysql

REM Clean up containers, volumes, and orphan resources
docker-compose down --remove-orphans

REM Notify the user
echo All containers, networks, and orphans have been cleaned up.
pause
