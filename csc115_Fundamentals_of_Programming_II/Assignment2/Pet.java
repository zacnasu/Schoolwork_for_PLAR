//Zachary Nasu
//V00911790

public class Pet{
	private String type;
	private String name;
	private Date birthdate;
	
	public Pet( String type, String name){
		this.type = type;
		this.name = name;
		this.birthdate = new Date();
	}
	public Pet(String type, String name, Date birthdate){
		this.type = type;
		this.name = name;
		this.birthdate = birthdate;
	}
	public void setType(String type){
		this.type = type;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setBirthdate(Date birthdate){
		this.birthdate = birthdate;
	}
	public String getType(){
		return this.type;
	}
	public String getName(){
		return this.name;
	}
	public Date getBirthdate(){
		return this.birthdate;
	}
	public boolean isOlder(Pet other){
		Date current = this.getBirthdate();
		if(!(current.isBefore(other.getBirthdate()))){
			return false;
		}else{
			return true;
		}
	}
	public String toString(){
		String a = this.type + ": " + this.name + ": " + this.getBirthdate().toString();
		return a;
	}
}