import java.awt.*;
import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;  
import java.util.Date;
import java.util.ArrayList;

class Main {
    public static void main(String[] args)throws Exception {
      String userDate = UserInput.getInput();
      String dateArray[] = ConvertDate.converter(userDate);
      String c1 = OrdinalNumber.converter(dateArray[0]);
      String c2 = dateArray[0] + " the " + dateArray[1] + c1 + " of " + dateArray[2] + " " + dateArray[3] ;
      System.out.println(c2);
    }
}
// Gets st, nd, rd for 1st 2nd 3rd etc.
class OrdinalNumber {
  static String converter(String i){
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
// Accepts user's date and returns Day, Month & year
class ConvertDate {
    public static String[] converter(String userDate)throws Exception{  
      Date date1 = new SimpleDateFormat("dd-MM-yyyy").parse(userDate);  
      
      String[] dateArray = new String[4];
      SimpleDateFormat weekDay = new SimpleDateFormat("EEEE"); 
      SimpleDateFormat day = new SimpleDateFormat("d");      
      SimpleDateFormat month = new SimpleDateFormat("MMMM");
      SimpleDateFormat year = new SimpleDateFormat("yyyy");
      dateArray[0] = weekDay.format(date1);
      dateArray[1] = day.format(date1);
      dateArray[2] = month.format(date1);      
      dateArray[3] = year.format(date1);
      return dateArray;
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
            boolean z1 = ValidateDate.validDateFormat(userDate);
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
// Validates user input has correct date format
class ValidateDate {
  
  static boolean validDateFormat(String userDate){
    boolean b3 = Pattern.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d", userDate);
    return b3;
  }

  static boolean dayMonthCheck(String userDate){
    String s2 = userDate.replaceAll("-", "");
    Pattern pattern = Pattern.compile("\\d\\d");
    Matcher matcher = pattern.matcher(s2);

    ArrayList<Integer> regexList = new ArrayList<>();
    while(matcher.find()){
      int regexInt = Integer.parseInt(matcher.group());//Converts String to int
      regexList.add(regexInt);
    }
    boolean b3 = true;
    if(regexList.get(0) > 31 
        || regexList.get(0) == 0 
        || regexList.get(1) > 12 
        || regexList.get(1) == 0){
      b3 = false;
    }
    return b3;    
  }

}