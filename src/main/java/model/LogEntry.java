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

    public String getName() {
        return name;
    }

    public ArrayList<Character> getLR() {
        return LR;
    }

    public ArrayList<Character> getKR() {
        return KR;
    }

    public ArrayList<Character> getCW() {
        return CW;
    }

    public ArrayList<Character> getExam() {
        return exam;
    }

	public int getTotal() {
		int total = 0;
		for(Character cur : LR)
			total += Character.getNumericValue(cur.toString().charAt(0));

		for(Character cur : KR)
			total += Character.getNumericValue(cur.toString().charAt(0));

		for(Character cur : CW)
			total += Character.getNumericValue(cur.toString().charAt(0));

		for(Character cur : exam)
			total += Character.getNumericValue(cur.toString().charAt(0));

		return total;
	}
}
