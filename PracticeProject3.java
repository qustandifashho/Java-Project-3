// Testing and final done in VDI because local DrJava Not compiling any Math functions even with JDK8 compiler  
import java.awt.*;
import java.util.*;
public class PracticeProject3{
  public static final Scanner CONSOLE = new Scanner(System.in); // Global variable
  public static void main(String[] args) {
    System.out.println("UTSA – Fall 2022 - CS1083 - Section 001 - Project 3 - SyncedForms - written by Qustandi Fashho");
    System.out.println();
    drawSyncedForms();                                                                                                   
  }
      public static void drawSyncedForms() {
        DrawingPanel panel = new DrawingPanel(400, 400);
        Graphics g = panel.getGraphics();
        
        // declare and initialize arrays
        int[] xPositions = new int[9];
        int[] yPositions = new int[9];
        char[] forms = new char[]{'S','s', 'R', 'r'};
        Color[] colors = new Color[9];
        int numForms = 4; // 
        int numMoves = 5; //
        int xPos = 0;
        int yPos = 0;
        int size = 0;
        
        boolean isValidInput = true;
        while(isValidInput){
          System.out.print("What form will be shown (C-ircle, R-ectangle)?"); ////////////////////
          char form  = CONSOLE.next().charAt(0); /////////////////////
          if (form == 'S' || form == 's' || form == 'R' || form == 'r') {
            xPos = 0;
            yPos = 0;
            size = 100;
            //g.fillRect(xPos, yPos, size, size / 2); //////////////// 
            break;
        } else if (form == 'C' || form == 'c') {
            xPos = 0;
            yPos = 0;
            size = 100;
            //g.drawOval(xPos, yPos, size, size);
            break;
        } else {
            System.out.println("Invalid form input: " + form);
            
        }
        }
        
        
        
        
        


        // obtain values
        System.out.print("How many forms do you want to show (max 9)? ");
        xPositions[0] = CONSOLE.nextInt();
        System.out.print("How many times do you want the form to move (max 500)?");
        yPositions[0] = CONSOLE.nextInt();

        // call methods
        initialPosition(xPositions, yPositions, numForms);
        initialColor(colors, numForms);

        // move forms
        for (int i = 0; i < numMoves; i++) {
            System.out.print("Please input the different moves");
            int movement = CONSOLE.nextInt();

            // display initial positions
            for (int j = 0; j < numForms; j++) {
                showForm(panel, forms[j], xPositions[j], yPositions[j], colors[j], 40); // Size of the R or C is 40
            }

            // move and show updated positions
            for (int j = 0; j < numForms; j++) {
                moveForm(xPositions, yPositions, j, movement); 
                showForm(panel, forms[j], xPositions[j], yPositions[j], colors[j], 40); // Size of R or C is 40 
            }

            // add delay (need to look up for delaying)
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // put the blak box with the green letters with my name 
        g.setColor(Color.BLACK);
        g.fillRect(20, 320, 360, 40);
        g.setColor(Color.GREEN);
        g.drawRect(20, 320, 360, 40);
        g.setColor(Color.GREEN);
        g.drawString("UTSA - Spring 2023 - CS1083 - Section 001 - Project 3 -", 30, 340);
        g.drawString("SyncedForms - written by Qustandi Fashho", 30, 355);
        }
     


    public static void initialPosition(int[] xPositions, int[] yPositions, int numForms) {
        // loop to go through all forms and assign initial position
        for (int i = 1; i < numForms; i++) {
            switch (i) {
                case 1:
                    xPositions[i] = xPositions[0] + 100;
                    yPositions[i] = yPositions[0];
                    break;
                case 2:
                    xPositions[i] = xPositions[0];
                    yPositions[i] = yPositions[0] + 100;
                    break;
                case 3:
                    xPositions[i] = xPositions[0] + 100;
                    yPositions[i] = yPositions[0] + 100;
                    break;
            }
        }
    }
    
    public static void initialColor(Color[] colors, int numForms) {
      // create an array of available colors
      Color[] availableColors = {Color.GREEN, Color.GRAY, Color.YELLOW, Color.RED, Color.ORANGE, Color.PINK, Color.DARK_GRAY, Color.BLUE, Color.BLACK};

      // loop to go through all forms and assign initial color
      for (int i = 0; i < numForms; i++) {
        int index = i % availableColors.length;
        colors[i] = availableColors[index];
    }
}
    public static void moveForms(DrawingPanel panel, char[] forms, int[] xPositions, int[] yPositions, Color[] colors, int numForms, int numMoves) {
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < numMoves; i++) {
        System.out.println("Enter movement number:");
        int movement = scanner.nextInt();

        for (int j = 0; j < numForms; j++) {
            showForm(panel, forms[j], xPositions[j], yPositions[j], colors[j], 25);
        }

        for (int j = 0; j < numForms; j++) {
            moveForm(xPositions, yPositions, j, movement);
            showForm(panel, forms[j], xPositions[j], yPositions[j], colors[j], 25);
        }

        try {
            Thread.sleep(50); // 50 milliseconds 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

    public static void moveForm(int[] xPositions, int[] yPositions, int formIndex, int orientation) {
      int x = xPositions[formIndex];
      int y = yPositions[formIndex];

      switch (orientation) {
        case 1:
            y -= 25;
            break;
        case 2:
            x += 25;
            y -= 25;
            break;
        case 3:
            x += 25;
            break;
        case 4:
            x += 25;
            y += 25;
            break;
        case 5:
            y += 25;
            break;
        case 6:
            x -= 25;
            y += 25;
            break;
        case 7:
            x -= 25;
            break;
        case 8:
            x -= 25;
            y -= 25;
            break;
    }

      xPositions[formIndex] = x;
      yPositions[formIndex] = y;
}
    public static void showForm(DrawingPanel panel, char form, int xPos, int yPos, Color color, int size) {
      if (panel == null) { 
        System.out.println("Form: " + form + " Position: (" + xPos + ", " + yPos + ") Color: " + color);
        Graphics g = panel.getGraphics(); ////////////
        g.setColor(color); //////////
        g.fillRect(xPos, yPos, size, size); //////////
        g.setColor(Color.BLACK); /////////   
    } 
      else if( form == 'S' || form == 's' || form == 'R' || form == 'r'){
          Graphics g = panel.getGraphics(); ////////////
        g.setColor(Color.BLACK); //////////
        g.drawRect(xPos, yPos, size, size);
        g.setColor(color); 
        g.fillRect(xPos, yPos, size, size); //////////
        g.setColor(Color.BLACK); ///////// 
        
      }
    
        
      else {
        Graphics g = panel.getGraphics();
        g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
        g.setColor(Color.BLACK); 
        /*
        System.out.print("Type C for circle or R for rectangle"); ////////////////////
        form = CONSOLE.next().charAt(0); /////////////////////
        if (form == 'R') {
            g.drawRect(xPos, yPos, size, size / 2); 
        } else if (form == 'C') {
            g.drawOval(xPos, yPos, size, size);
        } else {
            System.out.println("Invalid form input: " + form);
           */
        }
    }
}


    





