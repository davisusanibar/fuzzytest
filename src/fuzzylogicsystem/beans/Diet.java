package fuzzylogicsystem.beans;

import java.util.List;

public class Diet {
    private String name;
    private int totalCalories;
    private List<String> items;

    public Diet(String name, int totalCalories, List<String> items) {
        this.name = name;
        this.totalCalories = totalCalories;
        this.items = items;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(int totalCalories) {
        this.totalCalories = totalCalories;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Diet{" +
                "name='" + name + '\'' +
                ", totalCalories=" + totalCalories +
                ", items=" + items +
                '}';
    }
}
