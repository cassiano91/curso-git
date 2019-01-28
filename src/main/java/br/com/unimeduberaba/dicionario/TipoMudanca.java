package br.com.unimeduberaba.dicionario;

public enum TipoMudanca {

	PEQUENA("P"), MEDIA("M"), GRANDE("G");

	private final String code;

	TipoMudanca(String code) {
		this.code = code;
	}

	public static TipoMudanca fromCode(String code) {
		switch (code.toUpperCase()) {
		case "P":
			return PEQUENA;
		case "M":
			return MEDIA;
		case "G":
			return GRANDE;
		default:
			return null;
		}
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		switch (this) {
		case PEQUENA:
			return "Pequena";
		case MEDIA:
			return "Média";
		case GRANDE:
			return "Grande";
		}
		return null;
	}
}