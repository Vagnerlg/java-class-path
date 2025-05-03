package developer.entity;

public class Developer {

    private String name;
    private int age;
    private String city;
    private String[] skills;

    public Developer(String name, int age, String city, String[] skills) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.skills = skills;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    public String[] getSkills() {
        return skills;
    }
}