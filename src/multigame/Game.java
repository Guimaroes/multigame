/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multigame;

/**
 *
 * @author Gabriel
 */
public class Game {
    
    public static final char DIRECTION_UP     = 'W';
    public static final char DIRECTION_LEFT   = 'A';
    public static final char DIRECTION_DOWN   = 'S';
    public static final char DIRECTION_RIGHT  = 'D';
    
    private static final char FIELD_EMPTY       = '.';
    private static final char FIELD_BALL        = '@';
    private static final char FIELD_UPPER_GOAL  = 'X';
    private static final char FIELD_LOWER_GOAL  = 'x';
    private static final char FIELD_UPPER_A     = 'A';
    private static final char FIELD_UPPER_B     = 'B';
    private static final char FIELD_UPPER_C     = 'C';
    private static final char FIELD_UPPER_D     = 'D';
    private static final char FIELD_LOWER_A     = 'a';
    private static final char FIELD_LOWER_B     = 'b';
    private static final char FIELD_LOWER_C     = 'c';
    private static final char FIELD_LOWER_D     = 'd';
    
    private static final int NUM_COL = 45;//must be odd 
    private static final int NUM_ROW = 25;//must be odd 
    private static final int ROW_UPPER_GOAL = 0;
    private static final int ROW_LOWER_GOAL = NUM_ROW -1;
    
    private static final int TEAM_UPPER = 0;
    private static final int TEAM_LOWER = 1;
    
    private final int MIDDLE_COL;
    private final int MIDDLE_ROW;
    private final int UPPER_A_COL;
    private final int UPPER_A_ROW;
    private final int UPPER_B_COL;
    private final int UPPER_B_ROW;
    private final int UPPER_C_COL;
    private final int UPPER_C_ROW;
    private final int UPPER_D_COL;
    private final int UPPER_D_ROW;
    private final int LOWER_A_COL;
    private final int LOWER_A_ROW;
    private final int LOWER_B_COL;
    private final int LOWER_B_ROW;
    private final int LOWER_C_COL;
    private final int LOWER_C_ROW;
    private final int LOWER_D_COL;
    private final int LOWER_D_ROW;
    
    private int scoreUpper = 0;
    private int scoreLower = 0;
    
    private char[][] field = new char[NUM_ROW][NUM_COL];
    
    public Game() {
        int auxRow = NUM_ROW/6;
        int auxCol = NUM_COL/6;
        
        this.MIDDLE_COL = NUM_COL/2;
        this.MIDDLE_ROW = NUM_ROW/2;
        
        this.UPPER_A_COL = auxCol;
        this.UPPER_A_ROW = auxRow*2;
        this.UPPER_B_COL = auxCol*2;
        this.UPPER_B_ROW = auxRow;
        this.UPPER_C_COL = NUM_COL - auxCol*2 -1;
        this.UPPER_C_ROW = auxRow;
        this.UPPER_D_COL = NUM_COL - auxCol -1;
        this.UPPER_D_ROW = auxRow*2;
        
        this.LOWER_A_COL = auxCol;
        this.LOWER_A_ROW = NUM_ROW - auxRow*2 -1;
        this.LOWER_B_COL = auxCol*2;
        this.LOWER_B_ROW = NUM_ROW - auxRow -1;
        this.LOWER_C_COL = NUM_COL - auxCol*2 -1;
        this.LOWER_C_ROW = NUM_ROW - auxRow -1;
        this.LOWER_D_COL = NUM_COL - auxCol -1;
        this.LOWER_D_ROW = NUM_ROW - auxRow*2 -1;
        
        initField();
    } 
    
    public String getField() {
        String fieldStr = "UPPER " + scoreUpper + " x " + scoreLower + " LOWER\n\n";
        
        for ( int row = 0; row < NUM_ROW; row++ ) {
            for ( int col = 0; col < NUM_COL; col++ ) {
                fieldStr += field[row][col];
            }
            fieldStr += "\n";
        }
        
        return fieldStr;
    }
    
    public void movePlayer(char player, char direction) {
        for ( int col = 0; col < NUM_COL; col++ ) {
            for ( int row = 0; row < NUM_ROW; row++ ) {
                
                if ( field[row][col] == player ) {
                
                    switch (direction) {
                        case DIRECTION_UP:
                            
                            if ( field[row-1][col] == FIELD_EMPTY ) {
                                field[row-1][col] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row-1][col] == FIELD_BALL ) {
                                
                                if ( field[row-2][col] == FIELD_EMPTY      ||
                                     field[row-2][col] == FIELD_UPPER_GOAL )
                                {
                                    if ( field[row-2][col] == FIELD_UPPER_GOAL ) {
                                        scoreGoal( TEAM_LOWER );
                                    }
                                    
                                    else {
                                        field[row-2][col] = FIELD_BALL;
                                        field[row-1][col] = player;
                                        field[row][col] = FIELD_EMPTY;
                                    }
                                }
                            }
                            
                            break;
                            
                        case DIRECTION_LEFT:
                            
                            if ( col == 0 && field[row][NUM_COL-1] == FIELD_EMPTY ) {
                                field[row][NUM_COL-1] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( col == 0 && 
                                      field[row][NUM_COL-1] == FIELD_BALL &&
                                      field[row][NUM_COL-2] == FIELD_EMPTY ) 
                            {
                                field[row][NUM_COL-2] = FIELD_BALL;
                                field[row][NUM_COL-1] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row][col-1] == FIELD_EMPTY )
                            {
                                field[row][col-1] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row][col-1] == FIELD_BALL ) {
                                
                                if ( col-1 == 0 ) {
                                    if ( field[row][NUM_COL-1] == FIELD_EMPTY ) 
                                    {
                                        field[row][NUM_COL-1] = FIELD_BALL;
                                        field[row][col-1] = player;
                                        field[row][col] = FIELD_EMPTY;
                                    }
                                }
                                
                                else if ( field[row][col-2] == FIELD_EMPTY )
                                {
                                    field[row][col-2] = FIELD_BALL;
                                    field[row][col-1] = player;
                                    field[row][col] = FIELD_EMPTY;
                                }
                            }
                            
                            break;
                            
                        case DIRECTION_DOWN:
                            
                            if ( field[row+1][col] == FIELD_EMPTY ) {
                                
                                field[row+1][col] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row+1][col] == FIELD_BALL ) {
                                
                                if ( field[row+2][col] == FIELD_EMPTY      ||
                                     field[row+2][col] == FIELD_LOWER_GOAL )
                                {
                                    if ( field[row+2][col] == FIELD_LOWER_GOAL ) {
                                        scoreGoal( TEAM_UPPER );
                                    }
                                    
                                    else {
                                        field[row+2][col] = FIELD_BALL;
                                        field[row+1][col] = player;
                                        field[row][col] = FIELD_EMPTY;
                                    }
                                }
                            }                            
                            
                            break;
                            
                        case DIRECTION_RIGHT:
                            
                            if ( col == NUM_COL-1 && field[row][0] == FIELD_EMPTY ) {
                                field[row][0] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( col == NUM_COL-1 && 
                                      field[row][0] == FIELD_BALL &&
                                      field[row][1] == FIELD_EMPTY ) 
                            {
                                field[row][1] = FIELD_BALL;
                                field[row][0] = player;
                                field[row][NUM_COL-1] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row][col+1] == FIELD_EMPTY )
                            {
                                field[row][col+1] = player;
                                field[row][col] = FIELD_EMPTY;
                            }
                            
                            else if ( field[row][col+1] == FIELD_BALL ) {
                                
                                if ( col+1 == NUM_COL-1 ) {
                                    if ( field[row][0] == FIELD_EMPTY ) 
                                    {
                                        field[row][0] = FIELD_BALL;
                                        field[row][col+1] = player;
                                        field[row][col] = FIELD_EMPTY;
                                    }
                                }
                                
                                else if ( field[row][col+2] == FIELD_EMPTY )
                                {
                                    field[row][col+2] = FIELD_BALL;
                                    field[row][col+1] = player;
                                    field[row][col] = FIELD_EMPTY;
                                }
                            }
                            
                            break;
                            
                        default:
                            break;
                    }
                    
                    return;
                }
            }
        }
    }
    
    private void scoreGoal( int team ) {
        
        if ( team == TEAM_UPPER ) {
            scoreUpper++;
        }
        
        else if ( team == TEAM_LOWER ) {
            scoreLower++;
        }
        
        initField();
    }
    
    private void initField() {
        for ( int col = 0; col < NUM_COL; col++ ) {
            for ( int row = 0; row < NUM_ROW; row++ ) {
                
                if ( row == ROW_UPPER_GOAL ) {
                    field[row][col] = FIELD_UPPER_GOAL;
                }
                
                else if ( row == ROW_LOWER_GOAL ) {
                    field[row][col] = FIELD_LOWER_GOAL;
                }
                
                else if ( col == MIDDLE_COL && row == MIDDLE_ROW ) {
                    field[row][col] = FIELD_BALL;
                }
                
                else if ( col == UPPER_A_COL && row == UPPER_A_ROW ) {
                    field[row][col] = FIELD_UPPER_A;
                }
                
                else if ( col == UPPER_B_COL && row == UPPER_B_ROW ) {
                    field[row][col] = FIELD_UPPER_B;
                }
                
                else if ( col == UPPER_C_COL && row == UPPER_C_ROW ) {
                    field[row][col] = FIELD_UPPER_C;
                }
                
                else if ( col == UPPER_D_COL && row == UPPER_D_ROW ) {
                    field[row][col] = FIELD_UPPER_D;
                }
                
                else if ( col == LOWER_A_COL && row == LOWER_A_ROW ) {
                    field[row][col] = FIELD_LOWER_A;
                }
                
                else if ( col == LOWER_B_COL && row == LOWER_B_ROW ) {
                    field[row][col] = FIELD_LOWER_B;
                }
                
                else if ( col == LOWER_C_COL && row == LOWER_C_ROW ) {
                    field[row][col] = FIELD_LOWER_C;
                }
                
                else if ( col == LOWER_D_COL && row == LOWER_D_ROW ) {
                    field[row][col] = FIELD_LOWER_D;
                }
                
                else {
                    field[row][col] = FIELD_EMPTY;
                }
            }
        }
    }
}
