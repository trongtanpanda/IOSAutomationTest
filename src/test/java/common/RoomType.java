package common;

public enum RoomType {
    All("全て"),SINGLE("シングル"),TWIN("ツイン");

    public final String name;
    RoomType(String name){
        this.name = name;
    }

    public String getType(){
        return this.name;
    }
}
