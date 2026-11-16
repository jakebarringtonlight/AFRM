# Defect Observations

ID / Date / Type / Severity / Description / Solution / Status

OBS-01 / 15.11.2026 / Configuration / Medium / 'target/' (compiled classes) was tracked in Git so every build dirtied the working tree / Untracked and added to '.gitignore' in 'v1.1.0-baseline' / Closed

OBS-02 / 15.11.2026 / Documentation / Medium / Mismatch of scenario destination SYD in FlightSimData but SNG in report / 10,600km matches distance to SNG so FlightSimData needs changing from SYD to SNG / Open 

OBS-03 / 15.11.2026 / Documentation / Low / Mismatched wording 'INSUFFICIENT_FUEL_CAPACITY' in report but code states 'INSUFFICIENT_CAPACITY' / Use code wording for consistency / Open

OBS-04 / 15.11.2026 / Requirements / Medium / 5% reserve check is applied throughout the flight while it is supposedly a pre-flight check only / Review code and decide whether to align code with requirement or vice versa / Open

OBS-05 / 15.11.2026 / Process / Low / Git commits do not use consistent formatting or clear description of changes / Process implementation to ensure consistency and clarity / Open