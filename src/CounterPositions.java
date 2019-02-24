/**
 * Team name: Arrays start at 1
 * Team members: 17328173, 17768231, 17419914
 */
import java.awt.*;

public class CounterPositions {
    int xCo, yCo, numCounters;
    char color;
    boolean isTopRow;
    boolean isBearoff = false;
    boolean selected = false;
    final int DIAMETER = 55;
    final int HEIGHT = 10;

    CounterPositions(int xCo, int yCo, char color, int numCounters, boolean isTopRow) {
        this.xCo = xCo;
        this.yCo = yCo;
        this.color = color;
        this.numCounters = numCounters;
        this.isTopRow = isTopRow;
    }

    //Draw is called in BoardPanel to draw counters
    public void draw(Graphics2D g) // draw must be called by paintComponent of the panel
    {
        //Sets the color of the to be drawn counters
        if(color == 'R'){
            g.setColor(Color.red);
        } else if(color == 'W'){
            g.setColor(Color.WHITE);
        }

        /*If the counters are on the top row their yCo are increased to be drawn towards the bottom of the board
        but if they are not the top row their yCos are negated so they are drawn towards the top
         */
        if(selected && isTopRow){
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillOval(xCo, (yCo + ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
                if(i == getNumCounters()-1){
                    g.setColor(Color.green);
                    g.fillOval(xCo, (yCo + ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
                    if(color == 'R'){
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.white);
                    }
                    g.fillOval(xCo + 5, (yCo + ((DIAMETER + 2) * i) + 5), DIAMETER - 10, DIAMETER - 10);
                }
            }
        }
        else if(selected && !isTopRow){
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillOval(xCo, (yCo - ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
                if(i == getNumCounters()-1){
                    g.setColor(Color.green);
                    g.fillOval(xCo, (yCo - ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
                    if(color == 'R'){
                        g.setColor(Color.red);
                    } else {
                        g.setColor(Color.white);
                    }
                    g.fillOval(xCo + 5, (yCo - ((DIAMETER + 2) * i) + 5), DIAMETER - 10, DIAMETER - 10);
                }
            }
        }
        else if(isTopRow && !isBearoff)
        {
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillOval(xCo, (yCo + ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
            }
        } 
        else if(!isTopRow && !isBearoff)
        {
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillOval(xCo, (yCo - ((DIAMETER + 2) * i)), DIAMETER, DIAMETER);
            }
        } 
        else if(!isTopRow && isBearoff)
        {
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillRect(xCo, (yCo - ((HEIGHT + 2) * i)), DIAMETER, HEIGHT);
            }
        } 
        else if(isTopRow && isBearoff)
        {
            for(int i=0; i<getNumCounters(); i++)
            {
                g.fillRect(xCo, (yCo + ((HEIGHT + 2) * i)), DIAMETER, HEIGHT);
            }
        }
    }

    public void removeCounter(){
        setNumCounters(getNumCounters() - 1);
        if (getNumCounters() == 0) {
            setColor('B');
        }
    }

    public void addCounter(){
        setNumCounters(getNumCounters() + 1);
    }

    //Getters and Setters
    public void setxCo(int xCo) {
        this.xCo = xCo;
    }

    public void setyCo(int yCo) {
        this.yCo = yCo;
    }

    public void setColor(char color) {
        this.color = color;
    }

    public void setNumCounters(int numCounters) {
        this.numCounters = numCounters;
    }

    public void setTopRow(boolean topRow) {
        isTopRow = topRow;
    }

    public void setBearoff(boolean bearoff) {
        isBearoff = bearoff;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public int getxCo() {
        return xCo;
    }

    public int getyCo() {
        return yCo;
    }

    public char getColor() {
        return color;
    }

    public int getNumCounters() {
        return numCounters;
    }

    public int getDiameter() {
        return DIAMETER;
    }

    public boolean isTopRow() {
        return isTopRow;
    }

    public boolean isBearoff() {
        return isBearoff;
    }
}

