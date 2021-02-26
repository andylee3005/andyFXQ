package lee.andyfxq.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//@XmlRootElement(name = "Client")
//@XmlAccessorType(XmlAccessType.FIELD)
@Document(collection = "clients")
public class Client {
	
	public static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	
	@Id
	private String id;
	
	@NotNull
	@NotBlank
	private String fn;
	
	@NotNull
	@NotBlank
	private String ln;
	
	@Size(max = 50)
	@Email
	private String email;
	
	private String address;
	private String phone;
	private String post;
	private String birthdate;
	private String created;
	private String updated;
	
	
	
	public Client() { }

	public Client(@NotNull @NotBlank String fn, @NotNull @NotBlank String ln, @Size(max = 50) @Email String email,
			String address, String phone, String post, String birthdate) {
		
		this.fn = fn;
		this.ln = ln;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.post = post;
		this.birthdate = birthdate;
		this.created = dtf.format(LocalDateTime.now());
		this.updated = dtf.format(LocalDateTime.now());
	}

//	public Client(@NotNull @NotBlank String fn, @NotNull @NotBlank String ln, @Size(max = 50) @Email String email) {
//		
//		this.fn = fn;
//		this.ln = ln;
//		this.email = email;
//		this.created = dtf.format(LocalDateTime.now());
//		this.updated = dtf.format(LocalDateTime.now());
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Client [" + fn + ln + ", email=" + email + ", address=" + address + ", phone=" + phone
				+ ", post=" + post + ", DOB=" + birthdate + ", created=" + created + ", updated=" + updated + "]";
	}
	
	
	
	
}
