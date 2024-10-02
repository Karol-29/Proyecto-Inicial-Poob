import java.util.HashMap;
import javax.swing.JOptionPane;
import java.util.*;

public class Puzzle extends Rectangle {
    private int TILE_SIZE=10; // Tamaño de las tiles
    private Tile [][] matrixStarting; // matriz inicial
    private Tile [][] matrixEnding; // matriz final
    private HashMap<Integer, int[]> setTileStarting; //conjuntos matriz inicial  
    private HashMap<Integer, int[]> setTileEnding; //conjuntos matriz final  
    private int h; //Alto
    private int w; //Ancho
    private int currentId=0;  // Elegir los id de los tiles, para evitar problemas
    private boolean last;
    private boolean isVisible; 
    private boolean hasTwoPuzzle;
    public static final Map<String, String> COLORS;
    private Rectangle puzzleStarting;
    private Rectangle puzzleEnding;
    static {
        COLORS = new HashMap<>();
        COLORS.put("r", "red");
        COLORS.put("b", "blue");
        COLORS.put("g", "green");
        COLORS.put("y", "yellow");
    }
    public Puzzle(int h, int w) {
        setTileStarting = new HashMap<>();
        setTileEnding= new HashMap<>();
        matrixStarting = new Tile[h][w];
        matrixEnding = new Tile[h][w];
        this.h = h;
        this.w = w;
        this.isVisible = false;
        this.puzzleStarting=new Rectangle(h*10,w*10,0,0,"black");
        this.puzzleEnding=new Rectangle(h*10,w*10,w*10+50,0,"white");
    }
    public Puzzle(char[][] ending){
        this(ending.length,ending[0].length);
        matrixEnding=convertir(ending);
        this.puzzleEnding=new Rectangle(ending.length*10,ending[0].length*10,w*10+50,0,"black");
        addTile(matrixEnding,true);
        addTile(matrixStarting,false);
    }
    public Puzzle(char[][] ending,char[][] starting){
        this(ending);
        matrixStarting =convertir(starting);
        addTile(matrixEnding,true);
        addTile(matrixStarting,false);
    }
    private void addTile(Tile [][] matrix, boolean type ) {//true=ending, starting=false 
        if(type){
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] != null) {
                        matrix[i][j].setId(currentId);
                        matrix[i][j].moveHorizontal((w*10)-matrix[i][j].getXPosition()+50);        
                        matrix[i][j].setPosition(matrix[i][j].getYPosition(),(w*10)-matrix[i][j].getXPosition()+50);
                        setTileEnding.put(currentId,new int[]{currentId});
                        currentId++;
                        if(this.isVisible){
                            matrix[i][j].makeVisible();
                        }
                    }
                }
            }
        }else{
           for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    if (matrix[i][j] != null) {
                        matrix[i][j].setId(currentId);
                        setTileStarting.put(currentId,new int[]{currentId});
                        currentId++;
                        matrix[i][j].setPosition(matrix[i][j].getYPosition(),matrix[i][j].getXPosition());
                        if(this.isVisible){
                    matrix[i][j].makeVisible();
                    }  
                }
                }
            } 
        }
    }
    private Tile[][] convertir(char[][] matrix) {
        Tile[][] convertida = new Tile[h][w];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == '.') {
                    convertida[i][j] = null; // Si es '*', se deja como null
                } else {
                    String color = COLORS.get(String.valueOf(matrix[i][j])); // Obtener color del mapa
                    if (color != null) {
                        convertida[i][j] = new Tile(j, i, color); // Crear nueva Tile con el color adecuado
                    } else {
                        convertida[i][j] = new Tile(i, j, "white"); // Si no hay color en el mapa, asignar un color por defecto
                    }
                }
            }
        }
        return convertida; // Retornar la matriz convertida
    }
    public void addTile(int row, int column, String color) {
        // Verificar si la posición está dentro de los límites
        if (row >= 0 && row <= h-1 && column >= 0 && column <= w-1) {
            //verificar que la posicion este vacia
            if(matrixStarting[row][column]==null){
                Tile newTile = new Tile(column,row, color); 
                matrixStarting[row][column]=newTile;
                setTileStarting.put(currentId, new int[]{currentId});  // Asocia el tile al conjunto
                last = true;
                currentId++;
                if (this.isVisible){
                    newTile.makeVisible();
                }
            }else{
               JOptionPane.showMessageDialog(null, "Hay una tile es esta posicion", "Error", JOptionPane.ERROR_MESSAGE);
               last = false; 
            }
        } else {
            // Mostrar mensaje de error si está fuera de los límites
            JOptionPane.showMessageDialog(null, "Está fuera de los límites", "Error", JOptionPane.ERROR_MESSAGE);
            last = false;
        }
    }
    public void makeVisible(){
        puzzleStarting.makeVisible();
        puzzleEnding.makeVisible();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrixEnding[i][j] != null) {
                    matrixEnding[i][j].makeVisible();
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (this.matrixStarting[i][j] != null) {
                    matrixStarting[i][j].makeVisible();
                }
            }
        }
        this.isVisible=true;
    }
    public void makeInvisible(){
        puzzleStarting.makeInvisible();
        puzzleEnding.makeInvisible();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (matrixEnding[i][j] != null) {
                    matrixEnding[i][j].makeInvisible();
                }
            }
        }
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                if (this.matrixStarting[i][j] != null) {
                    matrixStarting[i][j].makeInvisible();
                }
            }
        }
        this.isVisible=false;
    }
}

