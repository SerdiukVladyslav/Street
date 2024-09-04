package street.classes;

import java.util.ArrayList;
import java.util.List;

class Street {
    private List<Building> buildings;

    public Street() {
        this.buildings = new ArrayList<>();
    }

    public void addBuilding(Building building) {
        buildings.add(building);
    }

    public void removeBuilding(String address) {
        buildings.removeIf(b -> b.getAddress().equals(address));
    }

    public void displayStreetInfo() {
        for (Building building : buildings) {
            building.displayInfo();
        }
    }

    public void findShopsNearHouse(String houseAddress, int range, String departmentType) {
        int houseIndex = -1;

        for (int i = 0; i < buildings.size(); i++) {
            if (buildings.get(i).getAddress().equals(houseAddress)) {
                houseIndex = i;
                break;
            }
        }

        if (houseIndex == -1) {
            System.out.println("House not found on the street.");
            return;
        }

        System.out.println("Shops near " + houseAddress + " with department type " + departmentType + ":");
        for (int i = Math.max(0, houseIndex - range); i <= Math.min(buildings.size() - 1, houseIndex + range); i++) {
            if (buildings.get(i) instanceof Shop) {
                Shop shop = (Shop) buildings.get(i);
                if (shop.getDepartmentCount() == 1 || departmentType.equalsIgnoreCase("general")) {
                    shop.displayInfo();
                }
            }
        }
    }
}
