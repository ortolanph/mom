package sp.senac.br.mom.maze;

public enum RoomType {
    BEGIN("B"),
    NORMAL("N"),
    END("E");
    
    private String code;
    
    private RoomType(String code) {
        this.code = code;
    }
    
    public String getCode() {
        return code;
    }
}
