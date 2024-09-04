package street.classes;

import java.util.Scanner;

class Menu {
    private Street street;
    private Scanner scanner;

    public Menu() {
        this.street = new Street();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n1. Add Residential House");
            System.out.println("2. Add Shop");
            System.out.println("3. Add School");
            System.out.println("4. Remove Building by Address");
            System.out.println("5. Display Street Information");
            System.out.println("6. Find Shops Near House");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addResidentialHouse();
                    break;
                case 2:
                    addShop();
                    break;
                case 3:
                    addSchool();
                    break;
                case 4:
                    removeBuilding();
                    break;
                case 5:
                    street.displayStreetInfo();
                    break;
                case 6:
                    findShopsNearHouse();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addResidentialHouse() {
        System.out.print("Enter address and residents count (format: 'address, residentsCount'): ");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid data format. Expected format: 'address, residentsCount'.");
            }

            String address = parts[0].trim();
            int residentsCount;

            try {
                residentsCount = Integer.parseInt(parts[1].trim());
                if (residentsCount <= 0) {
                    throw new IllegalArgumentException("Residents count must be greater than zero.");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid number format for residents count.", e);
            }

            ResidentialHouse house = new ResidentialHouse(address, residentsCount);
            street.addBuilding(house);
            System.out.println("Residential house added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add residential house: " + e.getMessage());
        }
    }

    private void addShop() {
        System.out.print("Enter address and department count (format: 'address, departmentCount'): ");
        String input = scanner.nextLine();
        try {
            Shop shop = new Shop("", 0);
            shop.setFieldsFromString(input);
            street.addBuilding(shop);
            System.out.println("Shop added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add shop: " + e.getMessage());
        }
    }

    private void addSchool() {
        System.out.print("Enter address and accreditation level (format: 'address, level'): ");
        String input = scanner.nextLine();

        try {
            String[] parts = input.split(",");
            if (parts.length != 2) {
                throw new IllegalArgumentException("Invalid data format. Expected format: 'address, level'.");
            }

            String address = parts[0].trim();
            AccreditationLevel level;

            try {
                String levelInput = parts[1].trim().toUpperCase();
                level = AccreditationLevel.valueOf(levelInput);
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid accreditation level. Valid values are: GENERAL, GYMNASIUM, LYCEUM.");
            }

            School school = new School(address, level);
            street.addBuilding(school);
            System.out.println("School added successfully.");
        } catch (IllegalArgumentException e) {
            System.err.println("Failed to add school: " + e.getMessage());
        }
    }

    private void removeBuilding() {
        System.out.print("Enter address to remove: ");
        String address = scanner.nextLine();
        if (address.isEmpty()) {
            System.err.println("Address cannot be empty.");
            return;
        }
        street.removeBuilding(address);
        System.out.println("Building removed successfully, if it existed.");
    }

    private void findShopsNearHouse() {
        try {
            System.out.print("Enter house address: ");
            String houseAddress = scanner.nextLine();
            System.out.print("Enter search range: ");
            int range = Integer.parseInt(scanner.nextLine().trim());
            if (range < 0) {
                throw new IllegalArgumentException("Range must be a non-negative number.");
            }
            System.out.print("Enter department type: ");
            String departmentType = scanner.nextLine();
            street.findShopsNearHouse(houseAddress, range, departmentType);
        } catch (NumberFormatException e) {
            System.err.println("Invalid number format for range. Please enter a valid integer.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
