package game_logic;

import java.util.Scanner;

public class LettoreInput 
{
	private static Scanner sc = new Scanner(System.in);
	
	public LettoreInput() 
	{
		
	}
	
	public String leggiString() 
	{
		return sc.nextLine();
	}
	
	public int leggiInt() 
	{
		return sc.nextInt();
	}
}
