package common;

public enum Room {
    R201("201", "2", 15000,	"シングル",	"シングルの部屋は、床面積：25m²"),
    R202("202", "2", 5000, "シングル", "シングルの部屋は、床面積：25m²"),
    R203("203", "2", 13000, "シングル", "シングルの部屋は、床面積：25m²"),
    R204("204", "2", 10000, "シングル", "シングルの部屋は、床面積：25m²"),
    R205("205", "2", 8000, "シングル", "シングルの部屋は、床面積：25m²"),
    R206("206", "2", 18000, "ツイン", "シングルの部屋は、床面積：25m²"),
    R301("301", "3", 12000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R302("302", "3", 15000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R303("303", "3", 16000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R304("304", "3", 10000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R305("305", "3", 17000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R401("401", "4", 22000, "ツイン", "ツインの部屋は、床面積：30m²"),
    R402("402", "4", 8000, "シングル", "シングルの部屋は、床面積：25m²"),
    R403("403", "4", 9000, "シングル", "シングルの部屋は、床面積：25m²"),
    R404("404", "4", 8000, "シングル", "シングルの部屋は、床面積：25m²");
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
