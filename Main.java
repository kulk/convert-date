import java.awt.*;
import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)throws Exception{
      String userDate = UserInput.getInput();
      String dateArray[] = AppDate.converter(userDate);
      String ordinal = AppDate.getOrdinal(dateArray[1]);
      System.out.println(dateArray[0] + " the " + dateArray[1] + ordinal + " of " + dateArray[2] + " " + dateArray[3]);
    }
}
// Runs while loop and get's user input
class UserInput {
    static String getInput(){
        Scanner myObj = new Scanner(System.in);
        String userDate; // Variable has to be declared outside the while loop
        while(true) {
            System.out.println("Enter a date (dd-mm-yyyy): ");
            userDate = myObj.nextLine();
            boolean z1 = ValidateDate.validateFormat(userDate);
            boolean z2 = ValidateDate.dayMonthCheck(userDate);
            if(z1 == true && z2 == true){
                break;
            }else {
                System.out.println("Date not valid. Please use format dd-mm-yyyy: ");
            }
        }
        return userDate;
    }
}
class AppDate {
  public static String[] converter(String userDate)throws Exception{
    //Converts user input to Day of the week, Day, Month, year  
    Date dateString = new SimpleDateFormat("dd-MM-yyyy").parse(userDate);      
    String[] dateArray = new String[4];
    SimpleDateFormat weekDay = new SimpleDateFormat("EEEE"); 
    SimpleDateFormat day = new SimpleDateFormat("d");      
    SimpleDateFormat month = new SimpleDateFormat("MMMM");
    SimpleDateFormat year = new SimpleDateFormat("yyyy");
    dateArray[0] = weekDay.format(dateString);
    dateArray[1] = day.format(dateString);
    dateArray[2] = month.format(dateString);      
    dateArray[3] = year.format(dateString);
    return dateArray;
  }
  static String getOrdinal(String i) {
    //Get ordinal part of day (st,nd, rd, th)
    String x;
    if(i.equals("1") || i.equals("21") ){
      x = "st";   
    }else if(i.equals("2") || i.equals("22") ){
      x = "nd";
    }else if(i.equals("3") || i.equals("23") ){
      x = "rd";
    }else{
      x = "th";
    }
    return x;
  }
}
// Validates user input has correct date format
class ValidateDate {  
  static boolean validateFormat(String userDate){
    // Regex to check the date fotmat
    boolean valid = Pattern.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d", userDate);
    return valid;
  }
  static boolean dayMonthCheck(String userDate){
    //Returns false if  day > 31 or month > 12
    ArrayList<Integer> regexList = new ArrayList<>();
    Pattern pattern = Pattern.compile("\\d\\d");
    Matcher matcher = pattern.matcher(userDate.replaceAll("-", ""));
    while(matcher.find()){
      int regexInt = Integer.parseInt(matcher.group());//Converts String to int
      regexList.add(regexInt);
    }
    boolean valid = true;
    if(regexList.get(0) > 31 
        || regexList.get(0) == 0 
        || regexList.get(1) > 12 
        || regexList.get(1) == 0){
      valid = false;
    }
    return valid;    
  }
}