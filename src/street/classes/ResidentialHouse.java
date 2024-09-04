package street.classes;

class ResidentialHouse extends Building {
    private int residentsCount;

    public ResidentialHouse(String address, int residentsCount) {
        super(address);
        this.residentsCount = residentsCount;
    }

    @Override
    public void setFieldsFromString(String data) {
        try {
            String[] parts = data.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid data format. Expected format: 'address, residentsCount'.");
            }

            this.address = parts[0].trim();

            try {
                this.residentsCount = Integer.parseInt(parts[1].trim());
                if (this.residentsCount < 0) {
                    throw new IllegalArgumentException("Residents count cannot be negative.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format for residents count.", e);
            }

        } catch (IllegalArgumentException e) {
            System.err.println("Error setting fields from string: " + e.getMessage());
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Residential House at " + address + " with " + residentsCount + " residents.");
    }
}
