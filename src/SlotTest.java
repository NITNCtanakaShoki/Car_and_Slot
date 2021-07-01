import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SlotTest {
    @Test
    public void 最初の契約() {
        Slot slot = new Slot();
        Car car = new Car("yellow", "1353");

        assertTrue( slot.contract(car) );
    }

    @Test
    public void 連続の契約() {
        Slot slot = new Slot();
        Car car = new Car("yellow", "1353");

        boolean result = slot.contract(car);

        car = new Car( "red", "1345");
        assertFalse( slot.contract(car) );
    }


    @Test
    public void キャンセル() {
        Slot slot = new Slot();

        // 1度契約
        Car car = new Car("yellow", "1353");
        boolean result = slot.contract( car );

        // 契約のキャンセル
        slot.cancel();

        // もう一度契約
        car = new Car( "red", "1345");
        assertTrue(  slot.contract(car) );
    }

    @Test
    public void 最初の入車() {
        Slot slot = new Slot();

        Car car = new Car("yellow", "1353");
        assertTrue( slot.carIn(car) );
    }

    @Test
    public void 連続の入車() {
        Slot slot = new Slot();

        Car car = new Car("yellow", "1353");
        boolean result = slot.carIn(car);

        assertFalse( slot.carIn(car) );
    }

    @Test
    public void 出車() {
        Slot slot = new Slot();

        // in
        Car car = new Car("yellow", "1353");
        boolean result =  slot.carIn(car);

        Car dummy = new Car( "white", "1234");
        // out
        Car outCar = slot.carOut( dummy );
        assertEquals( car.getColor(), outCar.getColor() );
        assertEquals( car.getNo(), outCar.getNo() );

        // in
        assertTrue( slot.carIn(car) );
    }

    @Test
    public void 未契約時の車がない時のチェック() {
        Slot slot = new Slot();
        Car car = new Car("yellow", "1353");

        assertTrue( slot.check() );
    }

    @Test
    public void 未契約時の車がある時のチェック() {
        Slot slot = new Slot();
        Car car = new Car("yellow", "1353");

        boolean result = slot.carIn(car);

        assertFalse( slot.check() );
    }

    @Test
    public void 契約時に契約車がある場合のチェック() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");
        Car car1copy = new Car("yellow", "1353");

        // 契約
        slot.contract( car1 );

        // 同じものをIn
        slot.carIn( car1copy );
        assertTrue( slot.check() );
    }

    @Test
    public void 契約時に色が異なる契約車がある場合のチェック() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");
        Car car2 = new Car("red", "1353");

        // 契約
        slot.contract( car1 );

        // 色が異なるものをIn
        slot.carIn( car2 );
        assertFalse( slot.check() );
    }

    @Test
    public void 契約時に番号が異なる契約車がある場合のチェック() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");
        Car car2 = new Car("yellow", "1111");

        // 契約
        slot.contract( car1 );

        // 番号が異なるものをIn
        slot.carIn(car2);
        assertFalse( slot.check() );
    }

    @Test
    public void 契約時に色も番号も異なる契約車がある場合のチェック() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");
        Car car2 = new Car("red", "1111");

        // 契約
        slot.contract( car1 );

        // 全てが異なるものをIn
        slot.carIn(car2);
        assertFalse( slot.check() );
    }

    @Test
    public void 未契約で車がない時の出力値を確認() {
        Slot slot = new Slot();
        assertEquals("no car: NoCont(NoCont)", slot.toString());
    }

    @Test
    public void 未契約で車がある時の出力値を確認() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");

        slot.carIn(car1);
        assertEquals("1353(yellow): NoCont(NoCont)", slot.toString());
    }

    @Test
    public void 契約済みで車がないときの出力値を確認() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");

        // 契約済みの場合
        slot.contract(car1);
        assertEquals("no car: 1353(yellow)", slot.toString());
    }

    @Test
    public void 契約済みで車がある時の出力値を確認() {
        Slot slot = new Slot();
        Car car1 = new Car("yellow", "1353");
        Car car2 = new Car("blue", "2222");
        // 契約済みの場合
        slot.contract(car1);
        slot.carIn(car2);
        assertEquals("2222(blue): 1353(yellow)", slot.toString());
    }
}
