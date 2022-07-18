package br.com.compass.avaliacao4.Enum;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino");

    private String value;

    Sexo(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    @Override
    public String toString() {
        return value;
    }
}
