/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mercato.Model;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author PC-Yassine
 */
public class DesktopNotification {
    
    
    public static void notification(String title,String message,NotificationType nt){

        TrayNotification tray = new TrayNotification();
        tray.setTitle(title);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndWait();
        tray.setAnimationType(AnimationType.SLIDE);
        tray.showAndDismiss(Duration.seconds(3));
    }
}
