package net.ivica.reservations.api.command;

public abstract class AbstractSearchCommand {

    public static final SortDirection DEFAULT_SORT_DIRECTION = SortDirection.ASCENDING;

    public static final int DEFAULT_PAGE = 0;

    public static final int DEFAULT_PAGE_SIZE = 50;

    private Integer _perPage = DEFAULT_PAGE_SIZE;
    private Integer _page = DEFAULT_PAGE;
    private String _sortProperty;
    private SortDirection _sortDirection = DEFAULT_SORT_DIRECTION;

    public Integer getPage() {
        return _page;
    }

    public void setPage(Integer page) {
        _page = page;
    }

    public Integer getPerPage() {
        return _perPage;
    }

    public void setPerPage(Integer perPage) {
        _perPage = perPage;
    }

    public SortDirection getSortDirection() {
        return _sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        _sortDirection = sortDirection;
    }

    public String getSortProperty() {
        return _sortProperty;
    }

    public void setSortProperty(String sortProperty) {
        _sortProperty = sortProperty;
    }

}