package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class Controller {
    
    @FXML
    private Label tour = new Label("C'est au tour de X de jouer.");
    
    @FXML
    private Button case9;

    @FXML
    private Button case8;

    @FXML
    private Button case5;

    @FXML
    private Button case2;

    @FXML
    private Button case6;

    @FXML
    private Button case3;

    @FXML
    private Button case7;

    @FXML
    private Button case4;

    @FXML
    private Button case1;
    
    private Button[][] buttons = new Button[3][3];

    @FXML
    private Button quitter;
    
    @FXML
    private Button rejouer;
    
	private char[][] board = new char[3][3];
	int row;
	int col;

    void ButtonHandler(int row, int col) {
    	this.row = row;
    	this.col = col;
    }
    
    private char currentPlayer = 'X';
    
    @FXML
    private void initialize() {
        quitter.setVisible(false);
        rejouer.setVisible(false);
        
        buttons[0][0] = case1;
        buttons[0][1] = case2;
        buttons[0][2] = case3;
        buttons[1][0] = case4;
        buttons[1][1] = case5;
        buttons[1][2] = case6;
        buttons[2][0] = case7;
        buttons[2][1] = case8;
        buttons[2][2] = case9;
    }
    
    @FXML
    void display(MouseEvent event) {
    	Button b = (Button) event.getSource();
    	b.setText(Character.toString(currentPlayer));
    	b.setDisable(true);
    	board[row][col] = currentPlayer;
    	
        if (isWon(currentPlayer)) {
        	tour.setText(currentPlayer + " a gagné !");
        	disableButtons();
        	quitter.setVisible(true);
            rejouer.setVisible(true);

        }
        else if (isFull()) {
            tour.setText("Match nul !");
            quitter.setVisible(true);
            rejouer.setVisible(true);

        }
        else {
        	currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            tour.setText("C'est au tour de " + currentPlayer + " de jouer.");
        }
    }
    
    private void disableButtons() {
    	case1.setDisable(true);
    	case2.setDisable(true);
    	case3.setDisable(true);
    	case4.setDisable(true);
    	case5.setDisable(true);
    	case6.setDisable(true);
    	case7.setDisable(true);
    	case8.setDisable(true);
    	case9.setDisable(true);
      }
    
    private boolean isFull() {
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			if (buttons[i][j].getText().isEmpty()) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    private boolean isWon(char player) {
    	// vérifie si le joueur a gagné en ligne
    	if (case1.getText().equals(Character.toString(player)) &&
    		case2.getText().equals(Character.toString(player)) &&
    		case3.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	if (case4.getText().equals(Character.toString(player)) &&
    		case5.getText().equals(Character.toString(player)) &&
    		case6.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	if (case7.getText().equals(Character.toString(player)) &&
    		case8.getText().equals(Character.toString(player)) &&
    		case9.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	
    	// verifie si le joueur a gagné en colonne
    	if (case1.getText().equals(Character.toString(player)) &&
    		case4.getText().equals(Character.toString(player)) &&
    		case7.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	if (case2.getText().equals(Character.toString(player)) &&
    		case5.getText().equals(Character.toString(player)) &&
    		case8.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	if (case3.getText().equals(Character.toString(player)) &&
    		case6.getText().equals(Character.toString(player)) &&
    		case9.getText().equals(Character.toString(player))) {
    		return true;
    	}

    	// vérifie si le joueur a gagné en diagonale
    	if (case1.getText().equals(Character.toString(player)) &&
    		case5.getText().equals(Character.toString(player)) &&
    		case9.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	if (case3.getText().equals(Character.toString(player)) &&
    		case5.getText().equals(Character.toString(player)) &&
    		case7.getText().equals(Character.toString(player))) {
    		return true;
    	}
    	return false;
    	}
    
    @FXML
    void resetGame(MouseEvent event) {
    	quitter.setVisible(false);
        rejouer.setVisible(false);
    	currentPlayer = 'X';
    	tour.setText("C'est au tour de " + currentPlayer + " de jouer.");
    	for (int i = 0; i < 3; i++) {
    		for (int j = 0; j < 3; j++) {
    			buttons[i][j].setText("");
    			buttons[i][j].setDisable(false);
    		}
    	}
    }
    
   @FXML
   void quit(MouseEvent event) {
	   Platform.exit();
   }
}
