package com.exercise.tema1;

import com.exercise.tema1.domain.Course;
import com.exercise.tema1.domain.Employee;
import com.exercise.tema1.service.CourseService;
import com.exercise.tema1.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class Tema1Application {

	private static EmployeeService employeeService;
	private static CourseService courseService;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public Tema1Application(EmployeeService employeeService, CourseService courseService) {
		this.employeeService = employeeService;
		this.courseService = courseService;
	}

	public static EmployeeService getEmpService(){
		return employeeService;
	}

	public static CourseService getCourseService(){
		return courseService;
	}
	
	public static void main(String[] args) throws IOException {
		SpringApplication.run(Tema1Application.class, args);
		List<Employee> employeeList = getEmpService().findAll();

		for (Employee e: employeeList) {
			System.out.println(e.toString());
		}

		List<Course> courselist = getCourseService().findAll();

		for(Course c: courselist) {
			System.out.println(c.toString());
		}


		boolean loop_condition = true, diploma;
		int choice = 9;
		int hours, value;
		long employeeId, courseId;
		String name, company, function, dateInString, year;
		Date hireDate;
		SimpleDateFormat formatter;

		while (loop_condition)
		{
			System.out.println("\n");
			System.out.println("1.Add Emp");
			System.out.println("2.Delete Emp");
			System.out.println("3.Find Emp");
			System.out.println("4.Update Emp");
			System.out.println("5.Add Course");
			System.out.println("6.Delete Course");
			System.out.println("7.Find Course");
			System.out.println("8.Update Course");
			System.out.println("9.List all Emp from given Company");
			System.out.println("10.List all Emp older that given period");
			System.out.println("11.List all Emp older from given Course");
			System.out.println("0.To exit");
			System.out.print("Pick:");

			try{
				choice = Integer.parseInt(br.readLine());
			}catch(NumberFormatException nfe){
				System.err.println("Invalid Format!");
			}

			switch(choice)
			{
				case 0:
					loop_condition=false;
					break;
				case 1:
					//add

					System.out.println("Name:");
					name = br.readLine();
					System.out.println("Company");
					company = br.readLine();
					System.out.println("Function");
					function = br.readLine();
					System.out.println("Date");
					dateInString = br.readLine();

					formatter = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
					formatter.setTimeZone(TimeZone.getTimeZone("GMT"));


					try {
						hireDate = formatter.parse(dateInString);
						getEmpService().addEmployee(name,company, function, hireDate);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					//delete
					System.out.println("Employee:");

					try{
						employeeId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					getEmpService().deleteEmployee(employeeId);
					break;
				case 3:
					//find
					System.out.println("Employee:");

					try{
						employeeId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					if(getEmpService().findEmployee(employeeId).isPresent()) {
						System.out.println(getEmpService().findEmployee(employeeId).get().toString());
					} else {
						System.out.println("No employee with given Id");
					}
					break;
				case 4:
					//update
					System.out.println("Employee:");

					try{
						employeeId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					if(getEmpService().findEmployee(employeeId).isPresent()) {
						getEmpService().findEmployee(employeeId).get().toString();
						System.out.println("Name:");
						name = br.readLine();
						System.out.println("Company");
						company = br.readLine();
						System.out.println("Function");
						function = br.readLine();
						System.out.println("Date");
						dateInString = br.readLine();

						formatter = new SimpleDateFormat("dd.M.yyyy", Locale.ENGLISH);
						formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
						try {
							hireDate = formatter.parse(dateInString);
							getEmpService().updateEmployee(getEmpService().findEmployee(employeeId).get(), name,company, function, hireDate);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					} else {
						System.out.println("No employee with given Id");
					}
					break;
				case 5:
					//add
					//String name, int hours, int value, boolean diploma, String year, Employee employee) {
					System.out.println("Name:");
					name = br.readLine();
					System.out.println("hours");

					try{
						hours = Integer.parseInt(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					System.out.println("Value");

					try{
						value = Integer.parseInt(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					System.out.println("Diploma ( 0-false   1-true)");
					int a;
					try{
						a = Integer.parseInt(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}

					if(a > 0)
						diploma = true;
					else
						diploma = false;
					System.out.println("Year");
					year = br.readLine();
					System.out.println("Employee:");

					try{
						employeeId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}

					if(getEmpService().findEmployee(employeeId).isPresent()) {
						getCourseService().addCourse(name, hours, value, diploma, year, getEmpService().findEmployee(employeeId).get());
					} else {
						System.out.println("No employee with given Id");
					}

					break;
				case 6:
					//delete
					System.out.println("Course:");

					try{
						courseId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					getCourseService().deleteCourse(courseId);
					break;
				case 7:
					//find
					System.out.println("Course:");

					try{
						courseId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					if(getCourseService().findCourse(courseId).isPresent()) {
						System.out.println(getCourseService().findCourse(courseId).get().toString());
					} else {
						System.out.println("No Course with given Id");
					}
					break;
				case 8:
					//update
					System.out.println("Course:");
					try{
						courseId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					if(getCourseService().findCourse(courseId).isPresent()) {
						System.out.println("Name:");
						name = br.readLine();
						System.out.println("hours");
						try{
							hours = Integer.parseInt(br.readLine());
						}catch(NumberFormatException nfe){
							System.err.println("Invalid Format!");
							break;
						}
						System.out.println("Value");
						try{
							value = Integer.parseInt(br.readLine());
						}catch(NumberFormatException nfe){
							System.err.println("Invalid Format!");
							break;
						}
						System.out.println("Diploma ( 0-false   1-true)");

						try{
							a = Integer.parseInt(br.readLine());
						}catch(NumberFormatException nfe){
							System.err.println("Invalid Format!");
							break;
						}
						if(a > 0)
							diploma = true;
						else
							diploma = false;
						System.out.println("Year");
						year = br.readLine();
						System.out.println("Employee:");

						try{
							employeeId = Long.parseLong(br.readLine());
						}catch(NumberFormatException nfe){
							System.err.println("Invalid Format!");
							break;
						}

						if(getEmpService().findEmployee(employeeId).isPresent()) {
							getCourseService().updateCourse(getCourseService().findCourse(courseId).get(), name, hours, value, diploma, year, getEmpService().findEmployee(employeeId).get());
						} else {
							System.out.println("No employee with given Id");
						}
					} else {
						System.out.println("No Course with given Id");
					}
					break;
				case 9:
					System.out.println("Company:");
					company = br.readLine();
					employeeList = getEmpService().findAllEmployeesFromComany(company);

					for (Employee e: employeeList) {
						System.out.println(e.toString());
					}
					break;
				case 10:
					System.out.println("Period:");
					int period;
					try{
						period = Integer.parseInt(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					int today = Calendar.getInstance().get(Calendar.YEAR);
					Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
					employeeList = getEmpService().findAll();
					for (Employee e: employeeList) {
						cal.setTime(e.getHireDate());
						if(today - cal.get(Calendar.YEAR) > period)
							System.out.println(e.toString());
					}
					break;
				case 11:
					//Only one Employee due to relation
					System.out.println("Course:");
					try{
						courseId = Long.parseLong(br.readLine());
					}catch(NumberFormatException nfe){
						System.err.println("Invalid Format!");
						break;
					}
					if(getCourseService().findCourse(courseId).isPresent()) {
						System.out.println(getCourseService().findCourse(courseId).get().getEmployee().getName() + " took " + getCourseService().findCourse(courseId).get().getName() + " course!");
					} else {
						System.out.println("No Course with given Id");
					}
					break;
				default:  System.err.println("Invalid! Pick again!"); break;
			}

		}
		
	}
}
