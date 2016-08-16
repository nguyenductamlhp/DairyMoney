package jpa.test;

import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;

import jpa.model.entity.Event;
import jpa.model.service.EventService;

public class TestEventService {

	private static void testGetAllEvent() {
		EventService eventService = new EventService();
		List<Event>  listAllEvent = eventService.getAllEvent();
		for (Event event : listAllEvent) {
			System.out.println(event.getName());
		}
	}
	
	private static void testGetAllEventByYear(int year) {
		EventService eventService = new EventService();
		List<Event>  listAllEventByYear = eventService.getAllEventByYear(year);
		for (Event event : listAllEventByYear) {
			System.out.println(event.getName());
		}
	}
	
	private static void testGetAllEventByMonth(int year, int month) {
		EventService eventService = new EventService();
		List<Event>  listAllEventByYear = eventService.getAllEventByMonth(year, month);
		for (Event event : listAllEventByYear) {
			System.out.println(event.getName());
		}
	}
	
	private static void testGetTotalCost() {
		EventService eventService = new EventService();
		System.out.println("Total cost: " + eventService.getTotalCost());
	}
	private static void testGetCostByYear(int year) {
		EventService eventService = new EventService();
		System.out.println("Total cost in " + year + " : " + eventService.getCostByYear(year));
	}
	private static void testGetCostByMonth(int year, int month) {
		EventService eventService = new EventService();
		System.out.println("Total cost in " + year + "/" + month + ": " + eventService.getCostByMonth(year, month));
	}
	private static void testGetAllPaying() {
		EventService eventService = new EventService();
		System.out.println("Total paying: " + eventService.getAllPaying());
	}
	private static void testGetAllPayingByYear(int year) {
		EventService eventService = new EventService();
		System.out.println("Total paying in " + year + ": " + eventService.getAllPayingByYear(year));
	}
	private static void testGetAllPayingByMonth(int year, int month) {
		EventService eventService = new EventService();
		System.out.println("Total paying in " + year + "/" + month + ": " + eventService.getAllPayingByMonth(year, month));
	}
	private static void testGetAllPaid() {
		EventService eventService = new EventService();
		System.out.println("Total paid: " + eventService.getAllPaid());
	}
	private static void testGetAllPaidByYear(int year) {
		EventService eventService = new EventService();
		System.out.println("Total paid in " + year + ": " + eventService.getAllPaidByYear(year));
	}
	private static void testGetAllPaidByMonth(int year, int month) {
		EventService eventService = new EventService();
		System.out.println("Total paid in " + year + "/" + month + ": " + eventService.getAllPaidByMonth(year, month));
	}
	
	private static void testGetAllYear() {
		EventService eventService = new EventService();
		List list = eventService.getAllYear();
		for (Object object : list) {
			System.out.println(object.toString());
		}
	}
	
	public static void main(String[] args) {
		testGetAllYear();
	}

}
