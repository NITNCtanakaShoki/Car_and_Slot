public class Slot {
    private Car car;
    private String contColor;
    private String contNo;

    private final String NO_CONTACT_STATUS = "NoCont";

    public Slot() {
        car       = null;
        contColor = NO_CONTACT_STATUS;
        contNo    = NO_CONTACT_STATUS;
    }

    boolean contract( Car car ) {
        if ( isHaveContact() ) return false;

        contactWork( car );
        return true;
    }

    void cancel() {
        contNo    = NO_CONTACT_STATUS;
        contColor = NO_CONTACT_STATUS;
    }

    boolean carIn( Car car ) {
        if ( this.isExistCar() ) return false;

        this.car = car;
        return true;
    }

    Car carOut(Car car) {
        Car result = new Car( this.car.getColor(), this.car.getNo() );
        this.car = null;
        return result;
    }

    boolean check() {
        if ( !isExistCar() ) return true;
        return isSameParkedCarAndContactCar();
    }

    public String toString() {
        if ( isExistCar() ) {
            return  String.format(
                    "%s(%s): %s(%s)",
                    car.getNo(),
                    car.getColor(),
                    contNo,
                    contColor
            );
        }
        return String.format(
                "no car: %s(%s)",
                contNo,
                contColor
        );
    }

    private boolean isHaveContact() {
        if ( contColor.equals( NO_CONTACT_STATUS ) ) return false;
        if ( contNo.equals( NO_CONTACT_STATUS ) ) return false;
        return true;
    }

    private void contactWork(Car car) {
        contColor = car.getColor();
        contNo = car.getNo();
    }

    private boolean isExistCar() {
        return car != null;
    }

    private boolean isSameParkedCarAndContactCar() {
        if ( !car.getColor().equals( contColor ) ) return false;
        if ( !car.getNo().equals( contNo ) ) return false;
        return true;
    }

}
