public class LocationW  {
    private int locationId=2;
    private String country="Nigeria";;
    private String city="Lagos";;
    private String state="Lagos State";;
    private String postalCode="w4k a4a";

    public LocationW(){
        this.locationId=getLocationId();
        this.country=getCountry();
        this.city=getCity();
        this.state=getState();
        this.postalCode=getPostalCode();
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
