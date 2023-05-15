package common;

public enum RoomType {
    All("全て"),SINGLE("シングル"),TWIN("ツイン");

    public final String type;
    RoomType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
