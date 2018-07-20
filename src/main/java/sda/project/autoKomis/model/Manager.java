package sda.project.autoKomis.model;

public class Manager extends Employee {

    // Tu dodatkowe pola/funkcje jakie może pełnić Manager

    private boolean manager;

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
