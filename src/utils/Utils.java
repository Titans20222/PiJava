/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;


import controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Hamdi
 */
public class Utils {

    //public final static String dir = "file:" + System.getProperty("user.dir").replace("Desktop", "WEB/web/uploads/").replace("\\", "/");
    public final static String dir = "http://localhost/pidev/WEB/web/uploads/";
    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public static String generateCode(int length) {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < length) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static boolean verifEmail(String email) {

//        String token = "secret_084d6973f2c771713959f58ae1848bc8";
//        NeverbounceClient neverbounceClient = NeverbounceClientFactory.create(token);
//        AccountInfoResponse accountInfoResponse = neverbounceClient
//                .createAccountInfoRequest()
//                .execute();
//        SingleCheckResponse singleCheckResponse = neverbounceClient
//                .prepareSingleCheckRequest()
//                .withEmail(email)
//                .withAddressInfo(false)
//                .withCreditsInfo(false)
//                .withTimeout(15000)
//                .build()
//                .execute();
        //return singleCheckResponse.getResult().toString().equals("VALID");// VALID
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

   public static void sendMail(String to_mail, String code) {

        final String userName = "sanaatyTn@gmail.com";
        final String password = "sanewbar";

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(to_mail));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_mail));
            msg.setSubject("Votre compte Sanaaty requiert votre attention");

            msg.setSentDate(new Date(System.currentTimeMillis()));
            String htmlBody = new String(Files.readAllBytes(Paths.get("src/Utils/mail.html"))).replace("123456", code);

            msg.setContent(htmlBody, "text/html");

            Runnable runnable = () -> {
                try {
                    Transport.send(msg);
                    System.out.println("Mail envoyé");
                } catch (MessagingException ex) {
                    System.out.println("Echec de l'envoie du mail \n" + ex.getMessage());
                }
            };

            Thread thread = new Thread(runnable);
            thread.start();

        } catch (IOException | MessagingException e) {
            System.out.println("Echec de l'envoie du mail \n" + e.getMessage());
        }

    }


    /**
     * **** To use this function ****
     *
     * Retrieve value From MySQL with : resultatSet.getString("date") =
     * 2018-02-10 01:09:51.0
     *
     * @param date from MySQL
     * @return null if date is different from this pattern ("yyyy-MM-dd
     * HH:mm:ss")
     */
    public static LocalDateTime getLocalDateTime(String date) {

        LocalDateTime l;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try {
            date = date.substring(0, 19);
            l = LocalDateTime.parse(date, formatter);
        } catch (Exception e) {
            //System.out.println("Exception in getLocalDate :\n"+e.getMessage());
            return null;
        }
        return l;
    }

    /**
     * **** To use this function ****
     *
     * Retrieve value From MySQL with : resultatSet.getString("date") =
     * 2018-02-10
     *
     * @param date from MySQL
     * @return null if date is different from this pattern ("yyyy-MM-dd")
     */
    public static LocalDate getLocalDate(String date) {

        LocalDate l;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            date = date.substring(0, 10);
            l = LocalDate.parse(date, formatter);
        } catch (Exception e) {
            //System.out.println("Exception in getLocalDate :\n"+e.getMessage());
            return null;
        }
        return l;
    }

    public static void showAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        alert.showAndWait();

    }

    public static Alert getAlert(Alert.AlertType type, String title, String header, String text) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(text);
        return alert;

    }

    public static void showTrayNotification(NotificationType type, String title, String header, String text, String photoNameInServer, int time) {
        TrayNotification tray = new TrayNotification();
        tray.setNotificationType(type);
        tray.setTitle(title);
        tray.setMessage(text);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.millis(time));
        tray.setRectangleFill(Color.valueOf("#1e90ff"));
        System.out.println(dir + photoNameInServer);
        tray.setImage(new Image(dir + photoNameInServer));
    }

    public static Stage getAnotherStage(FXMLLoader loader, String title) {
        Stage stage = new Stage();
        Parent root = null;
        try {
                 root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

        stage.setTitle(title);

        //stage.initStyle(StageStyle.DECORATED);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);

        stage.initModality(Modality.APPLICATION_MODAL);
        Scene scene = new Scene(root);

        scene.setFill(Color.TRANSPARENT);
        stage.getIcons().add(new Image("/images/SANAATY.png"));
        stage.centerOnScreen();
        mouseDrag md = new mouseDrag();
        md.setDragged(root, stage);
        stage.setScene(scene);
        stage.setMaximized(false);
        stage.setResizable(false);
        return stage;
    }



    private static String getFileChecksum(MessageDigest digest, File file) throws IOException {
        //Get file input stream for reading the file content
        FileInputStream fis = new FileInputStream(file);

        //Create byte array to read data in chunks
        byte[] byteArray = new byte[1024];
        int bytesCount = 0;

        //Read file data and update in message digest
        while ((bytesCount = fis.read(byteArray)) != -1) {
            digest.update(byteArray, 0, bytesCount);
        };

        //close the stream; We don't need it now.
        fis.close();

        //Get the hash's bytes
        byte[] bytes = digest.digest();

        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }

        //return complete hash
        return sb.toString();
    }
}
