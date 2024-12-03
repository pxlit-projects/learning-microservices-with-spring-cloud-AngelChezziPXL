@echo off
setlocal

REM Set the project name for Docker Compose
set COMPOSE_PROJECT_NAME=java_exam_mysql

REM Run docker-compose up with options to handle cleanup
docker-compose up --abort-on-container-exit

REM Cleanup orphaned containers and stop services on exit
echo Cleaning up orphan containers and stopping services...
docker-compose down --remove-orphans

echo All containers and orphans removed. Press any key to exit.
pause

