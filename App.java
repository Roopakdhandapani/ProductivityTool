package PaintProject;

import com.opencsv.CSVReader;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

class PaintProject {
    static Integer passed[] = new Integer[100000];
    static Integer failed[] = new Integer[100000];
    static Integer blck[] = new Integer[100000];
    static Integer knownFailures[] = new Integer[100000];
    static String testedon[] = new String[100000];
    static String name1[] = new String[100000];
    static String status[] = new String[100000];
    static Integer flag[] = new Integer[100000];
    static Integer len;
    static String useid,pass;
    static int i = 0, j = 0, no = 0;
    static List<String> distinctElements;
    static List<String> list = new ArrayList<String>();
    static ArrayList<Integer> list1 = new ArrayList<Integer>();
    static ArrayList<Integer> list2 = new ArrayList<Integer>();
    static WebDriver driver;
    static String startDate = "2020-01-01";
    public static ArrayList<String> buglist = new ArrayList<String>();
    public static ArrayList<String> prioritylist = new ArrayList<String>();
    public static ArrayList<String> submitterlist = new ArrayList<String>();
    public static ArrayList<String> buglistwithDepri = new ArrayList<String>();
    public static ArrayList<String> prioritylistwithDepri = new ArrayList<String>();
    public static ArrayList<String> submitterlistwithDepri = new ArrayList<String>();
    static String home = System.getProperty("user.home");
    static int count = 0;
    static int count1 = 0;
    static String tem = null;
    static String temp = null;
    static String s;
    static int size11,size12,size13;
    static int total = 0, mon, dat, fromDate, toDate;
    static String url, TestRunUrl;
    static String url1;
    static String mailid, name, sub;

    int fl = 0;
    static String start = "<html>\n" +
            "<head>\n" +
            "<style>\n" +
            "table, th, td {\n" +
            "  border: 1px solid black;\n" +
            "  border-collapse: collapse;\n" +
            "}\n" +
            "</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "<br> Hi Team, <br><br>Kindly find the output below <br><br>";
    static String htmlMsg = "";

    public static void main(String[] args) throws InterruptedException {
        String os = System.getProperty("os.name").toLowerCase();
        System.out.println(os);
        Scanner input = new Scanner(System.in);
        System.out.println("Please provide your name: ");
        name = input.nextLine();
        System.out.println("Enter the mail id you would send the report to: ");
        mailid = input.nextLine();
        System.out.println("Enter the password for testrail run: ");
        pass = input.nextLine();
        System.out.println("Enter the subject ");
        sub = input.nextLine();
        del();
        Thread.sleep(3000);
        System.out.println("CSV Files deleted");
        System.out.println("1.TestRun Url");
        System.out.println("2.MileStone Url");
        int choices = input.nextInt();
        if(choices==1){
            choiceTestRun();
        }
        else if(choices==2) {
            choiceMilestone();
        }
        else {
            System.out.println("Select appropriate choice");
        }
    }

    public static void choiceTestRun() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the TestRun url: ");
        TestRunUrl = input.nextLine();
        System.out.println("TestRun Execution Status Initiated..");
        System.out.println("1.Get data with date & month");
        System.out.println("2.Get data without date & month");
        System.out.print("Enter an integer: ");
        int number = input.nextInt();
        if(number==1) {
            System.out.println("Enter the month number: ");
            mon = input.nextInt();
            System.out.println("Enter the date number: ");
            dat = input.nextInt();
            testRundaily();
        }
        else{
            testRundaily1();
        }

    }

    public static void choiceMilestone() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the first milestone url: ");
        url = input.nextLine();
        System.out.println("Enter the second milestone url: ");
        url1 = input.nextLine();
        System.out.println("1.Month");
        System.out.println("2.Daily");
        System.out.println("3.Date Period");
        System.out.print("Enter an integer: ");
        int number = input.nextInt();
        if (number == 1) {
            System.out.println("Monthly Execution Status Initiated..");
            System.out.println("Enter the month number: ");
            mon = input.nextInt();
            month();
        } else if (number == 3) {
            System.out.println("Weekly Execution Status Initiated..");
            System.out.println("Enter the month number: ");
            mon = input.nextInt();
            System.out.println("Enter the From date number: ");
            fromDate = input.nextInt();
            System.out.println("Enter the To date number: ");
            toDate = input.nextInt();
            weekly();
        }else if(number==2) {
            System.out.println("Daily Execution Status Initiated..");
            System.out.println("Enter the month number: ");
            mon = input.nextInt();
            System.out.println("Enter the date number: ");
            dat = input.nextInt();
            daily();
        }
        else {
            System.out.println("Select appropriate choice");
        }

    }

    public static void testRundaily() {
        try {
            reportTestRun();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        tabledaily();
        //tabledailywihoutdate();
        //bugReport();
        try {
            se(htmlMsg);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static void testRundaily1() {
        try {
            reportTestRun();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        //tabledaily();
        tabledailywihoutdate();
        //bugReport();
        try {
            se(htmlMsg);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        driver.quit();
    }


    public static void month() {
        try {
            report();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        try {
            report1();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        tablemonthly();
        try {
            se(htmlMsg);
        } catch (javax.mail.MessagingException | MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void weekly() {
        try {
            report();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        try {
            report1();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        tableweekly();
        //bugReport();
        try {
            se(htmlMsg);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void daily() {
        try {
            report();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        try {
            report1();
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        }
        tabledaily();
        //bugReport();
        try {
            se(htmlMsg);
        } catch (javax.mail.MessagingException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /*public static void bugReport() {
        getUrl();
        bugwithoutDepriTable();
        bugwithDepriTable();
    }*/

    public static void reportTestRun() throws NullPointerException, javax.mail.MessagingException {
        for (i = 0; i < 100000; i++) {
            name1[i] = status[i] = null;
            blck[i] = passed[i] = failed[i] = knownFailures[i] = flag[i] = 0;
        }
        System.setProperty("webdriver.chrome.driver", "/Users/droo/Automation/Selenium/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.get(TestRunUrl);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(mailid);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
        //driver.findElement(By.xpath("//div[@class=\"single-sign-on\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"button_primary\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[3]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:status_id\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_by\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_on\"]")).click();
        driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void report() throws NullPointerException, javax.mail.MessagingException {
        for (i = 0; i < 100000; i++) {
            name1[i] = status[i] = null;
            blck[i] = passed[i] = failed[i]= knownFailures[i] = flag[i] = 0;
        }
        System.setProperty("webdriver.chrome.driver", "/Users/droo/Automation/Selenium/chromedriver");
        ChromeOptions options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        driver = new ChromeDriver(options);
        driver.get(url);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       driver.findElement(By.xpath("//*[@id=\"name\"]")).sendKeys(mailid);
        driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys(pass);
        //driver.findElement(By.xpath("//div[@class=\"single-sign-on\"]")).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//*[@id=\"button_primary\"]")).click();
        List<WebElement> list2 = driver.findElements(By.xpath("(//*[@class=\"summary-title summary-title-compact text-ppp\"])"));
        count1 = list2.size();
        System.out.println(count1);
        for (i = 1; i <= count1; i++) {
            String a = driver.findElement(By.xpath("(//*[@class=\"summary-title summary-title-compact text-ppp\"])[" + i + "]")).getText();
            System.out.println(a);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("(//div[@class='summary-title summary-title-compact text-ppp'])[" + i + "]//a")).click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[3]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:status_id\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_by\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_on\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.get(url);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void report1() throws NullPointerException, javax.mail.MessagingException {
        driver.get(url1);
        List<WebElement> list2 = driver.findElements(By.xpath("(//*[@class=\"summary-title summary-title-compact text-ppp\"])"));
        count1 = list2.size();
        System.out.println(count1);
        for (i = 1; i <= count1; i++) {
            String a = driver.findElement(By.xpath("(//*[@class=\"summary-title summary-title-compact text-ppp\"])[" + i + "]")).getText();
            System.out.println(a);
            WebDriverWait wait = new WebDriverWait(driver, 20);
            driver.findElement(By.xpath("(//div[@class='summary-title summary-title-compact text-ppp'])[" + i + "]//a")).click();
            driver.findElement(By.xpath("//*[@id=\"content-header\"]//*[@class=\"dropdownLink link-tooltip\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportDropdown\"]/ul/li[3]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns_control\"]/div/div[1]/div/a[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:status_id\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_by\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportCsvColumns-tests:tested_on\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"exportSubmit\"]")).click();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           driver.get(url1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        driver.quit();
    }

    public static void tablemonthly() {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    String[] nextRecord;
                    // we are going to read data line by line
                    while ((nextRecord = csvReader.readNext()) != null) {
                        String temp = nextRecord[0];
                        //System.out.println(temp);
                        String tem = nextRecord[1]; // holds name
                        //System.out.println(tem);
                        String tempr = nextRecord[2]; //holds tested on
                        Boolean temp1 = temp.contains("Untested");
                        Boolean temp2 = tem.equals("Tested By");

                        for (int date = 1; date <= 30; date++) {
                            Boolean getMonth = tempr.contains(mon + "/" + date + "/2022");
                            if (!temp1 && !temp2) {
                                if (getMonth) {
                                    if (fl > 1) {
                                        name1[count] = tem;
                                        status[count] = temp;
                                        list.add(tem);
                                        count++;
                                    } else {
                                        fl++;
                                    }
                                }
                            }
                        }
                    }

                } catch (IOException e) {

                }
            }
        }
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);
        Collections.sort(distinctElements);
        //Let's verify distinct elements
        //System.out.println(distinctElements);
        len = distinctElements.size();
        System.out.println(len);
        int sum = 0;
        int sum1 = 0;
        for (i = 0; i < len; i++) {
            no = 0;
            for (j = 0; j < count; j++) {
                if (name1[j].equals(distinctElements.get(i))) {
                    if (flag[j] == 0) {
                        flag[j] = 1;
                        if (status[j].equals("Passed")) {
                            passed[i]++;
                            no++;
                        } else if (status[j].contains("Failed")) {
                            failed[i]++;
                            no++;
                        } else if (status[j].contains("Blocked")) {
                            no++;
                            blck[i]++;
                        }else if (status[j].contains("Known_Failures")) {
                            no++;
                            knownFailures[i]++;
                        }
                    }

                }
            }
            list1.add(no);

            // System.out.println("Name:" + distinctElements.get(i) + " Total:" + list1.get(i) + " Passed:" + passed[i] + " Failed:" + failed[i] + "Blocked" + blck[i]);
        }

        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Name</th>\n" +
                "    <th>Total</th> \n" +
                "    <th>Passed</th>\n" +
                "    <th>Failed</th>\n" +
                "    <th>Blocked</th> \n" +
                "    <th>knownFailures</th> \n" +
                "    </tr>\n");
        size11 = list1.size();
        for (int k = 0; k < size11; k++) {
            System.out.println(list1.get(k));
            total = total + list1.get(k);
            System.out.println(total);
        }
        for (int i = 0; i < len; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            "<td>" + distinctElements.get(i) + "</td>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + list1.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + passed[i] + "</td>\n" +
                            "    <td align=\"center\">" + failed[i] + "</td>\n" +
                            "    <td align=\"center\">" + blck[i] + "</td>\n" +
                            "    <td align=\"center\">" + knownFailures[i] + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }
        /*for (int i = 0; i < len; i++)
            System.out.println("Name:" + distinctElements.get(i) + " Total:" + list1.get(i) + " Passed:" + passed[i] + " Failed:" + failed[i] + "Blocked" + blck[i]);*/

    //  cm(distinctElements, list1, len, passed, failed, blck);
    public static void tableweekly() {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    String[] nextRecord;
                    // we are going to read data line by line
                    while ((nextRecord = csvReader.readNext()) != null) {
                        String temp = nextRecord[0];
                        //System.out.println(temp);
                        String tem = nextRecord[1]; // holds name
                        //System.out.println(tem);
                        String tempr = nextRecord[2]; //holds tested on
                        Boolean temp1 = temp.contains("Untested");
                        Boolean temp2 = tem.equals("Tested By");
                        for (int date = fromDate; date <= toDate; date++) {
                            Boolean getMonth = tempr.contains(mon + "/" + date + "/2022");
                            if (!temp1 && !temp2) {
                                if (getMonth) {
                                    if (fl > 1) {
                                        name1[count] = tem;
                                        status[count] = temp;
                                        list.add(tem);
                                        count++;
                                    } else {
                                        fl++;
                                    }
                                }
                            }
                        }
                    }

                } catch (IOException e) {

                }
            }
        }
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);
        Collections.sort(distinctElements);
        //Let's verify distinct elements
        //System.out.println(distinctElements);
        len = distinctElements.size();
        System.out.println(len);
        int sum = 0;
        int sum1 = 0;
        for (i = 0; i < len; i++) {
            no = 0;
            for (j = 0; j < count; j++) {
                if (name1[j].equals(distinctElements.get(i))) {
                       if (flag[j] == 0) {
                           flag[j] = 1;
                           if (status[j].equals("Passed")) {
                               passed[i]++;
                               no++;
                           } else if (status[j].contains("Failed")) {
                               failed[i]++;
                               no++;
                           } else if (status[j].contains("Blocked")) {
                               no++;
                               blck[i]++;
                           }
                       }
                }
            }
            list1.add(no);

            // System.out.println("Name:" + distinctElements.get(i) + " Total:" + list1.get(i) + " Passed:" + passed[i] + " Failed:" + failed[i] + "Blocked" + blck[i]);
        }
        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Name</th>\n" +
                "    <th>Total</th> \n" +
                "    <th>Passed</th>\n" +
                "    <th>Failed</th>\n" +
                "    <th>Blocked</th> \n" +
                "    </tr>\n");
        size11 = list1.size();
        for (int k = 0; k < size11; k++) {
            System.out.println(list1.get(k));
            total = total + list1.get(k);
            System.out.println(total);
        }
        for (int i = 0; i < len; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            "<td>" + distinctElements.get(i) + "</td>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + list1.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + passed[i] + "</td>\n" +
                            "    <td align=\"center\">" + failed[i] + "</td>\n" +
                            "    <td align=\"center\">" + blck[i] + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }

    public static void tabledaily() {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    String[] nextRecord;
                    // we are going to read data line by line
                    while ((nextRecord = csvReader.readNext()) != null) {
                        String temp = nextRecord[0];
                        //System.out.println(temp);
                        String tem = nextRecord[1]; // holds name
                        //System.out.println(tem);
                        String tempr = nextRecord[2]; //holds tested on
                        Boolean temp1 = temp.contains("Untested");
                        Boolean temp2 = tem.equals("Tested By");
                        Boolean d = tempr.contains(mon + "/" + dat + "/2022");
                        System.out.println(d + "Checking");
                        if (!temp1 && !temp2) {
                            if (d)
                                if (fl >= 0) {
                                    name1[count] = tem;
                                    status[count] = temp;
                                    list.add(tem);
                                    System.out.println(tempr);
                                    System.out.println(tempr);
                                    //System.out.println(tem);
                                    System.out.println(name1[count]);
                                    System.out.println(status[count]);
                                    count++;
                                } else {
                                    fl++;
                                }
                        }
                        //System.out.println(count);
                    }

                } catch (IOException e) {

                }
            }
        }
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);
        Collections.sort(distinctElements);
        //Let's verify distinct elements
        //System.out.println(distinctElements);
        len = distinctElements.size();
        System.out.println(len);
        int sum = 0;
        int sum1 = 0;
        for (i = 0; i < len; i++) {
            no = 0;
            for (j = 0; j < count; j++) {
                if (name1[j].equals(distinctElements.get(i))) {
                    if (flag[j] == 0) {
                        flag[j] = 1;
                        if (status[j].equals("Passed")) {
                            passed[i]++;
                            no++;
                        } else if (status[j].contains("Failed")) {
                            failed[i]++;
                            no++;
                        } else if (status[j].contains("Blocked")) {
                            no++;
                            blck[i]++;
                        } else if (status[j].contains("Known_Failures")) {
                            no++;
                            knownFailures[i]++;
                        }
                    }

                }
            }
            list1.add(no);

            // System.out.println("Name:" + distinctElements.get(i) + " Total:" + list1.get(i) + " Passed:" + passed[i] + " Failed:" + failed[i] + "Blocked" + blck[i]);
        }
        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Name</th>\n" +
                "    <th>Total</th> \n" +
                "    <th>Passed</th>\n" +
                "    <th>Failed</th>\n" +
                "    <th>Blocked</th> \n" +
                "    <th>KnownFailures</th> \n" +
                "    </tr>\n");
        size11 = list1.size();
        for (int k = 0; k < size11; k++) {
            System.out.println(list1.get(k));
            total = total + list1.get(k);
            System.out.println(total);
        }
        for (int i = 0; i < len; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            "<td>" + distinctElements.get(i) + "</td>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + list1.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + passed[i] + "</td>\n" +
                            "    <td align=\"center\">" + failed[i] + "</td>\n" +
                            "    <td align=\"center\">" + blck[i] + "</td>\n" +
                            "    <td align=\"center\">" + knownFailures[i] + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }

    /*public static void getUrl() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/droo/automation/chrome/ChromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(
                "https://issues.amazon.com/issues/search?q=containingFolder%3A(1059de2f-3f25-41f0-8c6b-b44e35c5f737+OR+1ec41e64-2154-482a-8d06-864c0ea00429+OR+18a3917e-4f34-49fb-b1b1-9cdbd4fcb1ce+OR+80aacd71-eb40-48c1-b50c-7388bf542708+OR+dd7b756b-1217-47bc-9775-8d94455dd133+OR+665d98de-0252-4362-9b55-45e02454dfa5)+submitter%3A(sarevath+OR+vbalacha+OR+puviyr+OR+kavitr+OR+sakr+OR+cavishe+OR+pritheec+OR+shisaxen+OR+conmargo+OR+kimper+OR+jpriyaj+OR+amznsura+OR+yogeshwm+OR+fparveen+OR+srakumar+OR+neelimak+OR+smhukhu+OR+rnivedha+OR+monesh+OR+gdhna+OR+bdeepi+OR+smhukhu+OR+aseshang+OR+prethia)+status%3A(Open)+createDate%3A(%5B"
                        + startDate
                        + "T18%3A30%3A00.000Z..%5D)+-label%3A(0290f9fc-fb8c-4517-9bc3-706b076b03f6)&sort=lastUpdatedConversationDate+desc&selectedDocument=20f008bf-9c34-444b-8665-31b4c1948276");
        gridView();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String getNum = driver.findElement(By.xpath("//strong[@class='displayedItemCount']")).getText();
        int numVal = Integer.parseInt(getNum);
        getValues(numVal);
        driver.get(
                "https://issues.amazon.com/issues/search?q=containingFolder%3A(1059de2f-3f25-41f0-8c6b-b44e35c5f737+OR+1ec41e64-2154-482a-8d06-864c0ea00429+OR+18a3917e-4f34-49fb-b1b1-9cdbd4fcb1ce+OR+80aacd71-eb40-48c1-b50c-7388bf542708+OR+dd7b756b-1217-47bc-9775-8d94455dd133+OR+665d98de-0252-4362-9b55-45e02454dfa5)+submitter%3A(sarevath+OR+vbalacha+OR+puviyr+OR+kavitr+OR+sakr+OR+cavishe+OR+pritheec+OR+shisaxen+OR+conmargo+OR+kimper+OR+jpriyaj+OR+amznsura+OR+yogeshwm+OR+fparveen+OR+srakumar+OR+neelimak+OR+smhukhu+OR+rnivedha+OR+monesh+OR+gdhna+OR+bdeepi+OR+smhukhu+OR+aseshang+OR+prethia)+status%3A(Open)+createDate%3A(%5B"
                        + startDate
                        + "T18%3A30%3A00.000Z..%5D)+label%3A(0290f9fc-fb8c-4517-9bc3-706b076b03f6)&sort=lastUpdatedConversationDate+desc&selectedDocument=20f008bf-9c34-444b-8665-31b4c1948276");
        gridView();
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String getNum1 = driver.findElement(By.xpath("//strong[@class='displayedItemCount']")).getText();
        int numVal1 = Integer.parseInt(getNum1);
        getValuesWithDepri(numVal1);
        int totalNum = numVal + numVal1;
        System.out.println("Total Number of Bugs in Open State "+totalNum);
    }*/

    /*public static void gridView() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//button[@id='create-issue']")).click();
    }

    public static void getValues(int numVal) {
        for(int i=1;i<=numVal;i++) {
            String bugID = driver.findElement(By.xpath("(//td[@data-column-key='alias'])["+i+"]")).getText();
            String submitter = driver.findElement(By.xpath("(//td[@data-column-key='submitter'])["+i+"]")).getText();
            String priority = driver.findElement(By.xpath("(//td[@data-column-key='priority'])["+i+"]")).getText();
            buglist.add(bugID);
            submitterlist.add(submitter);
            prioritylist.add(priority);
        }
        int size12 = buglist.size();
        for(int i=0;i < size12;i++) {
            System.out.println(buglist.get(i));
        }
    }

    public static void getValuesWithDepri(int numVal) {
        for(int i=1;i<=numVal;i++) {
            String bugID = driver.findElement(By.xpath("(//td[@data-column-key='alias'])["+i+"]")).getText();
            String submitter = driver.findElement(By.xpath("(//td[@data-column-key='submitter'])["+i+"]")).getText();
            String priority = driver.findElement(By.xpath("(//td[@data-column-key='priority'])["+i+"]")).getText();
            buglistwithDepri.add(bugID);
            submitterlistwithDepri.add(submitter);
            prioritylistwithDepri.add(priority);
        }
    }

    public static void bugwithoutDepriTable() {
        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Bug ID</th>\n" +
                "    <th>Priority</th> \n" +
                "    <th>Submitter</th>\n" +
                "    </tr>\n");
        size12 = buglist.size();
        for (int i = 0; i < size12; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + buglist.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + prioritylist.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + submitterlist.get(i) + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }

    public static void bugwithDepriTable() {
        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Bug ID</th>\n" +
                "    <th>Priority</th> \n" +
                "    <th>Submitter</th>\n" +
                "    </tr>\n");
        size13 = buglistwithDepri.size();
        for (int i = 0; i < size13; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + buglistwithDepri.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + prioritylistwithDepri.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + submitterlistwithDepri.get(i) + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }*/

    public static void tabledailywihoutdate() {
        String home = System.getProperty("user.home");
        File dir = new File(home + "/Downloads");
        int fl = 0, count = 0;
        //ArrayList<String> fileContents = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.getName().contains(".csv")) {
                s = file.getName();
                System.out.println(s);
                try {
                    FileReader fReader = new FileReader(file);
                    BufferedReader fileBuff = new BufferedReader(fReader);
                    CSVReader csvReader = new CSVReader(fileBuff);
                    String[] nextRecord;
                    // we are going to read data line by line
                    while ((nextRecord = csvReader.readNext()) != null) {
                        String temp = nextRecord[0];
                        //System.out.println(temp);
                        String tem = nextRecord[1]; // holds name
                        //System.out.println(tem);
                        String tempr = nextRecord[2]; //holds tested on
                        Boolean temp1 = temp.contains("Untested");
                        Boolean temp2 = tem.equals("Tested By");
                        //Boolean d = tempr.contains(mon + "/" + dat + "/2022");
                        if (!temp1 && !temp2) {
                           // if (d)
                                if (fl >= 0) {
                                    name1[count] = tem;
                                    status[count] = temp;
                                    list.add(tem);
                                    System.out.println(tempr);
                                    //System.out.println(tem);
                                    System.out.println(name1[count]);
                                    System.out.println(status[count]);
                                    count++;
                                } else {
                                    fl++;
                                }
                        }
                        //System.out.println(count);
                    }

                } catch (IOException e) {

                }
            }
        }
        List<String> distinctElements = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctElements);
        Collections.sort(distinctElements);
        //Let's verify distinct elements
        //System.out.println(distinctElements);
        len = distinctElements.size();
        System.out.println(len);
        int sum = 0;
        int sum1 = 0;
        for (i = 0; i < len; i++) {
            no = 0;
            for (j = 0; j < count; j++) {
                if (name1[j].equals(distinctElements.get(i))) {
                    if (flag[j] == 0) {
                        flag[j] = 1;
                        if (status[j].equals("Passed")) {
                            passed[i]++;
                            no++;
                        } else if (status[j].contains("Failed")) {
                            failed[i]++;
                            no++;
                        } else if (status[j].contains("Blocked")) {
                            blck[i]++;
                            no++;
                        }else if (status[j].contains("Known_Failures")) {
                            no++;
                            knownFailures[i]++;
                        }
                    }

                }
            }
            list1.add(no);

            // System.out.println("Name:" + distinctElements.get(i) + " Total:" + list1.get(i) + " Passed:" + passed[i] + " Failed:" + failed[i] + "Blocked" + blck[i]);
        }
        htmlMsg = htmlMsg.concat("<table style=\"width:100%\">\n" +
                "  <tr bgcolor=\"#25BCF0\">\n" +
                "    <th>Name</th>\n" +
                "    <th>Total</th> \n" +
                "    <th>Passed</th>\n" +
                "    <th>Failed</th>\n" +
                "    <th>Blocked</th> \n" +
                "    <th>KnownFailures</th> \n" +
                "    </tr>\n");
        size11 = list1.size();
        for (int k = 0; k < size11; k++) {
            System.out.println(list1.get(k));
            total = total + list1.get(k);
            System.out.println(total);
        }
        for (int i = 0; i < len; i++) {


            htmlMsg = htmlMsg.concat(
                    "    <tr>\n" +
                            "<td>" + distinctElements.get(i) + "</td>\n" +
                            //"    <td>" + distinctElements.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + list1.get(i) + "</td>\n" +
                            "    <td align=\"center\">" + passed[i] + "</td>\n" +
                            "    <td align=\"center\">" + failed[i] + "</td>\n" +
                            "    <td align=\"center\">" + blck[i] + "</td>\n" +
                            "    <td align=\"center\">" + knownFailures[i] + "</td>\n" +
                            "    </tr>\n");
        }
        htmlMsg = htmlMsg.concat("</table>\n");
    }

    public static void se(String msg) throws javax.mail.MessagingException, MessagingException {
        Calendar rightNow = Calendar.getInstance();
        int hour = rightNow.get(Calendar.HOUR_OF_DAY);
        int minute = rightNow.get(Calendar.MINUTE);
        // Create object of Property file
        Properties props = new Properties();

        // this will set host of server- you can change based on your requirement
        props.put("mail.smtp.host", "smtp.amazon.com");

        // This will handle the complete authentication
       /* Session session = Session.getDefaultInstance(props,

                new javax.mail.Authenticator() {

                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("no-reply-report@amazon.com",);

                    }

                });
                */

        Session session = Session.getDefaultInstance(props);
        //String raj = "";

        // Create object of MimeMessage class
        Message message = new MimeMessage(session);

        // Set the from address
        message.setFrom(new InternetAddress("no-reply-report@amazon.com"));
        // message.setFrom(new InternetAddress("no-reply-email@amazon.com"));

        // Set the recipient address
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mailid));
        //message.addRecipient(Message.RecipientType.CC, new InternetAddress("droo@amazon.com"));


        // Add the subject link
        message.setSubject(sub);

        //Add last part to the msg
        msg = start + msg +
                "<br>Total Number of Test cases Executed: " + total +
               /* "<br>Total Number of Open bugs without Depri Label :  " + size12 +
                "<br>Total Number of Open bugs with Depri Label : " + size13 +*/
                "<br>Note: This is an automated email , please don't reply to this email. " + "<br> Thanks," + "<br>"+name+"" +
                "</body>\n" +
                "</html>";

        System.out.println(msg);


        // set the content
        message.setContent(msg, "text/html");

        // finally send the email
        Transport.send(message);

        System.out.println("=====Email Sent=====");

    }

    public static void del() {
        //String home = System.getProperty("user.home");
        File directory = new File("/Users/droo/Downloads");
        for (File file : directory.listFiles()) {
            if (file.getName().contains(".csv")) {
                file.delete();
            }
        }
        System.out.println("CSV Files deleting...");
    }
}
