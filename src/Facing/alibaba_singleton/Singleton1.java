package Facing.alibaba_singleton;

/**
 * 写出一个你认为最好的单例模式
 * 单例模式其实简单的来说就是一个类在整个应用程序有且只有一个实例在运行
 * 私用的构造函数，因为要确保在整个应用程序运行的时候只存在一个单例，首先必须要先保证该类不能被实例化，否则会产生多个实例，也就违反了单例的原则
 * 自行创建实例，提供一个方法给我们可以调用到这个唯一的实例，既然是私有的构造的函数，那也就意味着不能去实例化，所以要提供一个入口给调用者能够获取到这个唯一的实例
 */
public class Singleton1 {
    private static Singleton1 instance = new Singleton1();

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        return instance;
    }

    public static void print() {
        System.out.println("这是一个单例模式");
    }
}