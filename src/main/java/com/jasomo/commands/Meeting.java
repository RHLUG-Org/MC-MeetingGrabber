package com.jasomo.commands;

import com.google.gson.Gson;
import com.jasomo.MeetingGrabber;
import com.jasomo.util.MeetingInfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Meeting implements CommandExecutor {
    private MeetingGrabber instance;

    public Meeting(MeetingGrabber instance) {
        this.instance = instance;
    }

    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        String url_string = (String) instance.getConfig().get("url");
        try {
            URL url = new URL(url_string);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int response = connection.getResponseCode();
            if (response != 200) {
                commandSender.sendMessage("HTTP Response Error: " + response);
            } else {
                Scanner scan = new Scanner(url.openStream());
                String json_data = "";
                while(scan.hasNext()){
                    json_data+=scan.next();
                }
                Gson gson = new Gson();
                MeetingInfo currentMeeting = gson.fromJson(json_data, MeetingInfo.class);
                commandSender.sendMessage("The next meeting is at "+currentMeeting.getDate() + " "+ currentMeeting.getNumeric_date() +" from " + currentMeeting.getTime());
                scan.close();
            }
        } catch (Exception e) {
            commandSender.sendMessage("Could not retrieve URL or Parse JSON");
        }
        return false;
    }
}
