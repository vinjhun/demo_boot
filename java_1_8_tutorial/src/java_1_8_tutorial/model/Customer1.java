package java_1_8_tutorial.model;

@FunctionalInterface
public interface Customer1 {
	void getName();
	
	default void log(String str) {
		System.out.println(str);
	}
	
	default void log2(String str) {
		System.out.println(str);
	}
}
