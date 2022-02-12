package space.cogito.bubble;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
class Dog {
    private String name;
}

public class LombokTest {
    public static void main(String[] args) {
        Dog d = new Dog();

        d.setName("멍멍");
        System.out.println(d.getName());
    }
}
