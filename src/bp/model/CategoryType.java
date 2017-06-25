package bp.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public enum CategoryType {
    FOOD_AND_GROCERIES("Food"),
    HOUSING("Housing"),
    UTILITIES("Utilities"),
    HEALTH_CARE("Health care"),
    PERSONAL_CARE("Personal care"),
    ENTERTAINMENT("Entertainment"),
    EDUCATION("Education"),
    CONSUMER_DEBT("Debt"),
    OTHER("Other");

    private String name;

    CategoryType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }


    public static CategoryType fromName(String name) {
        for (CategoryType category : CategoryType.values()) {
            if (Objects.equals(category.name, name)) {
                return category;
            }
        }
        return null;
    }

    public static List<String> getNames() {
        List<String> categoryNames = new ArrayList<>();

        for (CategoryType categoryType : CategoryType.values()) {
            categoryNames.add(categoryType.name);
        }
        return categoryNames;

    }

}
