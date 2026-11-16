# AFRM
Aircraft Fuel Range Monitoring system prototype for Assignment 1 of Aerospace Application Development module.

# Tools / Versions

OpenJDK / 25.0.2
Apache Maven / 3.9.12
JUnit / 5.13.4
Maven Surefire / 3.5.3


# Build, test, run

```bash
# Compile and run unit tests.
mvn clean test

# Build the jar file.
mvn clean package

# Run the scenarios in sequence.
java -jar target/afrm-1.1.0.jar
```

# Scenarios
ID / Route / Initial Fuel (kg) / Distance (km) / Event
normal / BRS to JFK / 17000 / 3380 / Not Applicable
headwind / BRS to JFK / 17000 / 3380 / 200 kmh headwind.
leak / BRS to JFK / 17000 / 3380 / +2400 kgh burn at 1.5 hours. 
tight / BRS to MCO / 18500 / 3900 /  180 kmh headwind.
infeasible / BRS to SYD / 19000 / 10600 / Not Applicable


# Version history

Version / Tag / Comment

1.0 / 'v1.0-assignemnt1-submission' / Assignment 1 as submitted
1.1.0 / 'v.1.1.0-baseline' / Controlled baseline for Assignment 2