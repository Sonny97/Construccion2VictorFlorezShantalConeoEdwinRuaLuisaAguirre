package app.adapter.in.validators;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class SimpleValidator {

	public String stringValidator(String element, String value) throws Exception {
		if (value == null || value.equals("")) {
			throw new Exception(element + " no puede tener un valor vacio o nulo");
		}
		return value;
	}

	public int integerValidator(String element, String value) throws Exception {
		stringValidator(element, value);
		try {
			int intValue = Integer.parseInt(value);
			return intValue;
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public long longValidator(String element, String value) throws Exception {
		stringValidator(element, value);
		try {
			long longValue = Long.parseLong(value);
			return longValue;
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public double doubleValidator(String element, String value) throws Exception {
		stringValidator(element, value);
		try {
			double doubleValue = Double.parseDouble(value);
			return doubleValue;
		} catch (Exception e) {
			throw new Exception(element + " debe ser un valor numerico");
		}
	}

	public LocalDate localDateValidator(String element, String value) throws Exception {
		stringValidator(element, value);
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate dateValue = LocalDate.parse(value, formatter);
			return dateValue;
		} catch (Exception e) {
			throw new Exception(element + " debe tener el formato DD/MM/YYYY");
		}
	}

}
