package com.example.asavarikarandikar.chatall;

import java.io.UnsupportedEncodingException;


public class ByteStreamData {
	byte data[];
	
	// sets the data into the byte array
	public void setData(byte[] bytes, int size) {
		byte temp[] = data;
		data = new byte[temp.length+size];
		for (int i = 0; i < temp.length; i++) {
			data[i] = temp[i];
		}
		for (int i = 0; i <size; i++) {
			data[temp.length+i] = bytes[i];
		}
	}
	
	public String getString() {
		try {
			return new String(data, "UTF-8");
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		return null;
	}
}
