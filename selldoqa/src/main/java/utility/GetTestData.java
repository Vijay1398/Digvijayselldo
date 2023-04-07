package utility;

import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class GetTestData {

	// Generate random data using FAKER Class API and Random Class API

	Faker faker = new Faker(new Locale("en", "IN"));
	public String firstName = faker.firstName();
	public String lastName = faker.lastName();
	public String location = faker.country();
	public String address = faker.streetAddress(false);
	public String street = faker.streetName();
	public String city = faker.cityPrefix();
	public String state = faker.stateAbbr();
	public String zip = faker.zipCode();
	public String minBudget = faker.numerify("1000");
	public String maxBudget = faker.numerify("100000");
	public String team = faker.letterify("amura");
	public String locationPreference = faker.letterify("Pune");
	public String title = faker.letterify("Get Document");
	public String description = faker.sentence(15);
	public String transactionalSmsMaskfaker = faker.toString();
	


	Random r = new Random();
	public long phoneNumber = (long) (Math.random() * 100000 + 9833000000L);
	public long phoneNum = (long) (Math.random() * 100000 + 7833000000L);
	public String email = "email" + r.nextInt() + "@gmail.com";
	public String clientEmail = "email" + r.nextInt() + "@gm.com";

}
