//Zachary Nasu
//V00911790

/*
 * Date class
 *  represents a date in the Gregorian calendar (used in most of the world).
 *  started in year 1582
 */
public class Date {
	private int month;
	private int day;
	private int year;

    // TODO add Date attributes/fields...


    /*
     * Purpose: Initialize this instance of Date with values for:
     *  January 01, 1582 with values: month = 1, day = 1, year = 1582
     *
     * Parameters: nothing
     */
    // TODO...
	public Date(){
		month = 1;
		day = 1;
		year = 1582;
	}
		


	/*
     * Purpose: Initialize this instance of Date with parameter values
     *
     * Parameters: int month, int day, int year
     *
     * Precondition:  month, day, and year specify a valid date
     *  in the Gregorian calendar.
     */
    // TODO...
	public Date( int month, int day, int year){
		this.month = month;
		this.day = day;
		this.year = year;
	}

    /*
     * getMonth
     *
     * Purpose: Returns the month associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: int - month associated with the date
     */
    // TODO...
	public int getMonth(){
	int month = this.month;
	return month;
	}


    /*
     * setMonth
     *
     * Purpose: sets the month associated with this Date to parameter value
     *
     * Parameters: int month
     *
     * Precondition: month with this Date's day and year specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
    // TODO...
	public void setMonth(int month){
		this.month = month;
	}


    /*
     * getDay
     *
     * Purpose: Returns the day associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: int - day associated with the date
     */
    // TODO...
	public int getDay(){
		int day = this.day;
		return day;
	}

    /*
     * setDay
     *
     * Purpose: sets the day associated with this Date to parameter value
     *
     * Parameters: int day
     *
     * Precondition: day with this Date's month and year specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
    // TODO...
	public void setDay(int day){
		this.day = day;
	}


    /*
     * getYear
     *
     * Purpose: Returns the year associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: int - year associated with the date
     */
    // TODO...
	public int getYear(){
		int year = this.year;
		return year;
	}


    /*
     * setyear
     *
     * Purpose: sets the year associated with this Date to parameter value
     *
     * Parameters: int year
     *
     * Precondition: year with this Date's day and month specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
    // TODO...
	public void setYear(int year){
		this.year = year;
	}


    /*
     * equals
     *
     * Purpose: determines whether the month, day and year of
     *  this instance of Date is equal to other's month, day, year
     *
     * Parameters: Date other
     *
     * Precondition: other is not null and is a valid Gregorian date
     *
     * Returns: boolean - true if this Date equals other date, false otherwise
     */
    // TODO...
	public boolean equals(Date date2){
		if(this.getDay() != date2.getDay()){
			return false;
		} 
		if(this.getMonth() != date2.getMonth()){
			return false;
		}
		if(this.getYear() != date2.getYear()){
			return false;
		} 
		return true;
		
	}


    /*
     * isBefore
     *
     * Purpose: determines whether the month, day and year of
     *  this instance of Date is strictly before other Date in the calendar
     *
     * Parameters: Date other
     *
     * Precondition: other is not null and is a valid Gregorian date
     *
     * Returns: boolean - true if this Date is before other date, false otherwise
     */
    // TODO...
	
	public boolean isBefore(Date date2){
		if(this.getYear() < date2.getYear()){
			return true;
		}
		if(this.getYear() == date2.getYear()){
			if(this.getMonth() < date2.getMonth()){
				return true;
			}
			if(this.getMonth()==date2.getMonth()){
				if(this.getDay() < date2.getDay()){
					return true;
				}
			}
		}
		
		return false;
	}


    /*
     * isLeapYear
     *
     * Purpose: determines whether this instance of Date's year is a leap year
     *  A leap year is a year that is exactly divisible by 4
     *  except for years that are divisible by 100 but not divisible by 400.
     * Example:
     *      1900 was not a leap year even though it is divisible by 4
     *      because it is divisible by 100 but not by 400
     *
     * Parameters: nothing
     *
     * Returns: boolean - true if this Date is a leap year, false otherwise
     */
    // TODO...
	public boolean isLeapYear(){
		if((this.getYear() % 4) != 0)
			return false;
			
		return true;
		}

    /*
     * toString
     *
     * Purpose: returns a String representing this Date formated as:
     *  year-month-day
     *
     * Parameters: nothing
     *
     * Returns: String - a representation of this Date
     *
     * Example:
     *  Date d = new Date(10, 25, 2019)
     *  d.toString() returns "2019-10-25"
     */
    // TODO...
	public String toString(){
		String ans = this.getYear() + "-" + this.getMonth() + "-" + this.getDay();
	return ans;
}
}
