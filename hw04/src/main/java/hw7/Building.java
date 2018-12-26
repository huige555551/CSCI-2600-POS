package hw7;
/**
 * 
 * @author alanluo
 * @Overview
 * A building is a representation of a location on map. It has x,y coordinate, title and id.
 */
public class Building implements Comparable<Building> {
	private String title;
	private int id;
	private int x;
	private int y;
	/**
	 * @effects Constructs a new building object
	 */
	public Building() {
		this.x =0;
		this.y= 0;
		this.title ="";
		this.id =0;
	}
	/**
	 * 
	 * @param building_title: title of building
	 * @param building_id: id of the building 
	 * @param building_x: x coordinate of the building
	 * @param building_y: y coordinate of the building
	 * @effects Constructs a new building object
	 */
	public Building(String building_title, int building_id, int building_x, int building_y) {
		this.title = building_title;
		this.id = building_id;
		this.x = building_x;
		this.y = building_y;
	}
	/**
	 * 
	 * @return  title of the building
	 */
	public String getTitle(){
		return this.title;
	}
	/**
	 * 
	 * @return x coordinate of the building
	 */
	public int getX(){
		return this.x;
	}/**
	 * 
	 * @return  y coordinate of the building
	 */
	public int getY(){
		return this.y;
	}/**
	 * 
	 * @return  ID of the building
	 */
	public int getId(){
		return this.id;
	}
	/**
	 * 
	 * @param bu: building compared to
	 * @return true if two building are the same
	 */
	public boolean equals(Building bu) {
		if(this.title.equals(bu.getTitle())&& this.id==bu.getId()) {
			return true;
		}
		return false;
	}
	public boolean equals(String bu) {
		if(this.title.equals(bu)||Integer.toString(this.id).equals(bu)) {
			return true;
		}
		return false;
	}
	@Override
	/**
	 * @return a integer value for comparison
	 */
	public int compareTo(Building bu) {
		return this.id-bu.getId();
	}
	/**
	 * @return hashcode value of the building
	 */
	@Override 
	public int hashCode()
	{
	    return id;  
	}
	/**
	 * 
	 * @param b destination building
	 * @return the direction from origin to destination
	 */
	public String getDirection(Building b){
		double angle;
		double dist = Math.sqrt(Math.pow(this.y-b.getY(),2)+Math.pow(b.getX()-this.x, 2));
		
		if (x < b.getX()) { 
			angle = Math.toDegrees(Math.acos((y-b.getY())/dist));
            if (0 <= angle && angle < 22.5) 
                return "North";
            else if (22.5 <= angle && angle < 67.5)
                return "NorthEast"; 
            else if (67.5 <= angle && angle < 112.5)
                return "East";
            else if (112.5 <= angle && angle < 157.5)
                return "SouthEast";
            else   
                return "South";
        }
        else {
        	angle = Math.toDegrees(Math.acos((b.getY()-this.y)/dist));
            if (0 <= angle && angle < 22.5) 
                return "South";
            else if (22.5 <= angle && angle < 67.5)
                return "SouthWest";
            else if (67.5 <= angle && angle < 112.5)
                return "West";
            else if (112.5 <= angle && angle < 157.5)
                return "NorthWest";
            else 
                return "North"; 
        } 
	}

}
