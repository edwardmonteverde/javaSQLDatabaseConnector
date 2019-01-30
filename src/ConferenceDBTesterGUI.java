import java.util.Enumeration;
import java.util.Properties;
import java.util.Vector;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * For CSC/CIS 422 class
 * 
 * @author Edward MonteVerde / Nagendra Nepal (sort of)
 * @version Spring 2018
 */
// ----------------------------------------------------------
public class ConferenceDBTesterGUI extends Application {
	// Declare all the GUI components as instance variables.
	// Don't create the objects at this point.
	// First we need a GridPane
	private GridPane pane;

	// We need 10 labels
	private Label displayLabel;
	private Label q1Label;
	private Label q2Label;
	private Label q3Label;
	private Label q4Label;
	private Label q5Label;
	private Label q6Label;
	private Label q7Label;
	private Label q8Label;
	private Label q9Label;
	private Label q10Label;
	private Label uq1Label;
	private Label uq2Label;

	// We need 6 text fields
	private TextField timeSlot1TF;
	private TextField building2TF;
	private TextField subject3TF;
	private TextField subject4TF;
	private TextField lastName7TF;
	private TextField firstName7TF;
	private TextField sessionId11TF;
	private TextField author12TF;
	// private TextArea textArea;

	// We need a button
	private Button q1;
	private Button q2;
	private Button q3;
	private Button q4;
	private Button q5;
	private Button q6;
	private Button q7;
	private Button q8;
	private Button q9;
	private Button q10;
	// user query buttons
	private Button uq1;
	private Button uq2;
	private Button quit;

	private TextArea results;

	// We need a scene
	private Scene scene;

	// ----------------------------------------------------------
	// crtate GUI components
	private void createGUIComponents() {
		// Create GUI Objects
		pane = new GridPane();

		// ----------------------------------------------------------
		// Create Labels
		displayLabel = new Label("DISPLAY INFORMATION");
		q1Label = new Label("Q1. Number of papers that are in a given time slot ");
		q2Label = new Label("Q2. Number of papers that are scheduled for a given building ");
		q3Label = new Label("Q3. Details of paper based on given subject ");
		q4Label = new Label("Q4. Number of papers that are on a given subject ");
		q5Label = new Label("Q5. ID, Last and First Name of all session chairs (alphabetical order) ");
		q6Label = new Label("Q6. SessionID, RoomID, and Building Name of all sessions ");
		q7Label = new Label("Q7. First/Last Name of Author & Subject/SessionID/StartTime/EndTime ");
		q8Label = new Label("Q8. Insert a new time slot: 5:00 pm to 6:00 pm ");
		q9Label = new Label("Q9. Change inserted time slot to 5:30 pm to 6:30 pm ");
		q10Label = new Label("Q10. Delete the 5:30 pm to 6:30 pm time slot ");
		// user query labels
		uq1Label = new Label("UserQuery1. Return the building name(s) of a given session number ");
		uq2Label = new Label("UserQuery2. Return the name of all papers by a given contactAuthorId ");

		// ----------------------------------------------------------
		// Create the text fields

		timeSlot1TF = new TextField("<time slot>");
		building2TF = new TextField("<building>");
		subject3TF = new TextField("<subject>");
		subject4TF = new TextField("<subject>");
		lastName7TF = new TextField("<lastName>");
		firstName7TF = new TextField("<firstName>");
		// user query text fields
		sessionId11TF = new TextField("<sessionId>");
		author12TF = new TextField("<author>");

		// ----------------------------------------------------------
		// Create the buttons
		q1 = new Button("Q1");
		q2 = new Button("Q2");
		q3 = new Button("Q3");
		q4 = new Button("Q4");
		q5 = new Button("Q5");
		q6 = new Button("Q6");
		q7 = new Button("Q7");
		q8 = new Button("Q8");
		q9 = new Button("Q9");
		q10 = new Button("Q10");
		// user query buttons
		uq1 = new Button("UQ1");
		uq2 = new Button("UQ2");
		quit = new Button("Quit");

		// ----------------------------------------------------------
		// Create the text area to display results
		results = new TextArea();

		// ----------------------------------------------------------
		// Add the components to the pane
		pane.add(displayLabel, 0, 0);

		pane.add(q1Label, 0, 1);
		pane.add(timeSlot1TF, 1, 1);
		pane.add(q1, 3, 1);

		pane.add(q2Label, 0, 2);
		pane.add(building2TF, 1, 2);
		pane.add(q2, 3, 2);

		pane.add(q3Label, 0, 3);
		pane.add(subject3TF, 1, 3);
		pane.add(q3, 3, 3);

		pane.add(q4Label, 0, 4);
		pane.add(subject4TF, 1, 4);
		pane.add(q4, 3, 4);

		pane.add(q5Label, 0, 5);
		pane.add(q5, 3, 5);

		pane.add(q6Label, 0, 6);
		pane.add(q6, 3, 6);

		pane.add(q7Label, 0, 7);
		pane.add(lastName7TF, 1, 7);
		pane.add(firstName7TF, 2, 7);
		pane.add(q7, 3, 7);

		pane.add(q8Label, 0, 8);
		pane.add(q8, 3, 8);

		pane.add(q9Label, 0, 9);
		pane.add(q9, 3, 9);

		pane.add(q10Label, 0, 10);
		pane.add(q10, 3, 10);

		// start user queries
		pane.add(uq1Label, 0, 11);
		pane.add(sessionId11TF, 1, 11);
		pane.add(uq1, 3, 11);

		pane.add(uq2Label, 0, 12);
		pane.add(author12TF, 1, 12);
		pane.add(uq2, 3, 12);

		// quit
		pane.add(quit, 3, 13);
		pane.add(results, 0, 13);

		// Set Properties
		pane.setAlignment(Pos.CENTER);

	}

	// ----------------------------------------------------------
	// Attach handlers
	public void attachHandlers() {
		// ----------------------------------------------------------

		q1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String timeSlot = timeSlot1TF.getText();
				String query = "SELECT COUNT(PaperId), TIME_SLOT.StartTime, TIME_SLOT.EndTime "
						+ "FROM SESSION_ROOM_CHAIR, PAPER, TIME_SLOT "
						+ "WHERE PAPER.SessionId = SESSION_ROOM_CHAIR.SessionId "
						+ "AND SESSION_ROOM_CHAIR.TimeSlotId = TIME_SLOT.TimeSlotId " + "AND TIME_SLOT.TimeSlotId = '"
						+ timeSlot + "';";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------

		q2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String building = building2TF.getText();
				String query = "SELECT BUILDING.Name, COUNT(PAPER.paperId) "
						+ "FROM PAPER, BUILDING, SESSION_ROOM_CHAIR, ROOM " + "WHERE BUILDING.ID= ROOM.BuildingID "
						+ "AND PAPER.SessionId = SESSION_ROOM_CHAIR.SessionId "
						+ "AND SESSION_ROOM_CHAIR.RoomId = ROOM.RoomId " + "AND BUILDING.Name = '" + building + "';";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------

		q3.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String subject = subject3TF.getText();
				String query = "SELECT * FROM PAPER, SUBJECT_CATEGORY " + "WHERE SUBJECT_CATEGORY.Description = '"
						+ subject + "'AND SUBJECT_CATEGORY.SubjectId = PAPER.SubjectId "
						+ " GROUP BY PaperId LIMIT 10;";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------

		q4.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String subject = subject4TF.getText();
				String query = "SELECT COUNT(PAPER.PaperId) FROM PAPER, SUBJECT_CATEGORY WHERE "
						+ "SUBJECT_CATEGORY.Description = '" + subject + "';";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q5.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String query = "SELECT SESSION_CHAIR.SessionChairId, PERSON.LastName, PERSON.FirstName "
						+ "FROM PERSON, SESSION_CHAIR " + "WHERE PERSON.PersonId = SESSION_CHAIR.SessionChairId "
						+ "ORDER BY PERSON.LastName;";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q6.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String query = "SELECT SessionId, RoomId, Name" + "FROM SESSION_ROOM_CHAIR, BUILDING"
						+ "WHERE BUILDING.Id = SESSION_ROOM_CHAIR.BuildingId" + "ORDER BY SESSION_ROOM_CHAIR.SessionId"
						+ "LIMIT 10;";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q7.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String lastName = lastName7TF.getText();
				String firstName = firstName7TF.getText();
				String query = "SELECT PAPER.Title, PERSON.LastName, PERSON.FirstName, SUBJECT_CATEGORY.Description, "
						+ "SESSION_ROOM_CHAIR.SessionId, PAPER.StartTime, PAPER.EndTime "
						+ "FROM PERSON, PAPER, SUBJECT_CATEGORY, CONTACT_AUTHOR, SESSION_ROOM_CHAIR "
						+ "WHERE SESSION_ROOM_CHAIR.SESSIONID = PAPER.SESSIONID AND "
						+ "SUBJECT_CATEGORY.SUBJECTID = PAPER.SUBJECTID AND "
						+ "CONTACT_AUTHOR.CONTACTAUTHORID = PERSON.PERSONID AND "
						+ "PAPER.CONTACTAUTHORID = CONTACT_AUTHOR.CONTACTAUTHORID AND " + "PERSON.lastName = '"
						+ lastName + "' AND PERSON.firstName = '" + firstName + "';";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q8.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String query = "INSERT INTO TIME_SLOT VALUES (6, '05:00 PM', '06:00 PM');";
				System.out.println(query);
				insertIntoTable(query);

				String displayString = "Row inserted Successfully";
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q9.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String query = "UPDATE TIME_SLOT SET StartTime = '05:30 PM', EndTime = '06:30 PM' WHERE TimeSlotId = '6';";
				System.out.println(query);
				updateTable(query);

				String displayString = "Row updated Successfully";
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		q10.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				String query = "DELETE FROM TIME_SLOT WHERE TimeSlotId = '6';";
				System.out.println(query);
				deleteFromTable(query);

				String displayString = "Row deleted Successfully";
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		uq1.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String session = sessionId11TF.getText();
				String query = "SELECT BUILDING.NAME, SESSION_ROOM_CHAIR.SessionId "
						+ "FROM BUILDING, SESSION_ROOM_CHAIR " + "WHERE SESSIONID = '" + session + "' "
						+ "AND SESSION_ROOM_CHAIR.BUILDINGID = BUILDING.ID;";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});
		// ----------------------------------------------------------
		// Attach handlers to button using anonymous class
		uq2.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {

				String author = author12TF.getText();
				String query = "SELECT PAPER.TITLE, CONTACT_AUTHOR.CONTACTAUTHORID " + "FROM PAPER, CONTACT_AUTHOR "
						+ "WHERE PAPER.CONTACTAUTHORID = CONTACT_AUTHOR.CONTACTAUTHORID "
						+ "AND PAPER.CONTACTAUTHORID = '" + author + "';";
				System.out.println(query);

				String displayString = retrieveFromTable(query);
				results.setText(displayString);
			}
		});

		quit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	// ----------------------------------------------------------
	public void start(Stage primaryStage) {
		createGUIComponents();
		attachHandlers();

		// Create a scene and place it on stage
		scene = new Scene(pane);
		primaryStage.setTitle("Conference Database");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// ----------------------------------------------------------
	public static void main(String[] args) {
		Application.launch(args);
	}

	// ----------------------------------------------------------
	/**
	 * Converts a Vector of properties to a printable String
	 * 
	 * @param data
	 * @return
	 */
	private static String vecToString(Vector<Properties> data) {
		String result = "";
		// Now, we have to print out these rows in a user-understandable form
		if ((data == null) || (data.size() == 0)) {
			return ("No results were returned from database for this query");
		} else {
			// Print the headers
			result = ("==============================================\n");
			Properties p1 = data.firstElement();
			Enumeration props1 = p1.propertyNames();
			while (props1.hasMoreElements())
				result += (props1.nextElement() + "\t");
			result += "\n";
			result += ("----------------------------------------------\n");

			// Now go thru the entire 'data' Vector, get each Properties object
			// out of it
			// and print out the contents of the Properties object
			for (Properties p : data) {
				Enumeration props = p.propertyNames();
				while (props.hasMoreElements())
					result += (p.getProperty((String) (props.nextElement())) + "\t");
				result += "\n";
			}
			result += ("==============================================");
		}
		return result;
	}

	// ----------------------------------------------------------------------------
	// -------------------------------------------------------------------------------
	public static String retrieveFromTable(String queryString) {
		// First, set up an instance of the DatabaseAccessor class
		DatabaseAccessor dbAcc = new DatabaseAccessor();

		// Now that you have created the query string, you set that on the
		// DatabaseAccessor
		// object you created using the 'setSQLStatement()' method as shown
		// below
		dbAcc.setSQLStatement(queryString);

		// Then invoke the method 'executeSQLSelectStatement()' on the
		// DatabaseAccessor object
		// as shown below to run the query. The result of running this query is
		// a Vector of
		// Properties objects. Each Properties object in this Vector contains
		// the data from
		// one of the db table rows matching the query
		Vector<Properties> returnedValues = dbAcc.executeSQLSelectStatement();

		return vecToString(returnedValues);
		// Print the results
		// printValues(returnedValues);
	}

	// ----------------------------------------------------------------------------

	public static void insertIntoTable(String insertQueryString) {
		DatabaseMutator dbMut = new DatabaseMutator();

		dbMut.setSQLStatement(insertQueryString);
		Integer returnedValue = dbMut.executeSQLMutateStatement();

		if (returnedValue != 1)
			System.out.println("Error in db insertion");
		else
			System.out.println("Row inserted successfully");
	}

	// ----------------------------------------------------------------------------
	public static void updateTable(String updateQueryString) {
		DatabaseMutator dbMut = new DatabaseMutator();

		dbMut.setSQLStatement(updateQueryString);
		Integer returnedValue = dbMut.executeSQLMutateStatement();

		if (returnedValue < 0)
			System.out.println("Error in db update");
		else
			System.out.println("Row updated successfully");
	}

	// ------------------------------------------------------------------------
	public static void deleteFromTable(String deleteQueryString) {
		DatabaseMutator dbMut = new DatabaseMutator();

		dbMut.setSQLStatement(deleteQueryString);
		Integer returnedValue = dbMut.executeSQLMutateStatement();

		if (returnedValue < 0)
			System.out.println("Error in db Delete");
		else
			System.out.println("Row deleted successfully");
	}

}

// ----------------------------------------------------------
