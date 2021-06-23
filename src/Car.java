public class Car {
    private String color;
    private String no;

    public Car(String color, String no) {
        this.color = color;
        this.no = no;
    }

    public String getColor() {
        return color;
    }

    public String getNo() {
        return no;
    }

    public String toString() {
        return String.format("%s(%s)", this.no, this.color);
    }
}
