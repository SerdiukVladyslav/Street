package street.classes;

enum AccreditationLevel {
    GENERAL(100, 500),
    GYMNASIUM(200, 800),
    LYCEUM(300, 1000);

    private final int minStudents;
    private final int maxStudents;

    AccreditationLevel(int minStudents, int maxStudents) {
        this.minStudents = minStudents;
        this.maxStudents = maxStudents;
    }

    public int generateRandomStudentsCount() {
        return (int)(Math.random() * (maxStudents - minStudents + 1)) + minStudents;
    }
}

class School extends Building {
    private AccreditationLevel level;
    private int studentsCount;

    public School(String address, AccreditationLevel level) {
        super(address);
        this.level = level;
        this.studentsCount = level.generateRandomStudentsCount();
    }

    @Override
    public void setFieldsFromString(String data) {
        try {
            String[] parts = data.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid data format. Expected format: 'address, level'.");
            }

            this.address = parts[0].trim();

            try {
                this.level = AccreditationLevel.valueOf(parts[1].trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid accreditation level. Valid values are: GENERAL, GYMNASIUM, LYCEUM.");
            }

            this.studentsCount = this.level.generateRandomStudentsCount();
        } catch (IllegalArgumentException e) {
            System.err.println("Error setting fields from string: " + e.getMessage());
        }
    }

    @Override
    public void displayInfo() {
        System.out.println(level + " School at " + address + " with " + studentsCount + " students.");
    }
}
