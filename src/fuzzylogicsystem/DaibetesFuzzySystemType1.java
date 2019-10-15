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
public class DaibetesFuzzySystemType1 {

/*This Version with Type1 Fuzzy Logic System , Fuzzy Set by FCM */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

      System.out.println("This Applcation Using Type 1 Fuzzy Logic To Learning From Data IF- Then Rules \n" );

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
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(
                    new FileWriter(
                            "./files/out/fuzzyouttype1.csv"
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
                    double doubleDataArr[] = convertToDouble(data);
                    //int agee=Integer.parseInt(doubleDataArr[0]);
                    double age[] = getAge(doubleDataArr[0]);
                    double bMI[] = getBMI(doubleDataArr[4]);
                    // double management []=getManagment(doubleDataArr[5]);
                    double hight[] = getHight(doubleDataArr[6]);
                    double wight[] = getWight(doubleDataArr[7]);
                    double bMR[] = getBMR(doubleDataArr[8]);
                    String gender = getGender(doubleDataArr);
                    String type = getType(doubleDataArr);
                    String mang = getManagment(doubleDataArr);
                    String ageFuzzySets = getAgeFuzzySets(age);
                    String bMIFuzzySets = bMIFuzzySets(bMI);
                    String hightFuzzySets = hightFuzzySets(hight);
                    String wightFuzzySets = wightFuzzySets(wight);
                    String bMRFuzzySets = bMRFuzzySets(bMR);
             /* String dataa[]=new String[7];
              
			dataa[0]=ageFuzzySets;
                        dataa[1]=gender;
                        dataa[2]=type;
                        dataa[3]=bMIFuzzySets;
                        dataa[4]=mang;
                        dataa[5]=hightFuzzySets;
                        dataa[6]=wightFuzzySets;
                        dataa[7]=bMRFuzzySets;*/
                    ruleBase(ageFuzzySets, gender, type, bMIFuzzySets, mang, hightFuzzySets, wightFuzzySets, bMRFuzzySets);

                    /**
                     * dsusanibar
                     */
                    writer.append("If Age is  " + ageFuzzySets + " and Gender is " + gender + " and Type of Diabetes is " + type + " and BMI is " + bMIFuzzySets + " and Managment is " + mang + " and Hight is " + hightFuzzySets + " and Wight is " + wightFuzzySets + " Then Outcome BMR is " + bMRFuzzySets);
                    writer.append('\n');
                    /**
                     * dsusanibar
                     */
                    System.out.println("If Age is  " + ageFuzzySets + " and Gender is " + gender + " and Type of Diabetes is " + type + " and BMI is " + bMIFuzzySets + " and Managment is " + mang + " and Hight is " + hightFuzzySets + " and Wight is " + wightFuzzySets + " Then Outcome BMR is " + bMRFuzzySets);

                } catch (Exception e){
                    //only for purpose of test
                    //e.printStackTrace();
                }
            }
            /**
             * dsusanibar
             */
            writer.flush();
            /**
             * dsusanibar
             */
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            /**
             * dsusanibar
             */
            if(writer != null){
                try {
                    writer.close();
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

    }/***************************************************/
    public static void ruleBase(String age,String gend,String type,String bmi,String mang,String high,String wight, String out){
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

    /************************************************************************/
    public static double centerOfGravity(double x,double y){
    double cg = 0.5*(x+y);
    return cg;
    }
    /************************************************************************/
    public static double weight(double x,double y){
    double w = Math.max(x,y);
    return w;
    }
    /************************************************************************/
      public static double firingStringth(double  x, double y){
        double fs=Math.min(x,y);
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

        if(x<37){
        age[0]=1;// MF 
        age[4]=1;//fuzzy set young
        }
        if(x>=37 && x<45){
        age[0]=(45-x)/(45-40);// for  Young
        age[1]=(x-37)/(45-37);//mid-age 
        double w=weight(age[0],age[1]);
        
             if (w==age[0])
                  age[4]=1;//fuzzy set Young
                 else 
                    age[4]=2;//fuzzy set mid-age

        }
        if(x>=45 && x<50){
        age[0]=1;// MF 
        age[4]=2;//fuzzy set Med_Age
        }
        if(x>=50 && x<58){
        age[0]=(58-x)/(58-55);// for  Med age
        age[1]=(x-50)/(55-50);//for old 
        double w=weight(age[0],age[1]);
        
             if (w==age[0])
                  age[4]=2;//fuzzy set Young
                 else 
                    age[4]=3;//fuzzy set mid-age


        }
        if(x>=58){
        age[0]=1;//[0] for old
        age[4]=3;//fuzzy set old
        }
        return age;
    }
    
         /***************************************************/
    public static double [] getBMI(double x){
        double bmi[]=new double[5];

        if(x<23){
        bmi[0]=1;//[0] for  under weight 
        bmi[4]=1;//fuzzy set under weight
        }
        if(x>=23 && x<26){
        bmi[0]=(26-x)/(26-25);//[0] for  under weight
        bmi[1]=(x-23)/(25-23);//  normal
        
        double w=weight(bmi[0],bmi[1]);
        
             if (w==bmi[0])
                  bmi[4]=1;//fuzzy set under weight
                 else 
                    bmi[4]=2;//fuzzy set normal
        }
        if(x>=26 && x<28){
        bmi[0]=1;//[0] for  Normal
        bmi[4]=2;//fuzzy set Normal
        }
        if(x>=28 && x<35){
        bmi[0]=(35-x)/(35-30);//[0] for Normal
        bmi[1]=(x-28)/(30-28);// Over weight
        double w=weight(bmi[0],bmi[1]);
        
             if (w==bmi[0])
                  bmi[4]=2;//fuzzy set Normal
                 else 
                    bmi[4]=3;//fuzzy set Over weight

        }
        if(x>=35){
        bmi[0]=1;//[0] for Over weight
        bmi[4]=3;//fuzzy set Over weight
        }
       
        return bmi;
    }

    /***************************************************/
    public static double [] getHight(double x){
        double hight[]=new double[5];

        if(x<1.55){
        hight[0]=1;//[0] for upper
        hight[4]=1;//fuzzy set Short
        }
        if(x>=1.55 && x<1.65){
        hight[0]=(1.65-x)/(1.65-1.6);//[1] for Short
        hight[1]=(x-1.55)/(1.6-1.55);// for Normal

        double w=weight( hight[0], hight[1]);
        
             if (w== hight[0])
                  hight[4]=1;//fuzzy set Short
                 else 
                    hight[4]=2;//fuzzy set Normal
        }
        if(x>=1.65 && x<1.75){
        hight[0]=(1.75-x)/(1.75-1.7);// Normal
        hight[1]=(x-1.65)/(1.7-1.65);// for Long

        double w=weight( hight[0], hight[1]);
        
             if (w== hight[0])
                  hight[4]=2;//fuzzy set Normal
                 else 
                    hight[4]=3;//fuzzy set Long
        }
        if(x>=1.75){
        hight[0]=1;//[0] for Long
        hight[4]=3;//fuzzy set Long
        }
        return hight;
    }
    /***************************************************/
    public static double [] getWight(double x){
                double wight[]=new double[5];

        if(x<60){
        wight[0]=1;//[0] for Slight
        wight[4]=1;//fuzzy set Slight
        }
        if(x>=60 && x<72){
        wight[0]=(72-x)/(72-70);// for Slight
        wight[1]=(x-60)/(70-60);//med Normal
            double w=weight(wight[0],wight[1]);
        
             if (w==wight[0])
                  wight[4]=1;//fuzzy set Slight
                 else 
                    wight[4]=2;//fuzzy set Normal

        }
        if(x>=72 && x<80){
        wight[0]=1;// for upper Normal
        wight[4]=2;//fuzzy set Normal
        }
        if(x>=80 && x<95){
        wight[0]=(95-x)/(95-90);// Normal
        wight[1]=(x-80)/(90-80);// Heavy
        
              double w=weight(wight[0],wight[1]);
        
             if (w==wight[0])
                  wight[4]=2;//fuzzy set Normal
                 else 
                    wight[4]=3;//fuzzy set Heavy

        }
        if(x>=95){
        wight[0]=1;//[0] for Heavy
        wight[4]=3;//fuzzy set Heavy
        }
        return wight;
    }
    /***************************************************/
    public static double [] getBMR(double x){
                double bMR[]=new double[5];

        if(x<350){
        bMR[0]=1;//[0] for upper 
        bMR[4]=1;//fuzzy set Low
        }
        if(x>=350 && x<470){
        bMR[0]=(470-x)/(470-400);//[1] for Low
        bMR[1]=(x-350)/(400-350);// Med
            double w=weight(bMR[0],bMR[1]);
        
             if (w==bMR[0])
                  bMR[4]=1;//fuzzy set low
                 else 
                    bMR[4]=2;//fuzzy set med

        }
        if(x>=470 && x<530){
        bMR[0]=1;// for  low
              bMR[4]=2;//fuzzy set med

        }
        if(x>=530 && x<700){
        bMR[0]=(700-x)/(700-600);//[0] for Med
        bMR[1]=(x-530)/(600-530);//[1] for High
         double w=weight(bMR[0],bMR[1]);
        
             if (w==bMR[0])
                  bMR[4]=2;//fuzzy set Med
                 else 
                    bMR[4]=3;//fuzzy set High
        }
        
        if(x>=700){
        bMR[0]=1;//[0] for High
        bMR[4]=3;//fuzzy set High
        }
        return bMR;
    }


}



