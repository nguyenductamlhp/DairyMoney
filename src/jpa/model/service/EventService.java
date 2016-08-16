package jpa.model.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import jpa.model.entity.Event;

public class EventService {

	public EventService() {

	}
	
	/**
	 * Get information
	 * 
	 */
	@SuppressWarnings("unchecked")
	public List<Event> getAllEvent() {
		List<Event> reAllEvent = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryAllEvent = entityManager.createQuery("select e from Event e");
		reAllEvent  = queryAllEvent.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return reAllEvent;
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEventByYear(int year) {
		List<Event> reAllEventByYear = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		Query queryAllEvent = entityManager.createQuery("select e from Event e where e.year = " + year);
		reAllEventByYear  = queryAllEvent.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return reAllEventByYear;
	}
	
	@SuppressWarnings("unchecked")
	public List<Event> getAllEventByMonth(int year, int month) {
		List<Event> reAllEventByMonth = null;
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		Query queryAllEventByMonth = entityManager.createQuery("select e from Event e where e.year = " + year + "and e.month = " + month);
		reAllEventByMonth  = queryAllEventByMonth.getResultList();
		entityManager.close();
		entityManagerFactory.close();
		return reAllEventByMonth;
	}
	public Event getEvent(int id) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		return entityManager.find(Event.class, id);
	}
	
	public int getTotalCost() {
		List<Event> listAllEvent = getAllEvent();
		int Sum = 0;
		for (Event event : listAllEvent) {
			Sum = Sum + event.getCost();
		}
		return Sum;
	}
	public int getCostByYear(int year) {
		List<Event> listAllEventByYear = getAllEventByYear(year);
		int Sum = 0;
		for (Event event : listAllEventByYear) {
			Sum = Sum + event.getCost();
		}
		return Sum;
	}
	public int getCostByMonth(int year, int month) {
		List<Event> listAllEventByMonth = getAllEventByMonth(year, month);
		int Sum = 0;
		for (Event event : listAllEventByMonth) {
			Sum = Sum + event.getCost();
		}
		return Sum;
	}
	public int getAllPaying() {
		List<Event> listAllEvent = getAllEvent();
		int Sum = 0;
		for (Event event : listAllEvent) {
			if (event.getCost() < 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	public int getAllPayingByYear(int year) {
		List<Event> listAllEventByYear = getAllEventByYear(year);
		int Sum = 0;
		for (Event event : listAllEventByYear) {
			if (event.getCost() < 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	public int getAllPayingByMonth(int year, int month) {
		List<Event> listAllEventByMonth = getAllEventByMonth(year, month);
		int Sum = 0;
		for (Event event : listAllEventByMonth) {
			if (event.getCost() < 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	
	public int getAllPaid() {
		List<Event> listAllEvent = getAllEvent();
		int Sum = 0;
		for (Event event : listAllEvent) {
			if (event.getCost() >= 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	public int getAllPaidByYear(int year) {
		List<Event> listAllEventByYear = getAllEventByYear(year);
		int Sum = 0;
		for (Event event : listAllEventByYear) {
			if (event.getCost() >= 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	public int getAllPaidByMonth(int year, int month) {
		List<Event> listAllEventByMonth = getAllEventByMonth(year, month);
		int Sum = 0;
		for (Event event : listAllEventByMonth) {
			if (event.getCost() >= 0) {
				Sum = Sum + event.getCost();
			}
		}
		return Sum;
	}
	public List<Integer> getAllYear() {
		List<Integer> reList = new ArrayList<>();
		reList.clear();
		
		List<Event> listAllEvent = getAllEvent();
		for (Event event : listAllEvent) {
			if (reList.size() == 0) {
				reList.add(event.getYear());
			}
			else {
				boolean exist = false;
				for (int i = 0; i < reList.size(); i++){
					if (reList.get(i) == event.getYear()){
						exist = true;
					}
				}
				if (!exist) {
					reList.add(event.getYear());
				}
			}
		}
		return reList;
	}
	
	/**
	 * 
	 */
	
	public boolean addEvent(Event e) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.persist(e);
		entityManager.flush();
		entityManager.close();
		entityManagerFactory.close();
		return true;
	}
	public boolean editEvent(Event e) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Event event = entityManager.find(Event.class, e.getId());
		event.setDay(e.getDay());
		event.setCost(e.getCost());
		event.setMonth(e.getMonth());
		event.setName(e.getName());
		event.setNote(e.getNote());
		event.setYear(e.getYear());
		
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return true;
	}
	public boolean deleteEvent(Event e) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("DairyMoney");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		Event event = entityManager.find(Event.class, e.getId());
		entityManager.remove(event);
		entityManager.getTransaction().commit();
		entityManager.close();
		entityManagerFactory.close();
		return true;
	}
	
}
