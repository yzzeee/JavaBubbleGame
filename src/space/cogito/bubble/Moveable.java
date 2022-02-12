package space.cogito.bubble;

public interface Moveable {
    public abstract void left();

    public abstract void right();

    public abstract void up();

    // 인터페이스가 너무 많은 행위의 제약을 걸고 있으면 사용이 어려워서
    // 예전에는 Adapter 패턴을 이용해서 상속을 하도록 하여 필요한 메소드만 오버라이딩 하는 형태를 구현했었다.
    // 그런데 Java는 다중 상속이 되지 않으므로 어댑터 패턴 보다는 default를 사용하는 것이 좋다.
    // Java 8에서 인터페이스에 default 메소드를 구현할 수 있게되었다.
    // default를 사용하면 interface에서 몸체가 있는 메서드를 만들 수 있다.
    default public void down() {
    }

}
