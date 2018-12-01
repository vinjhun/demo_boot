package java_1_8_tutorial.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class TestBean implements Serializable {

	private BigDecimal decimalTest;
	private String stringTest;
	private Double doubleTest;

	public BigDecimal getDecimalTest() {
		return decimalTest;
	}

	public void setDecimalTest(BigDecimal decimalTest) {
		this.decimalTest = decimalTest;
	}

	public String getStringTest() {
		return stringTest;
	}

	public void setStringTest(String stringTest) {
		this.stringTest = stringTest;
	}

	public Double getDoubleTest() {
		return doubleTest;
	}

	public void setDoubleTest(Double doubleTest) {
		this.doubleTest = doubleTest;
	}

}
