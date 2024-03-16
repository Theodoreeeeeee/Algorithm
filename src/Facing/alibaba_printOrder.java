package Facing;

/**
 * 　类中的初始化顺序(一个类，没有继承关系)
 * 　　静态初始化==>默认初始化==>定义初始化==>{ }==>构造方法初始化
 */
class Ant {
    public Ant() {
        System.out.println("Ant");
    }

    {
        System.out.println("I'm Ant");
    }

    static {
        System.out.println("static Ant");
    }
}

public class alibaba_printOrder extends Ant{
    public alibaba_printOrder() {
        System.out.println("cloud");
    }
    {
        System.out.println("I'm cloud");
    }
    static {
        System.out.println("static cloud");
    }

    public static void main(String[] args) {
        new alibaba_printOrder();
        new alibaba_printOrder();
    }
}
/**
 "static Ant"
 "static cloud"
 "I'm Ant"
 "Ant"
 "I'm cloud"
 "cloud"
 "I'm Ant"
 "Ant"
 "I'm cloud"
 "cloud"
*/

/**
 * static Ant
 * static cloud
 * I'm Ant
 * Ant
 * I'm cloud
 * cloud
 * I'm Ant
 *
 * Ant
 * I'm cloud
 * cloud
 */

