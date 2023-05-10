package common;

public class Room {
    public String roomName, typeRoom, floor, description;
    public int price;
    Room(String roomName, String typeRoom, String floor, String description, int price){
        this.roomName = roomName;
        this.typeRoom = typeRoom;
        this.floor = floor;
        this.description = description;
        this.price = price;
    }

    Room(String roomName, String typeRoom, int price){
        this.roomName = roomName;
        this.typeRoom = typeRoom;
        this.price = price;
    }
    public String getRoomName() {
        return roomName;
    }
    public String getTypeRoom(){
        return typeRoom;
    }
    public String getFloor(){
        return floor;
    }
    public int getPrice(){
        return price;
    }
}
