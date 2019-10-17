package com.example.jaywayweatherapp.Models;

public class Sys{
	private String pod;

	public void setPod(String pod){
		this.pod = pod;
	}

	public String getPod(){
		return pod;
	}

	@Override
 	public String toString(){
		return 
			"Sys{" + 
			"pod = '" + pod + '\'' + 
			"}";
		}
}
