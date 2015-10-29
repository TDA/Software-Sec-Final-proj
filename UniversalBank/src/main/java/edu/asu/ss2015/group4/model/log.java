package edu.asu.ss2015.group4.model;

import java.util.Date;

public class log {
	private String id;     
    private String content;    
    private Date time;  

    public log() {}
         
    public log(String id, String content,Date time) 
    {
        this.id = id;
        this.content = content;
        this.time=time;
             
    }

    
 // Getter and Setter methods
	
	public String getid() {
		return id;
	}

	public void setid(String id) {
		this.id = id;
	}
	
	public Date gettime() {
		return time;
	}

	public void settime(Date time) {
		this.time = time;
	}

	public String getcontent() {
		return content;
	}

	public void setcontent(String content) {
		this.content = content;
	}

	
}