package edu.upenn.cis350.gpx;

import java.sql.Date;
import java.util.ArrayList;

import junit.framework.TestCase;

public class GPXcalculatorTest extends TestCase {

	public void test_Null(){ //if object is null, then -1
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(null));	
	}
	
	public void test_EmptyGPXtrk(){ // if no GPXtrkseg objs, then return -1
		ArrayList<GPXtrkseg> empty = new ArrayList<GPXtrkseg>();
		GPXtrk holder = new GPXtrk("holder",empty);
		assertEquals(-1,GPXcalculator.calculateDistanceTraveled(holder));
	}
	
	
	public void test_BasicCalc(){ //ensure simple distance calc with 2 pts works
		GPXtrkseg b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(15,22,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(13.0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	
	public void test_ZeroCalc(){ //ensure simple distance calc with 2 pts works
		GPXtrkseg b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		w= new GPXtrkpt(0,0,new Date(0));
		x= new GPXtrkpt(0,0,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	public void test_MultipleCalc(){ //ensure distance calc with >2 pts works
		GPXtrkseg b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(13,14,new Date(0)); //distance should be 5
		x= new GPXtrkpt(21,29,new Date(0)); //incremental distance should be 17
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(22.0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	public void test_NegativeDistCalc(){ //ensure distance calc with intial lat/long greater than next lat/long is as expected
		GPXtrkseg b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		w= new GPXtrkpt(13,14,new Date(0));
		x= new GPXtrkpt(10,10,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(5.0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	public void test_NullGPXtrkseg(){
		GPXtrkseg a, b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		a=null;
		testList.add(a); //a is null
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(13,14,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(5.0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	public void test_EmptyGPXtrkseg(){
		GPXtrkseg a, b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		a = new GPXtrkseg(new ArrayList<GPXtrkpt>());
		testList.add(a); //a is empty
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(13,14,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(5.0,GPXcalculator.calculateDistanceTraveled(trk));
	}
	
	public void test_OneGPXtrkseg(){
		GPXtrkseg a, b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		ArrayList<GPXtrkpt>aList = new ArrayList<GPXtrkpt>();
		aList.add(new GPXtrkpt(10,10,new Date(0)));
		a = new GPXtrkseg(aList);
		testList.add(a); //a has only one point
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(13,14,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(5.0,GPXcalculator.calculateDistanceTraveled(trk)); //a's dist should be 0
	}
	
	public void test_NullGPXtrkpt(){
		GPXtrkseg a, b;
		GPXtrkpt w, x;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		ArrayList<GPXtrkpt>aList = new ArrayList<GPXtrkpt>();
		aList.add(null);
		a = new GPXtrkseg(aList);
		testList.add(a); //a has null GPXtrkpt
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(10,10,new Date(0));
		x= new GPXtrkpt(13,14,new Date(0)); //distance should be 5
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(5.0,GPXcalculator.calculateDistanceTraveled(trk)); //a's dist should be 0
		ptList.add(null);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk)); //a's dist should be 0
	}
	
	public void test_LatGPXtrkpt(){
		GPXtrkseg a, b;
		GPXtrkpt w, x, y, z, wa, xa,ya, za;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		ArrayList<GPXtrkpt>aList = new ArrayList<GPXtrkpt>();
		aList.add(null);
		a = new GPXtrkseg(aList);
		testList.add(a);
		wa= new GPXtrkpt(0,0,new Date(0));
		xa= new GPXtrkpt(-89,0,new Date(0)); //distance should be 89
		ya= new GPXtrkpt(-90,0,new Date(0)); //distance should be 90
		za= new GPXtrkpt(-91,0,new Date(0)); //distance should be 0: illegal value
		aList.add(wa);
		aList.add(xa);
		assertEquals(89.0,GPXcalculator.calculateDistanceTraveled(trk));
		aList.add(ya);
		assertEquals(90.0,GPXcalculator.calculateDistanceTraveled(trk));
		aList.add(za);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(0,0,new Date(0));
		x= new GPXtrkpt(89,0,new Date(0)); //distance should be 89
		y= new GPXtrkpt(90,0,new Date(0)); //legal
		z= new GPXtrkpt(91,0,new Date(0)); //illegal
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(89.0,GPXcalculator.calculateDistanceTraveled(trk)); //b's dist should be 89
		ptList.add(y);
		assertEquals(90.0,GPXcalculator.calculateDistanceTraveled(trk)); //b's dist should be 90
		ptList.add(z);
		assertEquals(0,GPXcalculator.calculateDistanceTraveled(trk)); //91 is out of range b's dist should be 0
		}
	
	public void test_LongGPXtrkpt(){
		GPXtrkseg a, b;
		GPXtrkpt w, x, y, z, wa, xa,ya, za;
		ArrayList<GPXtrkseg >testList = new ArrayList<GPXtrkseg>();
		GPXtrk trk = new GPXtrk("trk",testList);
		ArrayList<GPXtrkpt>aList = new ArrayList<GPXtrkpt>();
		aList.add(null);
		a = new GPXtrkseg(aList);
		testList.add(a);
		wa= new GPXtrkpt(0,0,new Date(0));
		xa= new GPXtrkpt(0,-179,new Date(0)); //distance should be 179
		ya= new GPXtrkpt(0,-180,new Date(0)); //distance should be 180
		za= new GPXtrkpt(0,-181,new Date(0)); //distance should be 0: illegal value
		aList.add(wa);
		aList.add(xa);
		assertEquals(179.0,GPXcalculator.calculateDistanceTraveled(trk));
		aList.add(ya);
		assertEquals(180.0,GPXcalculator.calculateDistanceTraveled(trk));
		aList.add(za);
		assertEquals(181.0,GPXcalculator.calculateDistanceTraveled(trk));
		w= new GPXtrkpt(0,0,new Date(0));
		x= new GPXtrkpt(0,179,new Date(0)); //same as above but w/ positive
		y= new GPXtrkpt(0,180,new Date(0)); //legal
		z= new GPXtrkpt(0,181,new Date(0)); //illegal
		ArrayList<GPXtrkpt> ptList= new ArrayList<GPXtrkpt>();
		ptList.add(w);
		ptList.add(x);
		b = new GPXtrkseg(ptList);
		testList.add(b);
		assertEquals(179.0,GPXcalculator.calculateDistanceTraveled(trk)); //b's dist should be 89
		ptList.add(y);
		assertEquals(180.0,GPXcalculator.calculateDistanceTraveled(trk)); //b's dist should be 90
		ptList.add(z);
		assertEquals(0.0,GPXcalculator.calculateDistanceTraveled(trk)); //91 is out of range b's dist should be 0
		}
	
}
