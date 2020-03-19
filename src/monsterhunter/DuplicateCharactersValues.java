package monsterhunter;


public class DuplicateCharactersValues {
    private Object[][] rooms;
    private boolean[][] visited;


    public DuplicateCharactersValues() {

    }


    public DuplicateCharactersValues(Object[][] rooms, boolean[][] visited) {
        this.rooms = rooms;
        this.visited = visited;
    }

    //getters
    public Object[][] getRooms() {
        return this.rooms;
    }

    public boolean[][] getVisited() {
        return this.visited;
    }
}
