package fuzzylogicsystem;

import fuzzylogicsystem.beans.Diet;
import fuzzylogicsystem.beans.Meal;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Heba
 */
public class DaibetesFuzzySystemType2FCM {


    /*
     * To change this license header, choose License Headers in Project Properties.
     * To change this template file, choose Tools | Templates
     * and open the template in the editor.
     */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        System.out.println("This Applcation Using Type 2 Fuzzy Logic To Learning From Data IF- Then Rules Using FCM With FOU 10% \n" );

        generateRules();


    }
    /****************************************************/
    public static double [] convertToDouble(String [] arrString){
        double[] arrDouble = new double[arrString.length];
        for(int i=0; i<arrString.length; i++)
        {
            arrDouble[i] = Double.parseDouble(arrString[i]);
        }
        return arrDouble;
    }
    /****************************************************/
    public static void generateRules() throws IOException {

        String csvFile = "./files/in/DataSet.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        /**
         * dsusanibar
         */
        BufferedWriter writerType2 = null;
        try {
            writerType2 = new BufferedWriter(
                    new FileWriter(
                            "./files/out/fuzzyouttype2.csv"
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
        /**
         * dsusanibar
         */

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                try {
                    // use comma as separator
                    String[] data = line.split(cvsSplitBy);
                    String[] rule=new String[500];
                    double doubleDataArr []=convertToDouble(data);
                    //int agee=Integer.parseInt(doubleDataArr[0]);
                    double age[]=getAge(doubleDataArr[0]);
                    double bMI[]=getBMI(doubleDataArr[4]);
                    // double management []=getManagment(doubleDataArr[5]);
                    double hight []=getHight(doubleDataArr[6]);
                    double wight []=getWight(doubleDataArr[7]);
                    double bMR[]=getBMR(doubleDataArr[8]);
                    String gender=getGender(doubleDataArr);
                    String type=getType(doubleDataArr);
                    String mang=getManagment(doubleDataArr);
                    String ageFuzzySets=getAgeFuzzySets(age);
                    String bMIFuzzySets= bMIFuzzySets(bMI);
                    String hightFuzzySets=hightFuzzySets(hight);
                    String wightFuzzySets=wightFuzzySets(wight);
                    String bMRFuzzySets=bMRFuzzySets(bMR);
                    FileWriter writer = new FileWriter("./files/out/test.csv");
                    writer.append("Age");
                    writer.append(',');
                    writer.append("Gender");
                    writer.append(',');
                    writer.append("Type");
                    writer.append(',');
                    writer.append("BMI");
                    writer.append(',');
                    writer.append("Managment");
                    writer.append(',');
                    writer.append("Hieght");
                    writer.append(',');
                    writer.append("Weight");
                    writer.append(',');
                    writer.append("Output");
                    writer.append('\n');

                    for(int i=0;i<doubleDataArr.length;i++){
                        String s="If Age is  "+ageFuzzySets+" and Gender is "+gender+" and Type of Diabetes is "+type+" and BMI is "+bMIFuzzySets+" and Managment is "+mang+" and Hight is "+hightFuzzySets+" and Wight is "+wightFuzzySets+" Then Outcome BMR is "+bMRFuzzySets;
                        writer.append(ageFuzzySets);
                        writer.append(',');
                        writer.append(gender);
                        writer.append(',');
                        writer.append(type);
                        writer.append(',');
                        writer.append(bMIFuzzySets);
                        writer.append(',');
                        writer.append(mang);
                        writer.append(',');
                        writer.append(hightFuzzySets);
                        writer.append(',');
                        writer.append(wightFuzzySets);
                        writer.append(',');
                        writer.append(bMRFuzzySets);
                        writer.append('\n');

                        defuzzifier(ageFuzzySets,gender,type,bMIFuzzySets,mang,hightFuzzySets,wightFuzzySets,bMRFuzzySets);


                        rule[i]=s;
                    }
                    writer.flush();
                    writer.close();

                    /**
                     * dsusanibar
                     */
                    writerType2.append(Arrays.toString(rule));
                    writerType2.append('\n');
                    /**
                     * dsusanibar
                     */

                    System.out.println(Arrays.toString(rule));

                } catch (Exception e){
                    //only for purpose of test
                    // e.printStackTrace();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * dsusanibar
             */
            if(writerType2 != null){
                try {
                    writerType2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    throw e;
                }
            }
            /**
             * dsusanibar
             */
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public static void defuzzifier(String age,String gend,String type,String bmi,String mang,String high,String wight, String out){
        List<Meal> listOfMeals = getMeals();

        for (Meal meal : listOfMeals) {
            if(meal.getOutcomeBMR().toLowerCase().contains(out.toLowerCase())){
                //diets availables
                System.out.println(meal.getDiet());
            }
        }
    }

    private final static List<Meal> optionOfMeals(){
        return getMeals();
    }

    static List<Meal> getMeals() {
        Diet diet1_1 = new Diet("diet1_1", 1, Arrays.asList("1x", "1y"));
        Diet diet1_2 = new Diet("diet1_2", 2, Arrays.asList("2x", "2y"));
        Diet diet1_3 = new Diet("diet1_3", 3, Arrays.asList("3x", "3y"));
        Diet diet1_4 = new Diet("diet1_4", 4, Arrays.asList("4x", "4y"));
        Diet diet1_5 = new Diet("diet1_5", 5, Arrays.asList("5x", "5y"));
        Diet diet1_6 = new Diet("diet1_6", 6, Arrays.asList("6x", "6y"));

        Diet diet2_1 = new Diet("diet2_1", 1, Arrays.asList("1z", "1w"));
        Diet diet2_2 = new Diet("diet2_2", 2, Arrays.asList("2z", "2w"));
        Diet diet2_3 = new Diet("diet2_3", 3, Arrays.asList("3z", "3w"));
        Diet diet2_4 = new Diet("diet2_4", 4, Arrays.asList("4z", "4w"));
        Diet diet2_5 = new Diet("diet2_5", 5, Arrays.asList("5z", "5w"));
        Diet diet2_6 = new Diet("diet2_6", 6, Arrays.asList("6z", "6w"));

        Diet diet3_1 = new Diet("diet3_1", 1, Arrays.asList("1a", "1b"));
        Diet diet3_2 = new Diet("diet3_2", 2, Arrays.asList("2a", "2b"));
        Diet diet3_3 = new Diet("diet3_3", 3, Arrays.asList("3a", "3b"));
        Diet diet3_4 = new Diet("diet3_4", 4, Arrays.asList("4a", "4b"));
        Diet diet3_5 = new Diet("diet3_5", 5, Arrays.asList("5a", "5b"));
        Diet diet3_6 = new Diet("diet3_6", 6, Arrays.asList("6a", "6b"));

        return Arrays.asList(
                new Meal("High", Arrays.asList(diet1_1, diet1_2, diet1_3, diet1_4, diet1_5, diet1_6)),
                new Meal("Meduium", Arrays.asList(diet2_1, diet2_2, diet2_3, diet2_4, diet2_5, diet2_6)),
                new Meal("Low", Arrays.asList(diet3_1, diet3_2, diet3_3, diet3_4, diet3_5, diet3_6))
        );
    }

    /*  public static void Excle_sheet(double  [] data)
    {
         String FILE_NAME ="E:\\Ph.D Research\\Ph.D Research\\Practical Part\\FCM_Type2.xlsx";
  
       XSSFWorkbook workbook = new XSSFWorkbook();
       XSSFSheet sheet = workbook.createSheet("Type1");
        
        double[] datatypes = data ;
               int rowNum = 0;
        System.out.println("Creating excel");

        for (double datatype : datatypes) {
            Row row = sheet.createRow(rowNum++);
            int colNum = 0;
               Cell cell = row.createCell(colNum);
               
                    cell.setCellValue((double) datatype);
               
        }


        System.out.println("Done");


    }
*/
    /***************************************************/
    public static double centerOfGravity(double x,double y){
        double cg = 0.5*(x+y);
        return cg;
    }
    /************************************************************************/
    public static double weight(double x,double y){
        double w = Math.max(x,y);
        return w;
    }
    /************************************************************/
    public static double getMinValue(double[] numbers){
        double minValue = numbers[0];
        for(int i=1;i<numbers.length;i++){
            if(numbers[i] < minValue){
                minValue = numbers[i];
            }
        }
        return minValue;
    }
    /************************************************************************/
    public static double [] firingStringth(double  arrAge[], double arrBMI[],double arrHeight[], double arrWeight[], double arrOut[]){
        double fs[]=new double[2];
        double fsUpperAge=Math.min(arrAge[0],arrAge[2]);
        double fsLowerAge=Math.min(arrAge[1],arrAge[3]);
        double fsUpperBMI=Math.min(arrBMI[0],arrBMI[2]);
        double fsLowerBMI=Math.min(arrBMI[1],arrBMI[3]);
        double fsUpperHight=Math.min(arrHeight[0],arrHeight[2]);
        double fsLowerHight=Math.min(arrHeight[1],arrHeight[3]);
        double fsUpperWeight=Math.min(arrWeight[0],arrWeight[2]);
        double fsLowerWeight=Math.min(arrWeight[1],arrWeight[3]);
        double fsUpperOut=Math.min(arrOut[0],arrOut[2]);
        double fsLowerOutt=Math.min(arrOut[1],arrOut[3]);

        double upper[]=new double[]{fsUpperAge,fsUpperBMI,fsUpperHight,fsUpperWeight,fsUpperOut};

        double lower[]=new double[]{fsLowerAge,fsLowerBMI,fsLowerHight,fsLowerWeight,fsLowerOutt};

        double fsUpper=getMinValue(upper);
        double fsLower=getMinValue(lower);

        fs[0]=fsUpper;
        fs[1]=fsLower;
        return fs;
    }
    /************************************************************************/
    public static String  getAgeFuzzySets(double value[]){
        String s="";
        if(value[4]==1)
            s="Young";
        if(value[4]==2)
            s="Mid-age";
        if(value[4]==3)
            s="Old";
        return s;
    }
    /***************************************************/
    public static String  getGender(double [] arr){
        String s="";
        if(arr[1]==1.0)
            s="Female";
        if(arr[1]==0.0)
            s="Male";

        return s;
    }

    /***************************************************/
    public static String  getType(double [] arr){
        String s="";
        if(arr[4]==1.0)
            s="Type 1";
        if(arr[4]==2.0)
            s="Type 2";

        return s;
    }/***************************************************/
    public static String  bMIFuzzySets(double value[]){
        String s="";
        if(value[4]==1)
            s="Under Weight";
        if(value[4]==2)
            s="Normal";
        if(value[4]==3)
            s="Over Weight";
        return s;
    }
    /***************************************************/
    public static String  getManagment(double [] arr){
        String s="";
        if(arr[4]==1.0)
            s="Diet Control";
        if(arr[4]==2.0)
            s="Tablet";
        if(arr[4]==3.0)
            s="Insulin";
        return s;
    }
    /***************************************************/
    public static String  hightFuzzySets(double value []){

        String s="";
        if(value[4]==1)
            s="Short";
        if(value[4]==2)
            s="Normal";
        if(value[4]==3)
            s="Long";
        return s;

    }
    /***************************************************/
    public static String wightFuzzySets(double value []){

        String s="";
        if(value[4]==1)
            s="Slight";
        if(value[4]==2)
            s="Normal";
        if(value[4]==3)
            s="Heavy";
        return s;
    }
    /***************************************************/
    public static String bMRFuzzySets(double value []){

        String s="";
        if(value[4]==1)
            s="Low";
        if(value[4]==2)
            s="Meduium";
        if(value[4]==3)
            s="High";
        return s;
    }
    /***************************************************/

    /***************************************************/
    public static double [] getAge(double x){// this method return array of upper and lower membership and fuzzy set
        double age[]=new double[5];

        if(x<35){
            age[0]=1;//[0] for upper
            age[1]=1;//[1] for lower
            age[2]=0;//[0] for upper
            age[3]=0;//[1] for lower
            age[4]=1;//fuzzy set young
        }
        if(x>=35 && x<38){
            age[0]=1;// for upper Young
            age[1]=(43-x)/(43-40);// for lower Young
            double centerGravity_Young=0.5*(age[0]+age[1]);
            age[2]=(x-35)/(38-35);//mid-age upper
            age[3]=0;//mid-age lower
            double centerGravity_Mid_age=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Young,centerGravity_Mid_age);

            if (w==centerGravity_Young)
                age[4]=1;//fuzzy set Young
            else
                age[4]=2;//fuzzy set mid-age

        }
        if(x>=38 && x<43){
            age[0]=(47-x)/(47-45);// for upper Young
            age[1]=(43-x)/(43-40);// for lower Young
            double centerGravity_Young=0.5*(age[0]+age[1]);
            age[2]=(x-35)/(38-35);//mid-age upper
            age[3]=(x-38)/(40-38);//mid-age lower
            double centerGravity_Mid_age=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Young,centerGravity_Mid_age);

            if (w==centerGravity_Young)
                age[4]=1;//fuzzy set Young
            else
                age[4]=2;//fuzzy set mid-age

        }
        if(x>=43 && x<45){
            age[0]=(47-x)/(47-45);// for upper Young
            age[1]=0;// for lower Young
            double centerGravity_Young=0.5*(age[0]+age[1]);
            age[2]=(x-35)/(40-35);//mid-age upper
            age[3]=(x-38)/(40-38);//mid-age lower
            double centerGravity_Mid_age=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Young,centerGravity_Mid_age);

            if (w==centerGravity_Young)
                age[4]=1;//fuzzy set Young
            else
                age[4]=2;//fuzzy set mid-age

        }if(x>=45 && x<47){
            age[0]=(47-x)/(47-45);// for upper young
            age[1]=0;// for lower young
            double centerGravity_Young=0.5*(age[0]+age[1]);
            age[2]=1;//mid-age upper
            age[3]=(x-38)/(43-38);//mid-age lower
            double centerGravity_Mid_age=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Young,centerGravity_Mid_age);

            if (w==centerGravity_Young)
                age[4]=1;//fuzzy set Young
            else
                age[4]=2;//fuzzy set mid-age
        }
        if(x>=47 && x<50){
            age[0]=1;//[0] for upper Med
            age[1]=(57-x)/(57-50);//[1] for lower Med
            double centerGravity_Med_age=0.5*(age[0]+age[1]);
            age[2]=(x-47)/(50-47);// upper old
            age[3]=0;// lower old
            double centerGravity_old=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Med_age,centerGravity_old);

            if (w==centerGravity_Med_age)
                age[4]=2;//fuzzy set Med-Age
            else
                age[4]=3;//fuzzy set old
        }
        if(x>=50 && x<53){
            age[0]=(63-x)/(63-57);//[0] for upper med age
            age[1]=(57-x)/(57-55);//[1] for lower med age
            double centerGravity_Med_age=0.5*(age[0]+age[1]);
            age[2]=(x-47)/(50-47);// upper old
            age[3]=0;// lower old
            double centerGravity_old=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Med_age,centerGravity_old);

            if (w==centerGravity_Med_age)
                age[4]=2;//fuzzy set Med-Age
            else
                age[4]=3;//fuzzy set old

        }
        if(x>=53 && x<57){
            age[0]=(57-x)/(57-55);//[0] for upper med age
            age[1]=(63-x)/(63-57);//[1] for lower med age
            double centerGravity_Med_age=0.5*(age[0]+age[1]);
            age[2]=(x-47)/(53-47);// upper old
            age[3]=(x-53)/(55-53);// lower old
            double centerGravity_old=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Med_age,centerGravity_old);

            if (w==centerGravity_Med_age)
                age[4]=2;//fuzzy set Med-Age
            else
                age[4]=3;//fuzzy set old

        }
        if(x>=57 && x<63){
            age[0]=(63-x)/(63-60);// upper Med age
            age[1]=0;// lower med age
            age[2]=1;// for upper old
            age[3]=(x-53)/(55-53);// for lower old

            double centerGravity_Med_age=0.5*(age[0]+age[1]);
            double centerGravity_old=0.5*(age[2]+age[3]);
            double w=weight(centerGravity_Med_age,centerGravity_old);

            if (w==centerGravity_Med_age)
                age[4]=2;//fuzzy set Med-Age
            else
                age[4]=3;//fuzzy set old
        }
        if(x>=63){
            age[0]=1;//[0] for upper
            age[1]=1;//[1] for lower
            age[2]=1;//[0] for upper
            age[3]=1;//[1] for lower
            age[4]=3;//fuzzy set old
        }
        return age;
    }

    /***************************************************/
    public static double [] getBMI(double x){
        double bmi[]=new double[5];

        if(x<21){
            bmi[0]=1;//[0] for upper under weight
            bmi[1]=1;//[1] for lower under weight
            bmi[4]=1;//fuzzy set under weight
        }
        if(x>=21 && x<23){
            bmi[0]=1;//[0] for upper under weight
            bmi[1]=(25-x)/(25-23);//[1] for lower under weight
            bmi[2]=(x-21)/(23-21);//med upper normal
            bmi[3]=0;//med lower normal
            double centerGravity_Under_Weight=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Normal=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Under_Weight,centerGravity_Normal);

            if (w==centerGravity_Under_Weight)
                bmi[4]=1;//fuzzy set under weight
            else
                bmi[4]=2;//fuzzy set normal
        }
        if(x>=23 && x<25){
            bmi[0]=(27-x)/(27-25);// for upper under weight
            bmi[1]=(25-x)/(25-23);// for lower under weight
            bmi[2]=(x-21)/(23-21);//med upper normal
            bmi[3]=(x-23)/(25-23);//med lower normal
            double centerGravity_Under_Weight=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Normal=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Under_Weight,centerGravity_Normal);

            if (w==centerGravity_Under_Weight)
                bmi[4]=1;//fuzzy set under weight
            else
                bmi[4]=2;//fuzzy set normal

        }
        if(x>=25 && x<27){
            bmi[0]=(27-x)/(27-25);//[0] for upper under wight
            bmi[1]=0;//[1] for lower under weight
            bmi[2]=1;//med upper normal
            bmi[3]=(x-23)/(25-23);//med lower normal
            double centerGravity_Under_Weight=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Normal=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Under_Weight,centerGravity_Normal);

            if (w==centerGravity_Under_Weight)
                bmi[4]=1;//fuzzy set Under_Weight
            else
                bmi[4]=2;//fuzzy set Normal

        }
        if(x>=27 && x<30){
            bmi[0]=1;//[0] for upper Normal
            bmi[1]=(35-x)/(35-30);//[1] for lower Normal
            bmi[2]=(x-27)/(30-27);//med upper Over weight
            bmi[3]=0;//med lower Over Weight
            double centerGravity_Normal=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Over_Weight=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Normal,centerGravity_Over_Weight);

            if (w==centerGravity_Normal)
                bmi[4]=2;//fuzzy set Normal
            else
                bmi[4]=3;//fuzzy set Over weight

        }if(x>=30 && x<33){
            bmi[0]=(36-x)/(36-33);//[0] for upper Normal
            bmi[1]=(33-x)/(33-30);//[1] for lower Normal
            bmi[2]=(x-27)/(30-27);//med upper Over weight
            bmi[3]=(x-30)/(33-30);//med lower Over Weight
            double centerGravity_Normal=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Over_Weight=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Normal,centerGravity_Over_Weight);

            if (w==centerGravity_Normal)
                bmi[4]=2;//fuzzy set Normal
            else
                bmi[4]=3;//fuzzy set Over weight

        }
        if(x>=33 && x<36){
            bmi[0]=(36-x)/(36-33);//[0] for upper Normal
            bmi[1]=0;//[1] for lower Normal
            bmi[2]=1;//med upper Over weight
            bmi[3]=(x-30)/(33-30);//med lower Over Weight
            double centerGravity_Normal=0.5*(bmi[0]+bmi[1]);
            double centerGravity_Over_Weight=0.5*(bmi[2]+bmi[3]);

            double w=weight(centerGravity_Normal,centerGravity_Over_Weight);

            if (w==centerGravity_Normal)
                bmi[4]=2;//fuzzy set Normal
            else
                bmi[4]=3;//fuzzy set Over weight
        }
        if(x>=36){
            bmi[0]=1;//[0] for upper Over weight
            bmi[1]=1;//[1] for lower Over weight
            bmi[4]=3;//fuzzy set Over weight
        }

        return bmi;
    }

    /***************************************************/
    public static double [] getHight(double x){
        double hight[]=new double[7];

        if(x<1.5){
            hight[0]=1;//[0] for upper
            hight[1]=1;//[1] for lower
            hight[4]=1;//fuzzy set Short
        }
        if(x>=1.5 && x<1.6){
            hight[0]=1;//[0] for upper Short
            hight[1]=(1.6-x)/(1.6-1.55);//[1] for lower
            hight[2]=(x-1.5)/(1.6-1.5);//med upper Normal
            hight[3]=0;//med lower
            double centerGravity_Short=0.5*(hight[0]+hight[1]);
            double centerGravity_Normal=0.5*(hight[2]+hight[3]);
            double w=weight(centerGravity_Short,centerGravity_Normal);
            if (w==centerGravity_Short)
                hight[4]=1;//fuzzy set Short
            else
                hight[4]=2; //fuzzy set Normal
        }
        if(x>=1.6 && x<1.65){
            hight[0]=(1.7-x)/(1.7-1.65);//[0] for upper Short
            hight[1]=0;//[1] for lower
            hight[2]=(x-1.5)/(1.6-1.5);//med upper Normal
            hight[3]=(x-1.6)/(1.65-1.6);//med lower
            hight[4]=(x-1.6)/(1.65-1.6);//med upper Long
            hight[5]=0;//med lower
            double centerGravity_Short=0.5*(hight[0]+hight[1]);
            double centerGravity_Normal=0.5*(hight[2]+hight[3]);
            double centerGravity_Long=0.5*(hight[4]+hight[5]);
            double w=weight(centerGravity_Short,centerGravity_Normal);

            if (w==centerGravity_Short)
            {        double w2=weight(centerGravity_Short,centerGravity_Long);
                if (w2==centerGravity_Short)
                    hight[4]=1;//fuzzy set Short
                else
                    hight[4]=3; //fuzzy set Long
            }
            else
            {
                double w3=weight(centerGravity_Normal,centerGravity_Long);
                if (w3==centerGravity_Normal)
                    hight[4]=2;//fuzzy set Short
                else
                    hight[4]=3; //fuzzy set Long
            }
        }
        if(x>=1.65 && x<1.7){
            hight[0]=(1.7-x)/(1.7-1.65);// for upper Short
            hight[1]=0;// for lower low
            hight[2]=(1.77-x)/(1.77-1.7);//med upper Normal
            hight[3]=(1.7-x)/(1.7-1.65);//med lower
            hight[4]=(x-1.6)/(1.65-1.6);//med upper Long
            hight[5]=0;//med lower
            double centerGravity_Short=0.5*(hight[0]+hight[1]);
            double centerGravity_Normal=0.5*(hight[2]+hight[3]);
            double centerGravity_Long=0.5*(hight[4]+hight[5]);
            double w=weight(centerGravity_Short,centerGravity_Normal);

            if (w==centerGravity_Short)
            {        double w2=weight(centerGravity_Short,centerGravity_Long);
                if (w2==centerGravity_Short)
                    hight[4]=1;//fuzzy set Short
                else
                    hight[4]=2; //fuzzy set Long
            }
            else
            {
                double w3=weight(centerGravity_Normal,centerGravity_Long);
                if (w3==centerGravity_Normal)
                    hight[4]=2;//fuzzy set Short
                else
                    hight[4]=3; //fuzzy set Long
            }
        }
        if(x>=1.7 && x<1.77){
            hight[0]=(1.77-x)/(1.77-1.75);//[0] for upper Normal
            hight[1]=0;//[1] for lower
            hight[2]=(x-1.6)/(1.7-1.6);//[0] for upper Long
            hight[3]=(x-1.7)/(1.75-1.7);//[1] for lower
            double centerGravity_Normal=0.5*(hight[0]+hight[1]);
            double centerGravity_Long=0.5*(hight[2]+hight[3]);
            double w=weight(centerGravity_Normal,centerGravity_Long);
            if (w==centerGravity_Normal)
                hight[4]=2;//fuzzy set Short
            else
                hight[4]=3; //fuzzy set Normal

        }

        if(x>=1.77){
            hight[0]=1;//[0] for upper
            hight[1]=1;//[1] for lower
            hight[4]=3;//fuzzy set Long
        }
        return hight;
    }
    /***************************************************/
    public static double [] getWight(double x){
        double wight[]=new double[5];

        if(x<57){
            wight[0]=1;//[0] for upper
            wight[1]=1;//[1] for lower
            wight[4]=1;//fuzzy set Slight
        }
        if(x>=57 && x<63){
            wight[0]=1;//[0] for upper Slight
            wight[1]=(70-x)/(70-60);//[1] for lower Slight
            wight[2]=(x-57)/(60-57);//med upper Normal
            wight[3]=0;//med lower Normal
            double centerGravity_Slight=0.5*(wight[0]+wight[1]);
            double centerGravity_Normal=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Slight,centerGravity_Normal);

            if (w==centerGravity_Slight)
                wight[4]=1;//fuzzy set low
            else
                wight[4]=2;//fuzzy set med
        }
        if(x>=63 && x<70){
            wight[0]=(x-57)/(63-57);// for upper Slight
            wight[1]=(x-63)/(70-63);// for lower Slight
            wight[2]=(x-64.45)/(72.6-64.45);//med upper Normal
            wight[3]=0;//med lower Normal
            double centerGravity_Slight=0.5*(wight[0]+wight[1]);
            double centerGravity_Normal=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Slight,centerGravity_Normal);

            if (w==centerGravity_Slight)
                wight[4]=1;//fuzzy set Slight
            else
                wight[4]=2;//fuzzy set Normal


        }
        if(x>=70 && x<76){
            wight[0]=(76-x)/(76-70);//[0] for upper Slight
            wight[1]=0;//[1] for lower Slight
            wight[2]=1;//med upper Normal
            wight[3]=(x-63)/(70-63);//med lower Normal
            double centerGravity_Slight=0.5*(wight[0]+wight[1]);
            double centerGravity_Normal=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Slight,centerGravity_Normal);

            if (w==centerGravity_Slight)
                wight[4]=1;//fuzzy set Slight
            else
                wight[4]=2;//fuzzy set Normal

        }
        if(x>=76 && x<85){
            wight[0]=(99-x)/(99-90);//[0] for upper Normal
            wight[1]=(90-x)/(90-85);//[1] for lower Normal
            wight[2]=(x-76)/(80-76);//med upper Heavy
            wight[3]=0;//med lower Heavy
            double centerGravity_Normal=0.5*(wight[0]+wight[1]);
            double centerGravity_Heavy=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Normal,centerGravity_Heavy);

            if (w==centerGravity_Normal)
                wight[4]=2;//fuzzy set Normal
            else
                wight[4]=3;//fuzzy set Heavy
        }
        if(x>=85 && x<90){
            wight[0]=(99-x)/(99-90);// for upper Normal
            wight[1]=(90-x)/(90-85);// for lower Normal
            wight[2]=(x-105.2)/(121.5-105.2);//upper Heavy
            wight[3]=0;// lower Heavy
            double centerGravity_Normal=0.5*(wight[0]+wight[1]);
            double centerGravity_Heavy=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Normal,centerGravity_Heavy);

            if (w==centerGravity_Normal)
                wight[4]=2;//fuzzy set Normal
            else
                wight[4]=3;//fuzzy set Heavy

        }
        if(x>=90 && x<99){
            wight[0]=(99-x)/(99-90);//[0] for upper
            wight[1]=0;//[1] for lower
            wight[2]=1;//upper Heavy
            wight[3]=(x-85)/(90-85);// lower Heavy
            double centerGravity_Normal=0.5*(wight[0]+wight[1]);
            double centerGravity_Heavy=0.5*(wight[2]+wight[3]);

            double w=weight(centerGravity_Normal,centerGravity_Heavy);

            if (w==centerGravity_Normal)
                wight[4]=2;//fuzzy set Normal
            else
                wight[4]=3;//fuzzy set Heavy

        }
        if(x>99){
            wight[0]=1;//[0] for upper
            wight[1]=1;//[1] for lower
            wight[4]=3;//fuzzy set Heavy
        }
        return wight;
    }
    /***************************************************/
    public static double [] getBMR(double x){
        double bMR[]=new double[5];

        if(x<350){
            bMR[0]=1;//[0] for upper Low
            bMR[1]=1;//[1] for lower Low
            bMR[4]=1;//fuzzy set Low
        }
        if(x>=350 && x<400){
            bMR[0]=1;//[0] for upper Low
            bMR[1]=(450-x)/(450-400);//[1] for lower Low
            bMR[2]=(x-350)/(400-350);//med upper
            bMR[3]=0;//med lower
            double centerGravity_low=0.5*(bMR[0]+bMR[1]);
            double centerGravity_med=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_low,centerGravity_med);

            if (w==centerGravity_low)
                bMR[4]=1;//fuzzy set low
            else
                bMR[4]=2;//fuzzy set med

        }
        if(x>=400 && x<450){
            bMR[0]=1;// for upper low
            bMR[1]=(450-x)/(450-400);// for lower low
            bMR[2]=1;//med upper
            bMR[3]=(x-400)/(450-400);//med lower
            double centerGravity_low=0.5*(bMR[0]+bMR[1]);
            double centerGravity_med=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_low,centerGravity_med);

            if (w==centerGravity_low)
                bMR[4]=1;//fuzzy set low
            else
                bMR[4]=2;//fuzzy set med

        }
        if(x>=450 && x<500){
            bMR[0]=(500-x)/(500-450);//[0] for upper Low
            bMR[1]=0;//[1] for lower Low
            bMR[2]=1;//med upper
            bMR[3]=(x-400)/(450-400);//med lower
            double centerGravity_low=0.5*(bMR[0]+bMR[1]);
            double centerGravity_med=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_low,centerGravity_med);

            if (w==centerGravity_low)
                bMR[4]=1;//fuzzy set low
            else
                bMR[4]=2;//fuzzy set med

        }
        if(x>=500 && x<600){
            bMR[0]=1;//[0] for upper Med
            bMR[1]=(700-x)/(700-600);//[1] for lower Med
            bMR[2]=(x-500)/(600-500);//high upper
            bMR[3]=0;// high lower
            double centerGravity_med=0.5*(bMR[0]+bMR[1]);
            double centerGravity_high=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_med,centerGravity_high);

            if (w==centerGravity_med)
                bMR[4]=2;//fuzzy set low
            else
                bMR[4]=3;//fuzzy set med
        }
        if(x>=600 && x<700){
            bMR[0]=(780-x)/(780-700);// for upper med
            bMR[1]=(700-x)/(700-600);;// for lower med
            bMR[2]=(x-500)/(600-500);//high upper
            bMR[3]=(x-600)/(700-600);// high lower
            double centerGravity_med=0.5*(bMR[0]+bMR[1]);
            double centerGravity_high=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_med,centerGravity_high);

            if (w==centerGravity_med)
                bMR[4]=2;//fuzzy set low
            else
                bMR[4]=3;//fuzzy set med

        }
        if(x>=700 && x<780){
            bMR[0]=(780-x)/(780-700);//[0] for upper Med
            bMR[1]=0;//[1] for lower Med
            bMR[2]=1;//high upper
            bMR[3]=(x-600)/(700-600);// high lower
            double centerGravity_med=0.5*(bMR[0]+bMR[1]);
            double centerGravity_high=0.5*(bMR[2]+bMR[3]);

            double w=weight(centerGravity_med,centerGravity_high);

            if (w==centerGravity_med)
                bMR[4]=2;//fuzzy set low
            else
                bMR[4]=3;//fuzzy set med
        }
        if(x>=780){
            bMR[0]=1;//[0] for upper
            bMR[1]=1;//[1] for lower
            bMR[4]=3;//fuzzy set High
        }
        return bMR;
    }
}