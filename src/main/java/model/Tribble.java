package model;

public class Tribble {

    private int id;
    private Lab lab;
    private int fluffometer;
    private String name;
    private String color;

    public Tribble(Lab lab, int fluffometer, String name, String color) {
        this.lab = lab;
        this.fluffometer = fluffometer;
        this.name = name;
        this.color = color;
    }

    public Tribble() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }

    public int getFluffometer() {
        return fluffometer;
    }

    public void setFluffometer(int fluffometer) {
        this.fluffometer = fluffometer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Tribble{" +
                "id=" + id +
                ", lab=" + lab.getId() +
                ", fluffometer=" + fluffometer +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
