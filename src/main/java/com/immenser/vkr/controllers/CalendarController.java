package com.immenser.vkr.controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import com.immenser.vkr.dao.InterviewDAO;
import com.immenser.vkr.models.Candidate;
import com.immenser.vkr.models.Interview;
import com.immenser.vkr.models.Position;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CalendarController {

    @FXML
    private GridPane grid;

    @FXML
    private Label month;

    @FXML
    private Button nextBtn;

    @FXML
    private Button prevBtn;

    @FXML
    private VBox InterviewPane;

    @FXML
    private Pane datePane;

    private final InterviewDAO interviewDAO = new InterviewDAO();
    private HashMap<Integer, String> monthsHashMap = new HashMap<>();
    private int Month = Calendar.getInstance().get(Calendar.MONTH);
    private int Year = Calendar.getInstance().get(Calendar.YEAR);
    private int Day;

    @FXML
    void initialize() {

        monthsHashMap.put(0,"Январь");
        monthsHashMap.put(1,"Февраль");
        monthsHashMap.put(2,"Март");
        monthsHashMap.put(3,"Апрель");
        monthsHashMap.put(4,"Май");
        monthsHashMap.put(5,"Июнь");
        monthsHashMap.put(6,"Июль");
        monthsHashMap.put(7,"Август");
        monthsHashMap.put(8,"Сентябрь");
        monthsHashMap.put(9,"Октябрь");
        monthsHashMap.put(10,"Ноябрь");
        monthsHashMap.put(11,"Декабрь");

        setDays();
        setInterviews(Calendar.getInstance());
        nextBtn.setOnAction(actionEvent -> {
            Month += 1;
                setDays();
        });
        prevBtn.setOnAction(actionEvent -> {
            Month -= 1;
                setDays();
        });
    }


    private void setInterviews(Calendar date){
        InterviewPane.getChildren().clear();
        datePane.getChildren().clear();
        ResultSet resultSet = null;
        try {
            Label curr_date = new Label(new SimpleDateFormat("dd.MM.yyyy").format(date.getTime()));
            curr_date.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/grouplist.css")).toExternalForm());
            datePane.getChildren().add(curr_date);
            resultSet = interviewDAO.getInterwies(date);
            while (resultSet.next()){
                Interview interview = new Interview();
                interview.setInterview_time(resultSet.getTime("interview_time"));
                String time = interview.getInterview_time().toString().substring(0,5);
                Candidate candidate = new Candidate();
                Position position = new Position();
                candidate.setSurname(resultSet.getString("surname"));
                candidate.setName(resultSet.getString("name"));
                candidate.setPatronymic(resultSet.getString("patronymic"));
                candidate.setDate_of_birthday(resultSet.getDate("date_of_birthday"));
                candidate.setMail(resultSet.getString("mail"));
                candidate.setPhone(resultSet.getString("phone"));
                candidate.setExperience(resultSet.getInt("experience"));
                candidate.setPhoto(resultSet.getString("photo"));
                position.setPosition(resultSet.getString("position"));

                Button btn = new Button(time+" "+candidate.getSurname()+" "+candidate.getName()+" "+candidate.getPatronymic()+"\nВакансия: "+position.getPosition());
                btn.setOnAction(actionEvent -> {openInfo(candidate);});
                btn.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/people.css")).toExternalForm());
                InterviewPane.getChildren().add(btn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void setDays() {
        grid.getChildren().clear();

        if (Month == -1){
            Month = 11;
            Year -= 1;
        }
        if (Month == 12){
            Month = 0;
            Year += 1;
        }

        Calendar calendar = new GregorianCalendar(Year, Month, 1);
        month.setText(monthsHashMap.get(Month) + ", " + Year);

        int dayOfWeek = 7-(8-calendar.get(Calendar.DAY_OF_WEEK))%7;
        int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        int day = 1;
            for (int i=0; i<6; i++){
                for (int j=0; j<7; j++){
                    Pane pane = new Pane();
                    pane.setStyle("-fx-background-color: #E0FFFF; -fx-background-radius: 4 ");
                    GridPane.setMargin(pane, new Insets(5, 5, 5, 5));
                    grid.add(pane, j, i);
                    if (!(i==0 && j<dayOfWeek-1) && (day!=days+1)) {
                        Button btn = new Button("" + day);
                        btn.setOnAction(actionEvent -> {
                            resetInterviews(btn);
                        });
                        btn.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/calendar.css")).toExternalForm());
                        grid.add(btn, j, i);
                        day += 1;
                }
            }
        }
    }

    private void resetInterviews(Button btn) {
        Day = Integer.parseInt(btn.getText());
        Calendar date = Calendar.getInstance();
        date.set(Year, Month, Day);
        setInterviews(date);
    }

    private void openInfo(Candidate candidate) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Parent root = null;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/info.fxml"));
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        InfoController infoController = loader.getController();
        try {
            infoController.setInfo(candidate);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

