package common;

public enum SearchType {
    ALL_RECORDS("全て表示"),
    RANGE("特定期間を表示");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
