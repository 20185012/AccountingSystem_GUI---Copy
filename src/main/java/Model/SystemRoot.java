package Model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class SystemRoot implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int systemId;
    private String companyName;

    @OneToMany
    private List<Category> rootCategories;

    @OneToMany
    private List<User> users;


    public SystemRoot(int systemId, String companyName, List<Category> rootCategories, List<User> users) {
        this.systemId = systemId;
        this.companyName = companyName;
        this.rootCategories = rootCategories;
        this.users = users;
    }

    public SystemRoot(String companyName, List<Category> rootCategories, List<User> users) {
        this.companyName = companyName;
        this.rootCategories = rootCategories;
        this.users = users;
    }

    public SystemRoot() {
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public List<Category> getRootCategories() {
        return rootCategories;
    }

    public void setRootCategories(List<Category> rootCategories) {
        this.rootCategories = rootCategories;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SystemRoot{" +
                "systemId=" + systemId +
                ", companyName='" + companyName + '\'' +
                ", rootCategories=" + rootCategories +
                ", users=" + users +
                '}';
    }
}
