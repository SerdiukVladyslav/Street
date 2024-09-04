package street.classes;

abstract class Building {
    protected String address;

    public Building(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void setFieldsFromString(String data);

    public abstract void displayInfo();
}
