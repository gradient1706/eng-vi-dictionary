/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commandline_ver3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ngao
 */
public class DictionaryManagement extends Dictionary{
   
    protected int wordNum;
    //this methods enter the number, the words & the meanings from keyboard
    public void insertFromCommandline()
    {
        System.out.println("Enter number of words:");
        Scanner sc = new Scanner(System.in);
        wordNum = sc.nextInt();

        for(int i = 0; i < wordNum; i++)
        {
            Scanner enter = new Scanner(System.in);
            System.out.println("Enter word:");
            String engWord = enter.nextLine();

            Scanner enter1 = new Scanner(System.in);
            System.out.println("Enter meaning:");
            String vnWord = enter1.nextLine();

            //these codes create an obj and add the obj to array "Word"
            Word w = new Word();
            w.setWord_target(engWord);
            w.setWord_explain(vnWord);
            listOfWord.add(w);
        }
    }
    
    
    public void insertFromFile()
    {
        try {
             File f = new File("dictionaries.txt");
             if(!f.exists())
             {
                 System.out.println("Loi mo file");
                 return;//f.createNewFile();
             }
             Scanner input = new Scanner(new FileInputStream(f));
             while(input.hasNextLine())
             {
                 String line = input.nextLine();
                 if(line.trim()!=""){
                     String items[] = line.split("\t");
                     String en = items[0].toString();
                     String vi = items[1].toString();
                     Word w = new Word(en,vi);
                     listOfWord.add(w);
                 }
                     //wordNum++;
                 //listOfWord.add(new Word(input.next(), input.next()));
                     
                 
             }
             //System.out.println(listOfWord.size());
             //input.close();
         } catch (Exception e) {
             e.printStackTrace();
         }
    }
    
    
    public String dictionaryLookup()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập từ muốn tra");
        String en = scanner.nextLine();
        for(Word w : listOfWord)
        {
            if(w.getWord_target().equals(en))
            {
                System.out.println(w.getWord_explain());
                return w.getWord_explain();
            }
        }
        System.out.println("Can not find word");
        return "Can not find word";
    }
    
    
    
    
    public void SuaTu()
    {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String word_target = scanner1.nextLine();
        String word_explain = scanner2.nextLine();
        for (int i = 0; i < listOfWord.size(); i++) {
            if(listOfWord.get(i).getWord_target().equals(word_target))
            {
                listOfWord.get(i).setWord_explain(word_explain);
            }
        }
        
    }
    public void dictionaryExportToFile(String str)
    {
        try
        {
            File f = new File(str);
            FileWriter fw = new FileWriter(f);
            for(Word w: listOfWord)
            {
                fw.write(w.getWord_target() + " " + w.getWord_explain() +"\n");
                
            }
            fw.close();
        }catch(IOException ex)
        {
            System.out.println("Loi ghi file" + ex);
        }
    }
    
    public void addWord()
    {
        Scanner scanner1 = new Scanner(System.in);
        Scanner scanner2 = new Scanner(System.in);
        String word_target = scanner1.nextLine();
        String word_explain = scanner2.nextLine();
        listOfWord.add(new Word(word_target, word_explain));
    }
    
    public void delWord()
    {
        Scanner scanner = new Scanner(System.in);
        String word_target = scanner.nextLine();
        for(int i=0;i<listOfWord.size();i++)
        {
        if(listOfWord.get(i).getWord_target().equals(word_target)) listOfWord.remove(listOfWord.get(i));
            
        }
    }
    
}