package street.classes;

class Shop extends Building {
    private int departmentCount;

    public Shop(String address, int departmentCount) {
        super(address);
        this.departmentCount = departmentCount;
    }

    public int getDepartmentCount()
    {
        return departmentCount;
    }

    public void setDepartmentCount(int departmentCount)
    {
        this.departmentCount = departmentCount;
    }

    @Override
    public void setFieldsFromString(String data) {
        try {
            String[] parts = data.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid data format. Expected format: 'address, departmentCount'.");
            }

            this.address = parts[0].trim();
            this.departmentCount = Integer.parseInt(parts[1].trim());

            if (this.departmentCount <= 0) {
                throw new IllegalArgumentException("Department count must be greater than zero.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for department count.", e);
        }
    }

    @Override
    public void displayInfo() {
        System.out.println("Shop at " + address + " with " + departmentCount + " departments.");
    }
}
