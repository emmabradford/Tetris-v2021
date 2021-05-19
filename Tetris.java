/**
 * Tetris class to be completed for Tetris project
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tetris implements ArrowListener
{
    public static void main(String[] args)
    {
        Tetris tetris = new Tetris();
        tetris.play();
    }

    private BoundedGrid<Block> grid;
    private BlockDisplay display;
    private Tetrad activeTetrad;

    public Tetris()
    {
        grid = new BoundedGrid<Block>(20, 10);
        display = new BlockDisplay(grid);
        display.setTitle("Tetris");
        display.setArrowListener(this);
        activeTetrad = new Tetrad(grid);
        //activeTetrad.translate(0, 1);
    }

    public void upPressed()
    {
        //Insert Exercise 2.3 code here
        activeTetrad.rotate();
        display.showBlocks();
    }

    public void downPressed()
    {
        //Insert Exercise 2.3 code here
        activeTetrad.translate(1, 0);
        display.showBlocks();
    }

    public void leftPressed()
    {
        //Insert Exercise 2.3 code here
        activeTetrad.translate(0, -1);
        display.showBlocks();
    }

    public void rightPressed()
    {
        //Insert Exercise 2.3 code here
        activeTetrad.translate(0, 1);
        display.showBlocks();
    }

    public void spacePressed()
    {
        //Insert Exercise 2.3 code here
        while(activeTetrad.translate(1,0)){
            /*int count = 0;
            while(count < grid.getNumRows())
            {
            boolean conti = activeTetrad.translate(1, 0);
            if (conti)
            {
            count++;
            }
            else
            {
            count  = grid.getNumRows();
            }*/
            display.showBlocks();
        }
    }

    public void play()
    {
        while (true)
        {
            try { Thread.sleep(1000); } catch(Exception e) {}

            //Insert Exercise 3.2 code here
            if(!activeTetrad.translate(1,0))
            {
                clearCompletedRows();
                //Insert Exercise 3.3 code here
                if(topRowsEmpty())
                {
                    activeTetrad = new Tetrad(grid);
                }
                else
                {
                    return;
                }
            }
            display.showBlocks();
        }
    }

    //precondition:  0 <= row < number of rows
    //postcondition: Returns true if every cell in the
    //               given row is occupied;
    //               returns false otherwise.
    private boolean isCompletedRow(int row)
    {
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            // if any cell in the row is empty, the row is not completed
            if(grid.get(new Location(row, col)) == null)
            {
                return false;
            }
        }
        return true;
    }

    //precondition:  0 <= row < number of rows;
    //               given row is full of blocks
    //postcondition: Every block in the given row has been
    //               removed, and every block above row
    //               has been moved down one row.
    private void clearRow(int row)
    {
        // remove all of the blocks in the given completed row
        for(int col = 0; col < grid.getNumCols(); col++)
        {
            Block block = grid.get(new Location(row, col));
            block.removeSelfFromGrid();
        }

        // now move all of the blocks in each row above down one row
        for(int r = row - 1; r >= 0; r--)
        {
            for(int c = 0; c < grid.getNumCols(); c++)
            {
                Block block = grid.get(new Location(r, c));
                if(block != null)
                {
                    block.moveTo(new Location(r + 1, c));
                }
            }
        }
    }

    //postcondition: All completed rows have been cleared.
    private void clearCompletedRows()
    {
        // work from bottom row to top row
        for(int row = grid.getNumRows() - 1; row >= 0; row--)
        {
            if(isCompletedRow(row))
            {
                clearRow(row);
                row++;  // to check the first row that moved down
            }
        }
    }

    //returns true if top two rows of the grid are empty (no blocks), false otherwise
    private boolean topRowsEmpty()
    {
        for(int r = 0; r < 2; r++)
        {
            for(int c = 0; c < grid.getNumCols(); c++)
            {
                if(grid.get(new Location(r, c)) != null)
                {
                    return false;
                }
            }
        }
        return true;
    }

}