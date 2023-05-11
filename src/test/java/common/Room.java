package common;

public enum Room {
    R201("201", "2", 15000,	"シングル",	"ここは201号室です"),
    R202("202", "2", 5000, "シングル", "ここは202号室です"),
    R203("203", "2", 13000, "シングル", "ここは203号室です"),
    R204("204", "2", 10000, "シングル", "ここは204号室です"),
    R205("205", "2", 8000, "シングル", "ここは205号室です"),
    R206("206", "2", 18000, "ツイン", "ここは206号室です"),
    R301("301", "3", 12000, "ツイン", "ここは301号室です"),
    R302("302", "3", 15000, "ツイン", "ここは302号室です"),
    R303("303", "3", 16000, "ツイン", "ここは303号室です"),
    R304("304", "3", 10000, "ツイン", "ここは304号室です"),
    R305("305", "3", 17000, "ツイン", "ここは305号室です"),
    R401("401", "4", 22000, "ツイン", "ここは401号室です"),
    R402("402", "4", 8000, "シングル", "ここは402号室です"),
    R403("403", "4", 9000, "シングル", "ここは403号室です");
    public final String roomName;
    public final String floor;
    public final Integer price;
    public final String roomType;
    public final String description;

    Room(String roomName, String floor, Integer price, String roomType, String description) {
        this.roomName = roomName;
        this.floor = floor;
        this.price = price;
        this.roomType = roomType;
        this.description = description;
    }
    public String getRoomName(){
        return this.roomName;
    }
    public String getFloor(){
        return this.floor;
    }
    public Integer getPrice(){
        return this.price;
    }
    public String getRoomType(){
        return this.roomType;
    }
    public String getDescription(){
        return this.description;
    }
}
