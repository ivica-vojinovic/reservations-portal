package net.ivica.reservations.api.command;

public enum SortDirection {

    ASCENDING("asc"), DESCENDING("desc");

    public static final SortDirection DEFAULT = ASCENDING;

    private String _direction;

    SortDirection(String direction) {
        _direction = direction;
    }

    public String getDirection() {
        return _direction;
    }

}