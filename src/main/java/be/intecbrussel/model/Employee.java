package be.intecbrussel.model;

public class Employee {

    private int id;
    private String name;
    private String jobTitle;
    private Company company;

    public Employee() {

    }

    public Employee(String name, String jobTitle, Company company) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", company=" + company +
                '}';
    }
}
