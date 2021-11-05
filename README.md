# Sprite Cloud Test Automation Framework

---

## Prerequisite
- JAVA
- MAVEN
- IDE (IntelliJ Idea / Eclipse)
- Docker / Docker Compose

---

## Confluence documentation
[Super cool docs](https://dewald-personal.atlassian.net/wiki/spaces/SC/pages/196609/Sprite+Cloud+Test+Automation+Framework)

---

## Local project setup:
1. Git clone repository to your local
```bash
Clone repository
```
2. Navigate to project root
```bash
cd <path.to.repo.root>
```
3. Navigate to jenkins-docker folder
```bash
cd ./jenkins-docker
```
4. Run docker compose up command, this will setup Jenkins and Selenium Grid.
NOTE: Jenkins have a volume attached to the jenkins-backup folder.
```bash
docker-compose up -d
```
5. Verify Jenkins has launched successfully
```bash
URL: http://localhost:8083
```
6. Verify Selenium grid has lanched successfully
```bash
URL: http://localhost:4444
```

---

## Jenkins login
```
Username: admin
Password: admin123
```

---

## Usage

### From intellij IDE:
```
Step 1: Find TestNG XML - src/test/resources/SpriteCloudAllTests.xml
Step 2: Right click and run
```

### From CLI:
```
Step 1: Navigate to project root
Step 2: Run command - mvn clean install surefire-report:report -Dfile=src/test/resources/SpriteCloudAllTests.xml
```

### From Jenkins:
```
Step 1: Login to Jenkins (localhost:8083)
Step 2: Go into job
Step 3: Select build with parameters
Step 4: Select a option and run
```

---

## Reporting

Currently Calliope upload will only trigger automatically through Jenkins pipeline build

[Calliope Dashboard](https://app.calliope.pro/companies/1072)
