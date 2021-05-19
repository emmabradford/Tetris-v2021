/**
 * Tetrad class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.awt.*;

public class Tetrad
{
    private Block[] blocks;

    public Tetrad(BoundedGrid<Block> grid)
    {
        //Exercise 1.2  Insert code here to
        //                  instantiate blocks Block array (length 4)
        blocks = new Block[4];
        //                  initialize blocks array with new Block objects
        for(int i = 0; i < blocks.length; i++)
        {
            blocks[i] = new Block();
        }
        //                  declare color variable
        Color color = Color.BLACK;
        //                  declare and instantiate locs Location array (length 4)
        Location[] locs = new Location[4];
        //                  declare shape variable and set equal to zero
        int shape = (int)(Math.random()*7);

        //Exercise 2.0  Insert code here to
        //                  choose a random integer from 0 to 6

        //Exercise 1.2  Insert code here to
        //                  branch (if statements) based on each shape number, to then
        //                      set the color variable for that shape
        //                      set the block locations for that shape
        switch (shape)
        {
            case 0://i shape
                color = Color.BLUE;
                locs[1] = new Location(0, 3);
                locs[0] = new Location(0, 4);
                locs[2] = new Location(0, 5);
                locs[3] = new Location(0, 6);
                break;
            case 1: //t shape
                color = Color.GRAY;
                locs[1] = new Location(0, 3);
                locs[0] = new Location(0, 4);
                locs[2] = new Location(0, 5);
                locs[3] = new Location(1, 4);
                break;
            case 2://L shape
                color = Color.GREEN;
                locs[1] = new Location(0, 3);
                locs[0] = new Location(0, 4);
                locs[2] = new Location(0, 5);
                locs[3] = new Location(1, 5);
                break;
            case 3: //J shape
                color = Color.MAGENTA;
                locs[1] = new Location(1, 3);
                locs[0] = new Location(1, 4);
                locs[2] = new Location(1, 5);
                locs[3] = new Location(0, 5);
                break; 
            case 4: //s shape
                color = Color.ORANGE;
                locs[1] = new Location(1, 3);
                locs[0] = new Location(1, 4);
                locs[2] = new Location(0, 4);
                locs[3] = new Location(0, 5);
                break;
            case 5:
                color = Color.PINK;
                locs[1] = new Location(0, 3);
                locs[0] = new Location(0, 4);
                locs[2] = new Location(1, 4);
                locs[3] = new Location(1, 5);
                break;
            case 6://o shape
                color = Color.RED;
                locs[0] = new Location(0, 3);
                locs[1] = new Location(0, 4);
                locs[2] = new Location(1, 3);
                locs[3] = new Location(1, 4);
                break;
        }
        
        //Exercise 1.2  Insert code here (after the above if statements) to
        //                  loop through the blocks array to
        for(int i = 0; i < blocks.length; i++){
        //                      set the color of each block
            blocks[i].setColor(color);
        }
        //                  call addToLocations
        addToLocations(grid, locs);
    }

    //precondition:  blocks are not in any grid;
    //               blocks.length = locs.length = 4.
    //postcondition: The locations of blocks match locs,
    //               and blocks have been put in the grid.
    private void addToLocations(BoundedGrid<Block> grid, Location[] locs)
    {
        for(int i = 0; i < locs.length; i++)
        {
            blocks[i].putSelfInGrid(grid, locs[i]);
        }
        // throw new RuntimeException("Insert Exercise 1.1 code here");    // replace this line
    }

    //precondition:  Blocks are in the grid.
    //postcondition: Returns old locations of blocks;
    //               blocks have been removed from grid.
    private Location[] removeBlocks()
    {
        Location[] temp = new Location[blocks.length];
        for(int i  = 0; i < blocks.length; i++)
        {
            temp[i] = blocks[i].getLocation();
            blocks[i].removeSelfFromGrid();
        }
        return temp;
        //throw new RuntimeException("Insert Exercise 2.1 code here");    // replace this line
    }

    //postcondition: Returns true if each of locs is
    //               valid (on the board) AND empty in
    //               grid; false otherwise.
    private boolean areEmpty(BoundedGrid<Block> grid,
                             Location[] locs)
    {
        boolean val = true;
        boolean em = true;
        for(int i = 0; i < locs.length; i++)
        {
            if(!grid.isValid(locs[i]))
            {
                return false;
            }
            if(grid.get(locs[i])!=null)
            {
                return false;
            }
        }
        return true;
        //throw new RuntimeException("Insert Exercise 2.1 code here");    // replace this line
    }

    //postcondition: Attempts to move this tetrad deltaRow
    //               rows down and deltaCol columns to the
    //               right, if those positions are valid
    //               and empty; returns true if successful
    //               and false otherwise.
    public boolean translate(int deltaRow, int deltaCol)
    {
        //Exercise 2.2    Insert code here to
        //              ask any block for its grid and store value
        BoundedGrid<Block> temp = blocks[0].getGrid();
        //              remove the blocks (but save the locations)
        Location[] old = removeBlocks();
        //              create an array of the new (possible) locations\
        Location[] nuevo = new Location[old.length];
        for(int i = 0; i < nuevo.length; i++)
        {
            nuevo[i] = new Location(old[i].getRow() + deltaRow, old[i].getCol() + deltaCol);
        }
        //              check if the new locations are empty
        if(areEmpty(temp, nuevo))
        {
            //              replace the tetrad in the proper place (translated)
            addToLocations(temp, nuevo);
            return true;   
        }
        else
        {
            //              return true if moved, false if not moved
            addToLocations(temp, old);
            return false;
        }
        ///throw new RuntimeException("Insert Exercise 2.2 code here");    // replace this line
    }

    //postcondition: Attempts to rotate this tetrad
    //               clockwise by 90 degrees about its
    //               center, if the necessary positions
    //               are empty; returns true if successful
    //               and false otherwise.
    public boolean rotate()
    {
        //Exercise 3.0  Insert code here to
        //              ask any block for its grid and store value
        BoundedGrid<Block> grid = blocks[0].getGrid();
        //              remove the blocks (but save the locations)
        Location[] locs = removeBlocks();
        Location[] nuevo = new Location[locs.length];
        int row0 = locs[0].getRow();
        int col0 = locs[0].getCol();
        for(int i = 0; i < nuevo.length; i++)
        {
            nuevo[i] = new Location(row0 - col0 + locs[i].getCol(), row0 + col0 - locs[i].getRow());
        }
        //              check if the new locations are empty
        if(areEmpty(grid, nuevo))
        {
            //              replace the tetrad in the proper place (translated)
            addToLocations(grid, nuevo);
            return true;   
        }
        else
        {
            //              return true if moved, false if not moved
            addToLocations(grid, locs);
            return false;
        }
        //              replace the tetrad in the proper place (rotated)

        //throw new RuntimeException("Insert Exercise 3.0 code here");    // replace this line
    }
}