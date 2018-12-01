package java_1_8_tutorial.model;

public interface Customer2 {
	void getName();
	
	default String test() {
		return "i";
	}
}
