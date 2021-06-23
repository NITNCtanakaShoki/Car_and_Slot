public class Slot {
    private Car car;
    private String contColor;
    private String contNo;

    private final String NO_CONTACT_STATUS = "NoCont";

    public Slot() {
        this.car = null;
        this.contColor = NO_CONTACT_STATUS;
        this.contNo = NO_CONTACT_STATUS;
    }

    private boolean isContacted() {
        if ( this.contColor.equals(NO_CONTACT_STATUS) ) return false;
        if ( this.contNo.equals(NO_CONTACT_STATUS) ) return false;
        return true;
    }

    private boolean isExistCar() {
        if ( this.car == null ) return false;
        return true;
    }
    boolean contract( Car car ) {
        if ( this.isContacted() ) return false;

        this.contColor = car.getColor();
        this.contNo = car.getNo();
        return true;
    }

    void cancel() {
        this.contNo = NO_CONTACT_STATUS;
        this.contColor = NO_CONTACT_STATUS;
    }

    boolean carIn( Car car ) {
        if ( this.isExistCar() ) return false;

        this.car = car;
        return true;
    }

    Car carOut(Car car) {
        Car result = new Car( this.car.getColor(), car.getNo() );
        this.car = null;
        return result;
    }

    boolean check() {
        if ( !isExistCar() ) return true;
        if ( !this.car.getColor().equals( this.contColor ) ) return false;
        if ( !this.car.getNo().equals( this.contNo ) ) return false;
        return true;
    }

    public String toString() {
        if ( isExistCar() ) {
            return  String.format(
                    "%s(%s): %s(%s)",
                    this.car.getNo(),
                    this.car.getColor(),
                    this.contNo,
                    this.contColor
            );
        }
        return String.format(
                "no car: %s(%s)",
                this.contNo,
                this.contColor
        );
    }
}
