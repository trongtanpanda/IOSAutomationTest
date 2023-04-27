package common;

public enum SearchType {
    ALL_RECORDS("全記録"),
    RANGE("検索範囲");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
