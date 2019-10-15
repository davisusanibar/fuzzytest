package fuzzylogicsystem.beans;

import java.util.List;

public class Meal {
    private String outcomeBMR;
    private List<Diet> diet;

    public Meal(String outcomeBMR, List<Diet> diet) {
        this.outcomeBMR = outcomeBMR;
        this.diet = diet;
    }

    public String getOutcomeBMR() {
        return outcomeBMR;
    }

    public void setOutcomeBMR(String outcomeBMR) {
        this.outcomeBMR = outcomeBMR;
    }

    public List<Diet> getDiet() {
        return diet;
    }

    public void setDiet(List<Diet> diet) {
        this.diet = diet;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "outcomeBMR='" + outcomeBMR + '\'' +
                ", diet=" + diet +
                '}';
    }
}
