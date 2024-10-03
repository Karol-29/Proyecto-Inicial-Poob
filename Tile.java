public class Tile extends Rectangle {

    private String color;  // Campo para almacenar el color de la tile
    private int yPosition; // Campo para almacenar la posición Y específica de la tile
    private int xPosition;
    private int id;
    public Tile(int xPosition, int yPosition, String color) {
        super(10, 10, xPosition*10, yPosition*10, color);  // Llama al constructor de Rectangle
        this.color = color;  // Establece el color
        this.yPosition = yPosition; 
        this.xPosition = xPosition; // Guarda la posición X
        this.id=-1;
    }
    //retorna color
    public String getColor() {
        return color;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id){
        this.id=id;
    }
    
    public void makeVisible() {
        super.makeVisible();  // Llama al método de Rectangle para hacerlo visible
    }

    public void makeInvisible() {
        super.makeInvisible();  
    }

    public void deleteTile() {
        this.makeInvisible();  // Hace invisible la tile
    }

    public int getYPosition() {
        return yPosition;  // Devuelve la fila actual de la tile
    }

    public int getXPosition() {
        return xPosition;  // Devuelve la columna actual de la tile
    }

    public void relocate(int newRow, int newColumn) {
    // Calcula las nuevas posiciones x e y en función de la fila y columna dadas
        makeInvisible();  // Hace la tile invisible antes de moverla
        
        // Actualiza directamente las posiciones
        int newXPosition = newColumn * 10;
        int newYPosition = newRow * 10;
        
        // Mueve la tile horizontal y verticalmente a su nueva posición
        this.moveHorizontal(newXPosition - this.xPosition-10);
        this.moveVertical(newYPosition - this.yPosition-10);
        setPosition(newXPosition-10,newYPosition-10);
        makeVisible();  // Hace la tile visible después de moverla
    }
    public void setPosition(int xPosition,int yPosition){
        this.xPosition=xPosition;
        this.yPosition=yPosition;
    }
    public void propiedades() {
    System.out.println("xPosition: " + xPosition + ", yPosition: " + yPosition);
    }

}