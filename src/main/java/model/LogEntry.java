package model;

import java.io.*;
import java.util.*;

public class LogEntry {
    private String name;
    private ArrayList<Character> LR;
    private ArrayList<Character> KR;
    private ArrayList<Character> CW;
    private ArrayList<Character> exam;
	//private ArrayList<Stirng> total;


    public LogEntry(String name, ArrayList<Character> LR, ArrayList<Character> KR, ArrayList<Character> CW,
		 ArrayList<Character> exam) {
        this.name = name;
        this.LR = LR;
        this.KR = KR;
        this.CW = CW;
        this.exam = exam;
    }

    public LogEntry(String name){
        this.name = name;
        LR = new ArrayList<>();
        KR = new ArrayList<>();
        CW = new ArrayList<>();
        exam = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Character> getLR() {
        return LR;
    }
    public void addLR(Character lr) { LR.add(lr); }

    public ArrayList<Character> getKR() {
        return KR;
    }
    public void addKR(Character kr) { KR.add(kr); }

    public ArrayList<Character> getCW() {
        return CW;
    }
    public void addCW(Character cw) { CW.add(cw); }

    public ArrayList<Character> getExam() {
        return exam;
    }
    public void addExam(Character exam) { this.exam.add(exam); }

	public int getTotal() {
		int total = 0;
		for(Character cur : LR) {
            total += Character.getNumericValue(cur.toString().charAt(0));
        }

		for(Character cur : KR) {
            total += Character.getNumericValue(cur.toString().charAt(0));
        }

		for(Character cur : CW) {
            total += Character.getNumericValue(cur.toString().charAt(0));
        }

		for(Character cur : exam) {
            total += Character.getNumericValue(cur.toString().charAt(0));
        }

		return total;
	}

	public void filter(String subject){
        if (subject.equals("LR")){
            KR = new ArrayList<>();
            CW = new ArrayList<>();
            exam = new ArrayList<>();
        }
        else if (subject.equals("KR")){
            LR = new ArrayList<>();
            CW = new ArrayList<>();
            exam = new ArrayList<>();
        }
        else if (subject.equals("CW")){
            LR = new ArrayList<>();
            KR = new ArrayList<>();
            exam = new ArrayList<>();
        }
        else if (subject.equals("Exam")){
            LR = new ArrayList<>();
            KR = new ArrayList<>();
            CW = new ArrayList<>();
        }
    }
}
