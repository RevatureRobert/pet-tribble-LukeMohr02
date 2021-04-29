package model;

import dao.TribbleDAO;

import java.util.ArrayList;
import java.util.List;

public class Lab {

    private int id;
    private List<Tribble> tribbles;

    public Lab(List<Tribble> tribbles) {
        this.tribbles = tribbles;
    }

    public Lab() {
        this.tribbles = new ArrayList<>();
    }

    public Lab(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Tribble> getTribbles() {
        return tribbles;
    }

    public void setTribbles(List<Tribble> tribbles) {
        this.tribbles = tribbles;
    }

    public void addTribbles(Tribble tribble) {
        this.tribbles.add(tribble);
    }

    @Override
    public String toString() {
        return "Lab{" +
                "id=" + id +
                '}';
    }
}
