/*
 * Date class
 *  represents a date in the Gregorian calendar (used in most of the world).
 *  started in year 1582
 */
public class Date {
    
	private int month;
	private int day;
    private int year;
    
    /*
     * Purpose: Initialize this instance of Date with values for: January 01, 1582
     *
     * Parameters: nothing
     */
    public Date () {
        
        this.month = 1;
        this.day   = 1;
        this.year  = 1582;
    }

	/*
     * Purpose: Initialize this instance of Date with parameter values
     *
     * Parameters: int day, int month, int year
     *
     * Precondition: day, month and year specify a valid date
     *  in the Gregorian calendar.
     */
	public Date (int month, int day, int year) {
        
        this.month = month;
        this.day   = day;
        this.year  = year;
	}

    /*
     * Purpose: Returns the month associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: (int) - month associated with the date
     */
    public int getMonth () {
        return month;
    }
    
    /*
     * Purpose: sets the month associated with this Date to parameter value
     *
     * Parameters: int month
     *
     * Precondition: month with this Date's day and year specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
	public void setMonth (int month) {
		this.month = month;
	}
    
    /*
     * Purpose: Returns the day associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: (int) - day associated with the date
     */
    public int getDay () {
        return day;
    }
    
    /*
     * Purpose: sets the day associated with this Date to parameter value
     *
     * Parameters: int day
     *
     * Precondition: day with this Date's month and year specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
    public void setDay (int day) {
        this.day = day;
    }
    
    /*
     * Purpose: Returns the year associated with this Date
     *
     * Parameters: nothing
     *
     * Returns: (int) - year associated with the date
     */
    public int getYear () {
        return year;
    }
    
    /*
     * Purpose: sets the year associated with this Date to parameter value
     *
     * Parameters: int year
     *
     * Precondition: year with this Date's day and month specify a valid date
     *  in the Gregorian calendar.
     *
     * Returns: nothing
     */
    public void setYear (int year) {
        this.year = year;
    }

    /*
     * Purpose: determines whether the month, day and year of
     *  this instance of Date is equal to other's month, day, year
     *
     * Parameters: Date other
     *
     * Precondition: other is not null and is a valid Gregorian date
     *
     * Returns: true if this Date equals other date, false otherwise
     */
    public boolean equals (Date other) {
        return  other.getMonth() == month &&
                other.getDay()   == day &&
                other.getYear()  == year;
    }
    
    /*
     * Purpose: determines whether the month, day and year of
     *  this instance of Date is strictly before other Date in the calendar
     *
     * Parameters: Date other
     *
     * Precondition: other is not null and is a valid Gregorian date
     *
     * Returns: true if this Date is before other date, false otherwise
     */
    public boolean isBefore (Date other) {
        return  year < other.getYear() ||
                (year == other.getYear() && month < other.getMonth()) ||
                (year == other.getYear() && month == other.getMonth() && day < other.getDay());
    }
    
    /*
     * Purpose: determines whether this instance of Date's year is a leap year
     *  A leap year is a year that is exactly divisible by 4
     *  except for years that are divisible by 100 but not divisible by 400.
     * Example:
     *      1900 was not a leap year even though it is divisible by 4
     *      because it is divisible by 100 but not by 400
     *
     * Parameters: nothing
     *
     * Returns: true if this Date is a leap year, false otherwise
     */
    public boolean isLeapYear () {
        if (year % 4 != 0)
            return false;
        
        else if (year % 100 == 0 && year % 400 != 0)
            return false;
        
        else
            return true;
    }

    /*
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
	public String toString() {
			return year + "-" + month + "-" + day;
	}
}
