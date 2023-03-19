package moka;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import j_parser.ClassFile;

public class StringParser{
    public static void main(String[] args) throws IOException{
        final String PATH = args[0];   
        final File file;
        
        file = new File(PATH);
        ClassFile myClassFile = new ClassFile(file);

        System.out.println("Magic Number:" + Integer.toHexString(myClassFile.getMagicNumber())); 
        System.out.println("Minor Version:" + myClassFile.getMinorVersion());
        System.out.println("Major Version:" + myClassFile.getMajorVersion());
        System.out.println("Number of Constants:" + myClassFile.getConstantCount());
        System.out.println("Strings:");

        ArrayList<String> strings = myClassFile.getStrings();
        for (String s : strings){
            System.out.println(s);
        }
        
        ArrayList<String> accessFlags = new ArrayList<>();
        accessFlags = myClassFile.getAccessModifiers();
        System.out.println("");
        System.out.println("Access and Modifier flags:");
        for (String f : accessFlags){
            System.out.println(f);
        }
       
        System.out.println("");
        System.out.println("Class internal name:");
        String className = myClassFile.getThisClassName();
        System.out.println(className);
    } 
}
