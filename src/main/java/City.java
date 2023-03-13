import java.util.Objects;

public class City {

    private int idCity;
    private String cityName;

    public City(int idCity, String cityName) {
        this.idCity = idCity;
        this.cityName = cityName;
    }

    public int getIdCity() {
        return idCity;
    }

    public void setIdCity(int idCity) {
        this.idCity = idCity;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return idCity == city.idCity && Objects.equals(cityName, city.cityName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCity, cityName);
    }

    @Override
    public String toString() {
        return "City{" +
                "idCity=" + idCity +
                ", cityName='" + cityName + '\'' +
                '}';
    }
}
