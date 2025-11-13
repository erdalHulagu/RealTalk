package com.erdal.realTalk.user.requests;



public class UserRequest {
	
	    private Long id;

	   
	    private String fullName;

	   
	    private String email;

	   
	    private String password;
	    
	    
	    
	    

	    public  UserRequest() {
		}
	    

	    public UserRequest(String fullName, String email, String password) {
	        this.fullName = fullName;
	        this.email = email;
	        this.password = password;
	    }

	    // Getter - Setter
	    public Long getId() { return id; }

	    public void setId(Long id) { this.id = id; }

	    public String getFullName() { return fullName; }

	    public void setFullName(String username) { this.fullName = username; }

	    public String getEmail() { return email; }

	    public void setEmail(String email) { this.email = email; }

	    public String getPassword() { return password; }

	    public void setPassword(String password) { this.password = password; }

	   

	    @Override
	    public String toString() {
	        return "User{" +
	                "id=" + id +
	                ", fullName='" + fullName + '\'' +
	                ", email='" + email + '}';
	    }

}
