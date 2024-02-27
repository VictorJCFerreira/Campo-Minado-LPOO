package gameLogic;

import grid.Grid;

public class Console {
    
	   
    public static void showTable(Grid field){
        for(int i=0; i<field.getLines(); i++){
            for (int w=0; w<field.getCols(); w++){
                boolean checker = false;
                for(int x=0; x<field.lengthClicked(); x++){
                    
                            int[] current_position = field.ClickedElement(x);
                            
                            if(i == current_position[0] && w == current_position[1] ){
                                 checker = true;
                                 
                                int bombasRedor = field.getOnGrid()[i][w].getMinesNearBy();
                                if(w == field.getCols()-1){
                                            
                                            System.out.println(" " + bombasRedor + " ");
                                        }
                                        else{
                                            System.out.print("  "+ bombasRedor + " |");
                                        }
                                break;
                        }
                
                  } if (!checker){
                	  
                                if(w == field.getCols()-1){
                                	
                                            if(field.getOnGrid()[i][w].getIsFlagged()){
                                            	
                                                System.out.println("🚩 ");
                                                
                                            }
                                            
                                            System.out.println("* ");
                                 } else{
                                        	
                                        if(field.getOnGrid()[i][w].getIsFlagged()){
                                        	
                                                System.out.print(" 🚩  |");
                                                
                                        } else{
                                            System.out.print("  * |");
                                          }
                                            
                                    }
                     }
                            
                                
             }
  
          }
        }
    }