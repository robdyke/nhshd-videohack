package controllers;

import java.util.List;

import models.Appointment;
import play.*;
import play.mvc.*;

import views.html.*;

import play.data.DynamicForm;


public class Application extends Controller {
  
  public static Result index() {
	  return ok(index.render());
  }
	
  public static Result login() {
    return ok(login.render());
  }
  
  public static Result dashboard() {
	  
	  final DynamicForm form = form().bindFromRequest();
	      final String name = form.get("username");
	 //     return ok(index.render(String.format("You are %s, %s",surname, name)));
	  
	  	session("connected", name);
	  
	  	Logger.info(name);
	  	
	    return ok(dashboard.render());
  }
  
  public static Result admin() {
	  List<Appointment> appointments = Appointment.all();
	  return ok(admin.render(appointments));
  }
  
  public static Result clinician() {
	  List<Appointment> appointments = Appointment.all();
	  return ok(clinician.render(appointments));
  }
    
  public static Result patient() {
	  String username = session("connected");
	  return ok(patient.render(username));
  }
  
  public static Result test(String start, String end, String title, String body, String username) {
  
	  Logger.info ( "start: " + start + " end: " + end );
	  
	  Appointment appointment = new Appointment();
	  
	  appointment.patient = username;
	  appointment.start = start;
	  appointment.end = end;
	  appointment.title = title;
	  appointment.body = body;
	  
	  appointment.save();
	  
	  
	  
	  Logger.info( "patient"+ appointment.patient + "date" + appointment.start + appointment.end + appointment.title + appointment.body );
	  
	  
	  return ok();
  
  }  
  
  public static Result list() {
	  List<Appointment> appointments = Appointment.all();
	  //Appointment appointment = new Appointment();
	  
	  Logger.info("lenth " + appointments.size());
	  
	return ok();
	  
  }
  
  
}