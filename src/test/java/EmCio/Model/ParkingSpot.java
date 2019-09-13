package EmCio.Model;

public class ParkingSpot {
    private String name;
    private float feePerHour;

    public ParkingSpot(String name){
        this.name=name;
    }
    public ParkingSpot(String name, float feePerHour){
        this.name=name;
        this.feePerHour=feePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParkingSpot that = (ParkingSpot) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getFeePerHour() {
        return feePerHour;
    }

    public void setFeePerHour(float feePerHour) {
        this.feePerHour = feePerHour;
    }

}
