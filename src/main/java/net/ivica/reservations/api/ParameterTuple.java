package net.ivica.reservations.api;

public class ParameterTuple {

    private String _key;
    private String _value;

    public ParameterTuple(String key, String value) {
        _key = key;
        _value = value;
    }

    public String getKey() {
        return _key;
    }

    public String getValue() {
        return _value;
    }

}
