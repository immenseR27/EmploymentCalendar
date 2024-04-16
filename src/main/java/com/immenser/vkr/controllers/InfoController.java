package com.immenser.vkr.controllers;

import com.immenser.vkr.models.Candidate;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static com.immenser.vkr.config.FTP.ftpConn;

public class InfoController {

    @FXML
    private VBox title;

    @FXML
    private VBox vBox;

    @FXML
    void initialize() {
        title.getStylesheets().add(getClass().getResource("/styles/person.css").toExternalForm());
        vBox.getStylesheets().add(getClass().getResource("/styles/person.css").toExternalForm());
    }

    public void setInfo(Candidate candidate) throws IOException {
        Label fio = new Label(candidate.getSurname()+ " " + candidate.getName() + " " + candidate.getPatronymic());
        title.getChildren().add(fio);

        Date date_of_birthday = candidate.getDate_of_birthday();
        LocalDate date_of_birthday_to_local = date_of_birthday.toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int years = (int) ChronoUnit.YEARS.between(date_of_birthday_to_local, currentDate);

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String birthday = formatter.format(date_of_birthday);

        Label date_of_birth = new Label("Дата рождения: "+birthday+" ("+years+" "+getPostfix(years)+")");
        Label mail = new Label("Почта: "+candidate.getMail());
        Label phone = new Label("Номер: "+candidate.getPhone());

        Label exper;
        int experience = candidate.getExperience();
        if (experience==0){
            exper = new Label("Опыт работы: нет");
        }
        else {
            exper = new Label("Опыт работы: "+experience+" "+getPostfix(experience));
        }

        BufferedImage bufferedImage = ftpConn(candidate.getPhoto());

        Image image = SwingFXUtils.toFXImage(bufferedImage, null);
        ImageView photo = new ImageView(image);
        photo.setFitWidth(492);
        photo.setPreserveRatio(true);

        vBox.getChildren().addAll(photo, date_of_birth, mail, phone, exper);
    }


    private String getPostfix(int num){
        String postfix;
        switch (num%10) {
            case 1:
                postfix = "год";
                break;
            case 2:
            case 3:
            case 4:
                postfix = "года";
                break;
            default:
                postfix = "лет";
        }
        return postfix;
    }
}