package thinking.in.spring.ioc.overview.domain;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.core.io.Resource;
import thinking.in.spring.ioc.overview.enums.City;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 用户类
 */
public class User implements BeanNameAware {


    private Long id;

    private String name;

    private City city;

    private City[] workCities;


    private List<City> lifeCities;

    private Resource configFileLocation;


    private Company company;


    private Properties context;


    private String contextAsText;


    private transient String beanName;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public City[] getWorkCities() {
        return workCities;
    }

    public void setWorkCities(City[] workCities) {
        this.workCities = workCities;
    }

    public Resource getConfigFileLocation() {
        return configFileLocation;
    }

    public void setConfigFileLocation(Resource configFileLocation) {
        this.configFileLocation = configFileLocation;
    }

    public List<City> getLifeCities() {
        return lifeCities;
    }

    public void setLifeCities(List<City> lifeCities) {
        this.lifeCities = lifeCities;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }


    public Properties getContext() {
        return context;
    }

    public void setContext(Properties context) {
        this.context = context;
    }

    public String getContextAsText() {
        return contextAsText;
    }

    public void setContextAsText(String contextAsText) {
        this.contextAsText = contextAsText;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", city=" + city +
                ", workCities=" + Arrays.toString(workCities) +
                ", lifeCities=" + lifeCities +
                ", configFileLocation=" + configFileLocation +
                ", company=" + company +
                ", context=" + context +
                ", contextAsText='" + contextAsText + '\'' +
                '}';
    }

    /**
     * 通过静态方式创建对象
     *
     * @return
     */
    public static User createUser() {
        User user = new User();
        user.setId(1L);
        user.setName("杨");
        return user;
    }

    @PostConstruct
    public void init(){
        System.out.println("User Bean "+ beanName+" 初始化......");
    }
    @PreDestroy
    public void destroy(){
        System.out.println("User Bean "+ beanName+" 销毁......");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }
}
