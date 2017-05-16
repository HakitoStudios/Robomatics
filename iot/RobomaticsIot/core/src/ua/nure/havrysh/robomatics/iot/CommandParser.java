package ua.nure.havrysh.robomatics.iot;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParser {

    Pattern pattern = Pattern.compile();
    public Command parse(Command command, String commandString){
        Matcher matcher  = pattern.matcher(commandString);
        if(matcher.find()){
         command.throttle = Integer.valueOf(matcher.group(1));
         command.steering = Integer.valueOf(matcher.group(2));
        }
        return command;
    }

    public static class Command{
        public float steering=90;

        public float throttle=127;
    }
}
