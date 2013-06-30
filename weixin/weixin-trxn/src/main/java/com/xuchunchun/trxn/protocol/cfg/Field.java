package com.xuchunchun.trxn.protocol.cfg;

import java.util.ArrayList;
import java.util.HashMap;

public class Field {

	private String name;
	
	private int seq;
		
	private int length;
	
	private String location;
	
	private String type;
	
	public static enum TYPE{
		fix,dyn
	}
	
	private int headLength;
	
	private String headFillType;
	
	private char headFillChar;
	
	private String contentFillType;
	
	private char contentFillChar;
	
	
	private ArrayList<Field> fields = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public ArrayList<Field> getFields() {
		return fields;
	}

	public void setFields(ArrayList<Field> fields) {
		this.fields = fields;
	}
	
	public void addField(Field _field){
		if(fields == null)fields = new ArrayList<Field>();

		this.fields.add(_field);
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHeadLength() {
		return headLength;
	}

	public void setHeadLength(int headLength) {
		this.headLength = headLength;
	}

	public String getHeadFillType() {
		return headFillType;
	}

	public void setHeadFillType(String headFillType) {
		this.headFillType = headFillType;
	}

	public char getHeadFillChar() {
		return headFillChar;
	}

	public void setHeadFillChar(char headFillChar) {
		this.headFillChar = headFillChar;
	}

	public String getContentFillType() {
		return contentFillType;
	}

	public void setContentFillType(String contentFillType) {
		this.contentFillType = contentFillType;
	}

	public char getContentFillChar() {
		return contentFillChar;
	}

	public void setContentFillChar(char contentFillChar) {
		this.contentFillChar = contentFillChar;
	}

	
}
