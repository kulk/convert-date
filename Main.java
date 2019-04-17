import java.awt.*;
import java.util.Scanner;
import java.util.regex.*;
import java.text.SimpleDateFormat;  
import java.util.Date; 

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
            boolean z = ValidateDate.validator(userDate);
            if(z == true){
                break;
            }else {
                System.out.println("Please use format dd-mm-yyyy: ");
            }
        }
        return userDate;
    }
}
// Validates user input has correct date format (Improve function later)
class ValidateDate {
  static boolean validator(String userDate){
    boolean b3 = Pattern.matches("\\d\\d-\\d\\d-\\d\\d\\d\\d", userDate);
    return b3;
  }
}
